/**
 * CIS 35B Assignment 5: ClientDriver.java
 *
 * @author Xuanyu Liu
 */
package Driver;
import Client.*;

import java.util.ArrayList;

//This class represents client side
public class ClientDriver {
    public static void main(String[] args) {
        ArrayList<String> carModelList;
        DefaultClientSocket client = new DefaultClientSocket("127.0.0.1", 4455);

        client.establishConnection();
        System.out.println("Establishing connection");
        client.recieveObject();//Menu
        System.out.println("recieveing Menu");
        client.sendOutput("2");
        System.out.println("Sending 2");

        Object fromServer = client.recieveObject();
        System.out.println("recieving ArrayList");
        if(fromServer instanceof ArrayList)
        {
            carModelList = (ArrayList<String>) fromServer;
        }
        else {
            System.err.println("Something wrong with talking to server");
        }
        client.sendOutput("0");
        System.out.println("send 0");
        client.sendOutput("0");
        System.out.println("send 0");
        client.closeConnection();

    }

}
