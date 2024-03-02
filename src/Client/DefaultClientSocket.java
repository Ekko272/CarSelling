package Client;

import Model.Automobile;

import java.io.*;
import java.net.Socket;

public class DefaultClientSocket extends Thread{

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private BufferedReader stdIn;
    private Socket sock;
    private String strHost;
    private int port;
    private CarModelOptionsIO clientFTP;
    private SelectCarOptions clientProtocol;

    public DefaultClientSocket(String strHost, int iPort) {
        this.strHost = strHost;
        this.port = iPort;
    }

    public void establishConnection(){
        try{
            this.sock = new Socket(strHost, port);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            clientFTP = new CarModelOptionsIO();
            clientProtocol = new SelectCarOptions();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleConnection(){
        Object fromServer, toServer = null;
        try{
            while((fromServer = in.readObject()) != null){
                System.out.println(fromServer.toString());
                System.out.println("Response to server: ");
                toServer = stdIn.readLine();
                if (toServer.toString().contains(".properties")) {
                    toServer = clientFTP.loadPropsFile(toServer.toString());

                }
                else if (toServer.toString().contains(".txt")) {
                    toServer = clientFTP.loadTextFile(toServer.toString());

                }
                else if (toServer.toString().equals("2")){
                    sendOutput(toServer);
                    fromServer = in.readObject();
                    toServer = clientProtocol.promptAllModelsAndChose(fromServer);
                    sendOutput(toServer);
                    fromServer = in.readObject();//Automobile instance

                    if(clientProtocol.isAutomobile(fromServer)) {

                        Automobile auto = (Automobile) fromServer;
                        System.out.println("Auto: " + auto.getName() + " received from server. \nPress any key to start config it");
                        toServer = stdIn.readLine();
                        clientProtocol.configureAuto(fromServer);
                        //configure it
                    }
                }
                sendOutput(toServer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendOutput(Object obj){
        try{
            out.writeObject(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        establishConnection();
        handleConnection();
    }


}
