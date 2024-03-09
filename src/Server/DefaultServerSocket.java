package Server;

import Client.SelectCarOptions;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DefaultServerSocket extends Thread{
    private int port;
    private ServerSocket server;
    String menu =  "\nEnter 1 to upload a new Automobile\n"
            + "Enter 2 to configure an Automobile\n"
            + "Enter 0 to terminate connection\n";

    public DefaultServerSocket(int portt){
        this.port = portt;
        try{
            this.server = new ServerSocket(port);
        }catch (Exception e){
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
    }

    @Override
    public void run(){
        System.out.println("Server started, waiting for request...");
        Socket clientSocket = null;
        try{
            clientSocket = server.accept();
        }catch (Exception e){
            System.err.println("Error establishing client connection");
            System.exit(1);
        }
        new DefaultClientSocket(clientSocket).start();





    }


}
