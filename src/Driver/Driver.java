/**
 * CIS 35B Assignment 1: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Model.Automobile;
import Util.Util;
import java.io.IOException;
import Exception.AutoException;

public class Driver {
    public static void main(String[] args) throws AutoException {

try {
    BuildAuto newAuto = new BuildAuto();
    newAuto.buildAuto("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt");
    newAuto.printAuto("ZSW");
}catch (AutoException e){

}
    }
}