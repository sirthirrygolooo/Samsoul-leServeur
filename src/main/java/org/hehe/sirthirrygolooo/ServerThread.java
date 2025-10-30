package org.hehe.sirthirrygolooo;

import org.hehe.sirthirrygolooo.Exceptions.MatchingIdException;

import java.io.*;
import java.net.*;



public class ServerThread extends Thread {
    private Socket sockComm;
    BufferedReader br;
    PrintStream ps;
    private int id = 0;
    private ServerData data;
    // autres attributs nécessaires aux traitements des requête


    public ServerThread( int id, Socket sockComm, ServerData data){
        this.id = id;
        this.sockComm = sockComm;
        this.data = data;
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


        while(true){
            processResponse();
        }

    }

    public void processResponse() throws IOException {
        String line = br.readLine();

        String[] request = line.split(" ");

        System.out.println("Client "+ id +" received response from server: "+line);



        try{

            if(!request[0].equals(String.valueOf(id))){
                throw new MatchingIdException(request[0]);
            }

               switch (request[1]) {
                case "addHeartRate" ->{
                    data.getUserData(request[0]).addHeartRateData(Double.valueOf(request[2]));
                    ps.println("OK");
                }
                case "getUserHeartRates" ->{
                    ps.println(data.getUserHeartRateData(request[0]));
                }
                case "addReactionTime" ->{
                    data.getUserData(request[0]).addReactionTime(Double.valueOf(request[2]));
                    ps.println("OK");
                }
                case "getUserReactionTimes" ->{
                    ps.println(data.getUserData(request[0]).getReactionTime());
                }
                case "getAll" -> {
                    System.out.println(data);
                    ps.println("OK");
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
        catch(Exception e){
            System.out.println("Error : "+e);
            ps.println("Err_req_format");
        }

    }

        // etc. avec les autres méthodes processRequestX()
}

