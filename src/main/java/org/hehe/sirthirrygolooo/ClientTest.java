package org.hehe.sirthirrygolooo;

import com.sun.tools.jconsole.JConsoleContext;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class PositionClient  {

    public static void main(String []args) {

        BufferedReader br = null; // pour lire du texte sur la socket
        PrintStream ps = null; // pour écrire du texte sur la socket
        String line = "NULL";
        Socket sock = null;
        int port = -1;
        String userID = "2";

        if (args.length !=2 ) {
            System.out.println("usage: EchoClient ip_server port ");
            System.exit(1);
        }

        try {
            port = Integer.parseInt(args[1]); // récupération du port sous forme int
            sock = new Socket(args[0],port); // création socket client et connexion au serveur donné en args[0]
        }
        catch(IOException e) {
            System.out.println("problème de connexion au serveur : "+e.getMessage());
            System.exit(1);
        }

        try {
            br = new BufferedReader(new InputStreamReader(sock.getInputStream())); // création flux lecture lignes de texte
            ps = new PrintStream(sock.getOutputStream()); // création flux écriture lignes de texte




            userID = br.readLine();
            System.out.println("User id : " + userID);


            ps.println(userID+" reactionTime 2.2");

            System.out.println(br.readLine());


            ps.println(userID+" reactionTime 2.5");

            System.out.println(br.readLine());

            Scanner sc = new Scanner(System.in);

            String cmd = "a";
            while(!cmd.isEmpty()) {
                cmd = sc.nextLine();
                ps.println(userID+" reactionTime 2.5");

                System.out.println(br.readLine());
            }

            br.close();
            ps.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}