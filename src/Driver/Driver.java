/**
 * CIS 35B Assignment 4: Driver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Exception.AutoException;
import Scale.EditOptions;

public class Driver {
    public static void main(String[] args) throws AutoException, InterruptedException {
        BuildAuto newAuto = new BuildAuto();
        newAuto.buildAuto("C:\\Users\\17212\\OneDrive\\桌面\\CIS35B\\CarSelling\\src\\info.txt");
        newAuto.printAuto("ZWT");

        //Trying to delete an option that not exist yet
        EditOptions eo1 = new EditOptions(newAuto);
        eo1.setAutoNameToEdit("ZWT");
        eo1.setOptionSetName("Transmission");
        eo1.setOldOptName("New Option to DELETE");//This Option has not being added now
        eo1.setNewOptPrice(222);
        eo1.setOperation(1);// DELETE operation

        EditOptions eo2 = new EditOptions(newAuto);
        eo2.setAutoNameToEdit("ZWT");
        eo2.setOptionSetName("Transmission");
        eo2.setNewOptName("New Option to DELETE"); //Now ADDING this Option
        eo2.setNewOptPrice(222);
        eo2.setOperation(2);// ADD operation


        Thread t1 = new Thread(eo1);
        Thread t2 = new Thread(eo2);

        //Two threads runs simultaneously.
        //If Delete happens before Add, Delete is going to wait for Add to complete.
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        newAuto.printAuto("ZWT");

    }

}