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

        EditOptions eo1 = new EditOptions(newAuto);
        eo1.setAutoNameToEdit("ZWT");
        eo1.setOldName("ABS");
        eo1.setNewName("A CHANGED NAME IN THREAD 1");
        eo1.setNewPrice(2800);
        eo1.setOperation(0);

        EditOptions eo2 = new EditOptions(newAuto);
        eo2.setAutoNameToEdit("ZWT");
        eo2.setOldName("Automatic");
        eo2.setNewName("A CHANGED NAME IN THREAD 2");
        eo2.setNewPrice(888);
        eo2.setOperation(0);

        Thread t1 = new Thread(eo1);
        Thread t2 = new Thread(eo2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        newAuto.printAuto("ZWT");

    }

}