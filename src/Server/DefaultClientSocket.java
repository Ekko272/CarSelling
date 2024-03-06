package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DefaultClientSocket extends Thread{

    private Socket clientSocket;
    private boolean stop;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final String menu = "\nEnter 1 to upload a new Automobile\n"
            + "Enter 2 to configure an Automobile\n"
            + "Enter 0 to terminate connection\n";

    public DefaultClientSocket(){}
    public DefaultClientSocket(Socket clienSocket){
        this.clientSocket = clienSocket;
        this.stop = false;
    }

    public void handleConnection() {
        while (!stop) {
            try {
                System.out.println("Connected!");
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
                Object fromClient;
                sendOutput(menu);
                BuildCarModelOptions bcmo = new BuildCarModelOptions();

                while ((fromClient = in.readObject()) != null) {

                    if (fromClient.toString().equals("1")) {
                        sendOutput("Provide the file path here:");
                        fromClient = in.readObject();
                        bcmo.setState(1);
                        Object toClient = bcmo.processRequest(fromClient);
                        sendOutput(toClient.toString());

                    }

                    if (fromClient.toString().equals("2")) {
                        bcmo.setState(2);
                        Object toClient = bcmo.getAllExistedModel();//ArrayList<String>
                        sendOutput(toClient);
                        fromClient = in.readObject();
                        sendOutput(bcmo.processRequest(fromClient));
                        //Write the client asked auto to client

                    }

                    if (fromClient.toString().equals("0") && fromClient.toString().equalsIgnoreCase("n")) {
                        System.out.println("Disconnecting, bye bye...");
                        out.close();
                        in.close();
                        clientSocket.close();
                        stop = true;
                        break;
                    }
                    Object tempIgonre = in.readObject();//ignore the client's "Press any key to show the menu"
                    sendOutput(menu);
                }

            } catch (Exception e) {
                System.err.println("Error establishing client connection");
                System.exit(1);
            }
        }

    }

    public void sendOutput(Object obj) {
        try {
            out.writeObject(obj);
        }
        catch (IOException e) {
            System.err.println("Error returning output to client ... ");
            System.exit(1);
        }
    }

    @Override
    public void run(){
        handleConnection();
    }


}
