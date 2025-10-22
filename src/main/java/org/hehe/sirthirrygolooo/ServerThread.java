package org.hehe.sirthirrygolooo;

import java.io.*;
import java.net.*;



public class ServerThread extends Thread {
    private Socket sockComm;
    BufferedReader br;
    PrintStream ps;
    private int id;
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
            // code identique à celui du canevas serveur mono-threadé
    }

    public void processRequest1() throws IOException {
            // code adapté du canevas serveur mono-threadé en utilisant
            // l'attribut data pour traiter les requêtes.
    }

        // etc. avec les autres méthodes processRequestX()
}

