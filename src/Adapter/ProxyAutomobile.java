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
    private static LinkedHashMapAutos lhmAutos;
    private static Automobile a1;
    private Util util;

    public ProxyAutomobile(){
        lhmAutos = new LinkedHashMapAutos();
        this.util = new Util();
    }
    public ProxyAutomobile(String fileName){
        this.util = new Util((fileName));
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
        lhmAutos.addAuto(a1.getName(), a1);
    }
    public void buildAuto() throws AutoException {
        a1 = util.readFile();
        lhmAutos.addAuto(a1.getName(), a1);
    }
    public void printAuto(String ModelName)throws AutoException{
        lhmAutos.getAutoByName(ModelName).print();
    }

    //TODO: Question: Should these methods be synchronized? (a1 is being "get" by this method)
    public synchronized void updateOptionSetName(String ModelName, String OptionSetName, String newName)throws AutoException{
        a1 = lhmAutos.getAutoByName(ModelName);
        a1.updateOptionSet(OptionSetName, newName);
    }
    public synchronized void updateOptionPrice(String ModelName, String OptionName, String Option, double newPrice)throws AutoException{
        a1 = lhmAutos.getAutoByName(ModelName);
        a1.updateOptionPrice(OptionName, Option, newPrice);
    }

    public void addAuto(String key, Automobile value){
        lhmAutos.addAuto(key, value);
    }
    public void deleteAutoByName(String name){
        lhmAutos.deleteAutoByName(name);
    }
    public Automobile getAutoByName(String name){
        return lhmAutos.getAutoByName(name);
    }
    public void printAll(){
        lhmAutos.printAll();
    }

}
