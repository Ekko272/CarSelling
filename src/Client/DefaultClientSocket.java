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
                    sendOutput(toServer);//request config
                    fromServer = in.readObject();//ArrayList of all available models
                    toServer = clientProtocol.promptAllModelsAndChose(fromServer);
                    sendOutput(toServer);//send the model name that client choose
                    fromServer = in.readObject();//Automobile instance received from server

                    if(clientProtocol.isAutomobile(fromServer)) {
                        Automobile auto = (Automobile) fromServer;
                        System.out.println("Auto: " + auto.getName() + " received from server. \nPress [ENTER] to start config it");
                        stdIn.readLine();
                        clientProtocol.configureAuto(fromServer);
                        System.out.println("Press any key to get back to menu");
                        toServer = stdIn.readLine();
                    }
                    else{
                        System.out.println("Auto does not exist, exiting...");
                        System.exit(1);
                    }
                }
                else if(toServer.toString().equals("0")){
                    sendOutput(toServer);
                    sendOutput(toServer);
                    sleep(1000);
                    closeConnection();
                    System.out.println("I'll see you next time, bye bye :)");
                    break;
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

    public void closeConnection() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (stdIn != null) {
                stdIn.close();
            }
            if (sock != null) {
                sock.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        establishConnection();
        handleConnection();
    }


}
