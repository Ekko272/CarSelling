/**
 * CIS 35B Assignment 2: Driver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Exception.AutoException;

public class Driver {
    public static void main(String[] args) throws AutoException {
        /*
        Note:
        The Model name is missing intentionally on the text file for testing the fixing method
        */
        BuildAuto newAuto = new BuildAuto();
        newAuto.setUtil("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt");

        newAuto.buildAuto();
        newAuto.printAuto();
        newAuto.updateOptionSetName("zws", "Color", "NewNameColor");
        newAuto.printAuto();

    }

}