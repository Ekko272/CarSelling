/**
 * CIS 35B Assignment 4: Driver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Adapter.EditOptionInter;
import Exception.AutoException;
import Scale.EditOptions;

public class Driver {
    public static void main(String[] args) throws AutoException, InterruptedException {

        CreateAuto newAuto = new BuildAuto();
        newAuto.buildAuto("C:\\Users\\17212\\OneDrive\\桌面\\CIS35B\\CarSelling\\src\\info.txt");
        newAuto.printAuto("ZWT");

        EditOptionInter editAuto = new BuildAuto();

        EditOptions eo = new EditOptions(editAuto);
        eo.setAutoNameToEdit("ZWT");
        eo.setOldOptName("Manual");
        eo.setNewOptName("Altered By Thread 1");
        eo.setNewOptPrice(8899);
        eo.setOperation(0); //Update operation

        EditOptions eo2 = new EditOptions(editAuto);
        eo2.setAutoNameToEdit("ZWT");
        eo2.setOldOptName("Automatic");
        eo2.setNewOptName("Altered By Thread 2");
        eo2.setNewOptPrice(2225);
        eo2.setOperation(0); //Update operation

        //Start threads
        Thread t1 = new Thread(eo);
        Thread t2 = new Thread(eo2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();


        newAuto.printAuto("ZWT");
        System.out.println("Main method finished");


    }

}