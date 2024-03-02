/**
 * CIS 35B Assignment 2: ProxyAutomobile.java
 *
 * @author Xuanyu Liu
 */

package Adapter;
import Util.*;
import Model.*;
import Exception.AutoException;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public abstract class ProxyAutomobile {
    private static LinkedHashMapAutos lhmAutos;
    private static Automobile a1;
    private Util util;

    public ProxyAutomobile(){
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

    public void initializeLHMAutos(){
        lhmAutos = new LinkedHashMapAutos();
    }
    public Automobile buildAutoByProps(String fileName){
        util.setFilePath(fileName);
        return util.readProperties();
    }
    public Automobile buildAutoByProps(Properties props){
        return util.readProperties(props);
    }
    public Automobile buildAutoByText(String fileName) throws AutoException {
        util.setFilePath(fileName);
        return util.readFile();
    }

    public void buildAuto(String fileName, String fileType) {
        try {
            if (fileType.equals("properties")) {
                util.setFilePath(fileName);
                a1 = util.readProperties();
                lhmAutos.addAuto(a1.getName(), a1);
            } else if (fileType.equals("txt")) {
                util.setFilePath(fileName);
                a1 = util.readFile();
                lhmAutos.addAuto(a1.getName(), a1);
            } else {
                System.out.println("Error: Invalid File Type.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void buildAuto() throws AutoException {
        a1 = util.readFile();
        lhmAutos = new LinkedHashMapAutos();
        lhmAutos.addAuto(a1.getName(), a1);
    }
    public void printAuto(String ModelName)throws AutoException{
        lhmAutos.getAutoByName(ModelName).print();
    }

    public ArrayList<String> getAllExistedModel(){
        return lhmAutos.getAllModels();
    }
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

    public void addOption(String autoName, String OpSetName, String name, double price){

        lhmAutos.getAutoByName(autoName).addOption(OpSetName, name, price);
    }
    public void deleteOption(String autoName, String optionName){
        lhmAutos.getAutoByName(autoName).deleteOption(optionName);
    }
    public void updateOption(String autoName, String name, String newName, double newPrice){
        lhmAutos.getAutoByName(autoName).updateOption(name, newName, newPrice);
    }

    public void addAutoToLHMAuto(Automobile automobile){
        a1 = automobile;
        lhmAutos.addAuto(a1.getName(), a1);
    }

}
