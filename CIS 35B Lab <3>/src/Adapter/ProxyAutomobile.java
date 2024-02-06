/**
 * CIS 35B Assignment 2: ProxyAutomobile.java
 *
 * @author Xuanyu Liu
 */

package Adapter;
import Util.*;
import Model.*;
import Exception.AutoException;

public abstract class ProxyAutomobile {
    private static Automobile a1;
    private Util util;


    public ProxyAutomobile(){
        this.util = new Util();
    }
    public ProxyAutomobile(String fileName){
        this.util = new Util((fileName));
    }
    public static Automobile getA1() {
        return a1;
    }
    public static void setA1(Automobile a1) {
        ProxyAutomobile.a1 = a1;
    }
    public Util getUtil() {
        return util;
    }
    public void setUtil(Util util) {
        this.util = util;
    }
    public void setUtil(String fileName)
    {
        this.util.setFilePath(fileName);
    }


    public void buildAuto(String fileName) throws AutoException {
        util.setFilePath(fileName);
        a1 = util.readFile();
    }
    public void buildAuto() throws AutoException {
        a1 = util.readFile();
    }
    public void printAuto(String ModelName)throws AutoException{
        a1.print(ModelName);
    }
    public void printAuto()throws AutoException{
        a1.print();
    }
    public void updateOptionSetName(String ModelName, String OptionSetName, String newName)throws AutoException{
        a1.updateOptionSet(OptionSetName, newName);
    }
    public void updateOptionPrice(String ModelName, String OptionName, String Option, double newPrice)throws AutoException{
        a1.updateOptionPrice(OptionName, Option, newPrice);
    }
}
