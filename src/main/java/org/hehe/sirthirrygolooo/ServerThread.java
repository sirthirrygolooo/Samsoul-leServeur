package org.hehe.sirthirrygolooo;

import com.google.gson.Gson;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.hehe.sirthirrygolooo.Exceptions.MatchingIdException;
import org.hehe.sirthirrygolooo.Exceptions.WrongParamNumberException;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;


public class ServerThread extends Thread {
    private Socket sockComm;
    BufferedReader br;
    PrintStream ps;
    private int id = 0;
    private ServerData data;
    private final MongoDatabase database;
    // autres attributs nécessaires aux traitements des requête


    public ServerThread( int id, Socket sockComm, ServerData data,MongoDatabase database) {
        this.id = id;
        this.sockComm = sockComm;
        this.data = data;
        this.database = database;
        // init. autres attributs
    }

    public void run() {
        try
        {
            br = new BufferedReader(new InputStreamReader(sockComm.getInputStream()));
            ps = new PrintStream(sockComm.getOutputStream());
            requestLoop();
        } catch (IOException e)
        { System.err.println(e); }

    }

    public void requestLoop()throws IOException {
        ps.println(id);
        System.out.println("Client "+id+" connecté");
        data.addUser(String.valueOf(id));

        MongoCollection<Document> collection = database.getCollection("SamSoul_data");



        //Au début du thread, on cherche si l'id de l'utilisateur est dans la BDD
        FindPublisher<Document> findDocPublisher = collection.find(eq("id",id));
        Document findResults = Flux.from(findDocPublisher)
                .doOnNext(System.out::println)
                .blockLast();

        System.out.println("On trouve" + findResults);

        //Si il n'est pas dedans, on le crée
        if (findResults == null) {
            Document newUser = new Document("_user", id)
                    .append("reactionTime", new ArrayList<Double>())
                    .append("heartRateData", new Document())
                    .append("RightAccelerometerData", new Document())
                    .append("LeftAccelerometerData", new Document())
                    .append("bodyTemperatureData", new Document());
            Publisher<InsertOneResult> insertOnePublisher = collection.insertOne(newUser);
            InsertOneResult result = Mono.from(insertOnePublisher).block();
        }



//        System.out.printf("Inserted 1 document with ID %s.\n",
//                result.getInsertedId());





        while(true){
            processResponse();
        }

    }

    public void processResponse() throws IOException {
        String line = br.readLine();
        String[] request = line.split(" ");
        System.out.println("Client "+ id +" received response from client: "+line);
        try{
            if(!request[0].equals(String.valueOf(id))){
                throw new MatchingIdException(request[0]);
            }
               switch (request[1]) {
                case "addHeartRate" ->{
                    checkParamNumber(request,3);
                    data.getUserData(request[0]).addHeartRateData(Double.valueOf(request[2]));
                    ps.println("OK");
                }
                case "getUserHeartRates" ->{
                    checkParamNumber(request,2);
                    ps.println(data.getUserData(request[0]).getHeartRateData());
                }
                case "addReactionTime" ->{
                    checkParamNumber(request,3);
                    data.getUserData(request[0]).addReactionTime(Double.valueOf(request[2]));
                    ps.println("OK");
                }
                case "getUserReactionTimes" ->{
                    checkParamNumber(request,2);
                    ps.println(data.getUserData(request[0]).getReactionTime());
                }
                case "getAll" -> {
                    checkParamNumber(request,2);
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    System.out.println(json);
                    ps.println("OK");
                }
                case "addBodyTemperature" ->{
                    checkParamNumber(request,3);
                    data.getUserData(request[0]).addBodyTemperatureData(Double.valueOf(request[2]));
                    ps.println("OK");
                }
                case "getUserBodyTemperature" ->{
                    checkParamNumber(request,2);
                    ps.println(data.getUserData(request[0]).getBodyTemperatureData());
                }
                case "addLeftAccelerometerData" ->{
                    checkParamNumber(request,3);
                    data.getUserData(request[0]).addLeftAccelerometerData(Double.valueOf(request[2]));
                    ps.println("OK");
                }
                case "getUserLeftAccelerometerData" ->{
                    checkParamNumber(request,2);
                    ps.println(data.getUserData(request[0]).getLeftAccelerometerData());
                }
                case "addRightAccelerometerData" ->{
                    checkParamNumber(request,3);
                    data.getUserData(request[0]).addRightAccelerometerData(Double.valueOf(request[2]));
                    ps.println("OK");
                }
                case "getUserRightAccelerometerData" ->{
                    checkParamNumber(request,2);
                    ps.println(data.getUserData(request[0]).getRightAccelerometerData());
                }
                default ->{
                    throw new IOException();
                }
            }
        }
        catch(MatchingIdException e){
            System.out.println("Error : "+e);
            ps.println("Err_wrong_id");
        }
        catch(NumberFormatException e){
            System.out.println("Error : "+e);
            ps.println("Err_req_value_format");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error : "+e);
            ps.println("Err_id");
        }
        catch (WrongParamNumberException e){
            System.out.println("Error : "+e);
            ps.println("Err_wrong_param_number");
        }
        catch(Exception e){
            System.out.println("Error : "+e);
            ps.println("Err_req_format");
        }

    }
        // etc. avec les autres méthodes processRequestX()


    // checks the number of parameters
    private void checkParamNumber(String[] request, int paramNumbers) throws WrongParamNumberException {
        if(request.length != paramNumbers){
            throw new WrongParamNumberException("Wrong number of request parameters");
        }
    }

}

