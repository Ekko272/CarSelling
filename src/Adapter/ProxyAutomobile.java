package Adapter;
import Util.*;
import Model.*;
import Exception.AutoException;
public abstract class ProxyAutomobile {
    private static Automobile a1;
    public void buildAuto(String fileName) throws AutoException {
        Util util = new Util(fileName);
        a1 = util.readFile();
    }
    public void printAuto(String ModelName)throws AutoException{
        a1.print(ModelName);
    }
    public void updateOptionSetName(String ModelName, String OptionSetName, String newName)throws AutoException{
        a1.updateOptionSet(OptionSetName, newName);
    }
    public void updateOptionPrice(String ModelName, String OptionName, String Option, double newPrice)throws AutoException{
        a1.updateOptionPrice(OptionName, Option, newPrice);
    }
}
