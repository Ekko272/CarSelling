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

    public DefaultServerSocket(int port){
        this.port = port;
        try{
            this.server = new ServerSocket(port);
        }catch (Exception e){
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
    }

    @Override
    public void run(){
        Socket clientSocket = null;
        boolean stop = false;
        while(!stop){
            try{
                clientSocket = server.accept();
                System.out.println("Connected!");
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                Object fromClient;
                out.writeObject(menu);
                BuildCarModelOptions bcmo = new BuildCarModelOptions();

                while((fromClient = in.readObject()) != null){

                    if(fromClient.toString().equals("1")){
                        out.writeObject("Provide the file path here:");
                        fromClient = in.readObject();
                        bcmo.setState(1);
                        Object toClient = bcmo.processRequest(fromClient);
                        out.writeObject(toClient.toString());

                    }

                    if(fromClient.toString().equals("2")){
                        bcmo.setState(2);
                        Object toClient = bcmo.getAllExistedModel();//ArrayList<String>
                        out.writeObject(toClient);
                        fromClient = in.readObject();
                        out.writeObject(bcmo.processRequest(fromClient));
                        //Write the client asked auto to client

                    }

                    if(fromClient.toString().equals("0")){
                        System.out.println("Server turning down, bye bye...");
                        out.close();
                        in.close();
                        clientSocket.close();
                        server.close();
                        stop = true;
                        break;
                    }
                    Object tempIgonre = in.readObject();//ignore the client's "Press any key to show the menu"
                    out.writeObject(menu);


                }

            }catch (Exception e){
                System.err.println("Error establishing client connection");
                System.exit(1);
            }
        }




    }


}
