package org.hehe.sirthirrygolooo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.*;
import java.net.*;

class ServerTCP {
    private ServerSocket sockConn;
    private int id;
    private ServerData data; // l'objet partagé entre les threads
    private final MongoClient mongoClient;
    private final MongoDatabase database;


    public ServerTCP(int port) throws IOException {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        sockConn = new ServerSocket(port);
        this.mongoClient = MongoClients.create("mongodb+srv://" + username + ":" + password + "@clustersamsoul.ku08vkx.mongodb.net/?appName=ClusterSamsoul");
        this.database = mongoClient.getDatabase("samsoul_Test");
        data = new ServerData();
        id = 0;

    }
    public void mainLoop() {
        while(true) {
            try {
                Socket sockComm = sockConn.accept();
                id += 1;
                ServerThread t = new ServerThread(id, sockComm, data,database);
                t.start();
            }
            catch(IOException e) {  }
        }
    }
}