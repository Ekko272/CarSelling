/**
 * CIS 35B Assignment 5: ServerDriver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Adapter.ModifyLHMAuto;
import Exception.AutoException;
import Server.*;

//This driver class represents the server side. It adds some cars into lhmautos before the server starts
public class ServerDriver {
    public static void main(String[] args) throws AutoException, InterruptedException {
        //Initialize static object LHMAutos.
        //This method only call once in the whole program
        ModifyLHMAuto initialize = new BuildAuto();
        initialize.initializeLHMAutos();

        CreateAuto auto = new BuildAuto();
        auto.buildAuto("C:\\Users\\17212\\OneDrive\\桌面\\CIS35B\\CarSelling\\src\\car2.properties", "properties");
        auto.buildAuto("C:\\Users\\17212\\OneDrive\\桌面\\CIS35B\\CarSelling\\src\\car3.properties", "properties");



        DefaultServerSocket server = new DefaultServerSocket(4455);
        server.start();




    }

}