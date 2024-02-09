/**
 * CIS 35B Assignment 3: Driver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Adapter.ProxyAutomobile;
import Exception.AutoException;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Driver {
    public static void main(String[] args) throws AutoException {
        CreateAuto newAuto = new BuildAuto();
        newAuto.buildAuto("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt");

        newAuto.printAuto("ZWS");

    }

}