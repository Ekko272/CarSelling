/**
 * CIS 35B Assignment 4: Driver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Adapter.EditOptionInter;
import Exception.AutoException;
import Scale.EditOptions;

public class Driver {
    public static void main(String[] args) throws AutoException, InterruptedException {
        BuildAuto newAuto = new BuildAuto();
        newAuto.buildAuto("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt");
        newAuto.printAuto("ZWT");

        EditOptionInter editAuto = new BuildAuto();
        EditOptions eo = new EditOptions(editAuto);
        eo.setAutoNameToEdit("ZWT");
        eo.setOldOptName("Manual");
        //test here


    }

}