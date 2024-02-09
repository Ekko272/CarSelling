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

    private Util util;

    public ProxyAutomobile(){
        LinkedHashMapAutos lhmAutos = new LinkedHashMapAutos();
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
        Automobile a1 = util.readFile();
        lhmAutos.addAuto(a1.getName(), a1);
    }
    public void buildAuto() throws AutoException {
        Automobile a1 = util.readFile();
        lhmAutos.addAuto(a1.getName(), a1);
    }
    public void printAuto(String ModelName)throws AutoException{
        lhmAutos.getAutoByName(ModelName).print();
    }

    //TODO: Change all these a1 into lhmAutos
    public void updateOptionSetName(String ModelName, String OptionSetName, String newName)throws AutoException{
        a1.updateOptionSet(OptionSetName, newName);
    }
    public void updateOptionPrice(String ModelName, String OptionName, String Option, double newPrice)throws AutoException{
        a1.updateOptionPrice(OptionName, Option, newPrice);
    }

    public synchronized void addAuto(String key, Automobile value){
        this.lhmAutos.addAuto(key, value);
    }
    public synchronized void deleteAutoByName(String name){
        this.lhmAutos.deleteAutoByName(name);
    }
    public synchronized Automobile getAutoByName(String name){
        return this.lhmAutos.getAutoByName(name);
    }
    public synchronized void printAll(){
        this.lhmAutos.printAll();
    }

}
