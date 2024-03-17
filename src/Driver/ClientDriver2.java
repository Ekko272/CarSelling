package Driver;

import Client.DefaultClientSocket;
import Model.Automobile;

public class ClientDriver2 {
    public static void main(String[] args) {
        DefaultClientSocket client = new DefaultClientSocket("127.0.0.1", 4455);

        client.establishConnection();
        System.out.println("Establishing connection");
        client.recieveObject();//Menu
        System.out.println("recieveing Menu");
        client.sendOutput("2");
        System.out.println("Sending 2");

        client.recieveObject();//ArrayList of CarModel
        System.out.println("recieving ArrayList");


        client.sendOutput("BMW M5 Competition");
        System.out.println("send BMW m5");
        Automobile auto = (Automobile)client.recieveObject();
        System.out.println("recieving auto");
        client.sendOutput("0");
        System.out.println("send 0");
        client.sendOutput("0");
        System.out.println("send 0");
        client.closeConnection();
    }
}
