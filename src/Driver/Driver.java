/**
 * CIS 35B Assignment 4: Driver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Adapter.EditOptionInter;
import Adapter.ModifyLHMAuto;
import Exception.AutoException;
import Server.*;
import Client.*;
import Scale.EditOptions;

//Added Comment is at the top of Automobile Class
public class Driver {
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