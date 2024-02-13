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
        newAuto.buildAuto("C:\\Users\\17212\\OneDrive\\桌面\\CIS35B\\CarSelling\\src\\info.txt");
        newAuto.printAuto("ZWT");

        EditOptionInter editAuto = new BuildAuto();
        EditOptions eo = new EditOptions(editAuto);
        eo.run();

    }

}