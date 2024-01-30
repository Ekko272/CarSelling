/**
 * CIS 35B Assignment 1: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Exception.AutoException;

public class Driver {
    public static void main(String[] args) throws AutoException {
        /*
        The Model name is missing intentionally on the text file for testing the fixing method
        */

        BuildAuto newAuto = new BuildAuto();
        newAuto.setUtil("This is a wrong file path....");

        newAuto.buildAuto();
        newAuto.printAuto();


    }

}