/**
 * CIS 35B Assignment 1: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Model.Automobile;
import Util.Util;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        Util util = new Util();
        Automobile FordZTW = util.readFile("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt");
        FordZTW.print();
        util.serializeAutomotive(FordZTW);
        Automobile newFordZTW = util.deserializeAutomotive("Ford's Focus Wagon ZTW");
        newFordZTW.print();
    }
}