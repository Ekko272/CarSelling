/**
 * CIS 35B Assignment 3: Automotive.java
 *
 * @author Xuanyu Liu
 */

/*
Comments for the added synchronized methods:
*/
package Model;
/*
I have made the methods in this class synchronized since all the operations for ProxyAutomobile starts from here,
and since the OptionSet class is protected, the best solution for me is making methods of Automobile class synchronized.
Synchronized avoid data corruption because it only allows one method access the data at a time when there are multiple thread running.
My implement of EditOption allows Updating, Adding and Deleting, so these three methods have to be synchronized.
And in case of the future using of the other methods, I have made them synchronized as well.
 */
import Adapter.ChoiceAuto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Automobile implements Serializable, ChoiceAuto {
    private String name;
    private String make;
    private String model;
    private double basePrice;
    private ArrayList<OptionSet> opset;
    private ArrayList<OptionSet.Option> choice;

    public Automobile(){
        this.opset = new ArrayList<>();
        this.choice = new ArrayList<>();
    }
    public Automobile(int size, String n, Double b) {
        name = n;
        basePrice = b;
        opset = new ArrayList<OptionSet>(size);
    }
    //Print methods
    public void printModel(){
        System.out.println("Name: " + name);
    }
    public void printBasePrice(){
        System.out.printf("Base Price: " + basePrice + "\n");
    }
    public void printAllOptionSet(){
        for(OptionSet optionSet : opset){
            System.out.printf(optionSet.getName() + "\n");
        }
    }
    public void printOneOptionSet(int index){
        opset.get(index).print();
    }
    public void print(){
        System.out.printf("Name: " + name + "\n");
        System.out.printf("\n");
        System.out.printf("Base Price: " + basePrice + "\n");
        System.out.printf("\n");
        for(int i = 0; i < opset.size(); i++){
            printOneOptionSet(i);
            System.out.printf("\n");
        }
    }
    //Getters and setters
    public synchronized String getName() {
        return name;
    }
    public synchronized void setName(String name) {
        this.name = name;
    }
    public synchronized double getBasePrice() {
        return basePrice;
    }
    public synchronized void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    public synchronized OptionSet getOptionSet(int i){
        try {
            return opset.get(i);
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
    public synchronized void setOpset(int index, OptionSet Op){
        opset.set(index, Op);
    }
    public synchronized void setOpset(int index, String name){
        opset.set(index, new OptionSet(name));
    }
    public synchronized void addOpset(int index, String name){
        opset.add(index, new OptionSet(name));
    }
    public synchronized void setOption(int index, OptionSet Op, String name, double price){
        Op.setOpt(name, price, index);
    }
    public synchronized void addOption(int index, OptionSet Op, String name, double price){
        Op.addOpt(name, price, index);
    }
    public synchronized void addOption(OptionSet Op, String name, double price){
        Op.addOpt(name, price);
    }
    public synchronized String getMake() {
        return make;
    }
    public synchronized void setMake(String make) {
        this.make = make;
    }
    public synchronized String getModel() {
        return model;
    }
    public synchronized void setModel(String model) {
        this.model = model;
    }

    @Override
    public synchronized String getOptionChoice(String opSetName){
        for(OptionSet optionSet : opset){
            if(Objects.equals(optionSet.getName(), opSetName)){
                return optionSet.getOptionChoice().getName();
            }
        }
            return null;
    }
    @Override
    public synchronized void setOptionChoice(String setName, String optionName){
        for(OptionSet optionSet : opset){
            if(Objects.equals(optionSet.getName(), setName)){
                for(OptionSet.Option option : optionSet.getOpt()){
                    if(Objects.equals(option.getName(), optionName)){
                        this.choice.add(option);
                        optionSet.setOptionChoice(option.getName());
                    }
                }
            }
        }
    }
    @Override
    public synchronized double getOptionChoicePrice(String optionName){
        for(OptionSet optionSet : opset){
            for(OptionSet.Option option : optionSet.getOpt()){
                if(Objects.equals(option.getName(), optionName)){
                    return option.getPrice();
                }
            }
        }
        return -1;
    }

    @Override
    public synchronized double getTotalPrice() {
        double totalPrice = 0;
        for(OptionSet optionSet : opset){
            totalPrice += optionSet.getOptionChoice().getPrice();
        }
        return totalPrice;
    }

    //Find option set with given name, return the index number of it if found, return -1 if not
    public synchronized int findOptionSetWithName(String name){
        for(int i = 0; i < opset.size(); i++){
            if (opset.get(i).getName().equals(name)){
                System.out.printf("Found [" + name + "] at index " + i + ".\n");
                return i;
            }
        }
        System.out.printf("[" + name + "] does not exist.\n");
        return -1;
    }

    // Find option with given name, if found, return an array which stores the index of OptionSet and the index of the option to find
    // return null if option doesn't exist
    public synchronized int[] findOptionWithName(String name){
        int []indexArr = new int[2];
        for(int i = 0; i < opset.size(); i++){
            for(int j = 0; j < opset.get(i).getOpt().size(); j++){
                if(opset.get(i).getOptByIndex(j).getName().equals(name)){
                    System.out.printf("Found [" + name + "] in option set " + opset.get(i).getName() + " at index " + j + ".\n");
                    indexArr[0] = i;
                    indexArr[1] = j;
                    return indexArr;
                }
            }
        }
        System.out.printf("[" + name + "] does not exist.\n");
        return null;
    }

    public synchronized int findOptionWithNameWithKnownOptionSet(int optSetIndex, String name){
        for(int i = 0; i < opset.get(optSetIndex).getOpt().size(); i++){
            if(opset.get(optSetIndex).getOptByIndex(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public synchronized int findOptionSetWithKnownOption(String optionName){
        for(int i = 0; i < opset.size(); i++){
            for(int j = 0; j <opset.get(i).getOpt().size(); j++) {
                if (opset.get(i).getOpt().get(j).getName().equals(optionName)) {
                    System.out.printf("Found OptionSet [" + name + "] at index " + i + ".\n");
                    return i;
                }
            }
        }
        System.out.printf("OptionSet [" + name + "] does not exist.\n");
        return -1;

    }

    // Update methods with three overload methods, they return 1 if updating succeed and return -1 if failed
    public synchronized int updateOptionSet(String name, String newName){
        int index = findOptionSetWithName(name);
        if (index != -1){
            opset.get(index).setName(newName);
            return 1;
        }
        return -1;
    }

    public synchronized int updateOption(String name, String newName, double newPrice){
        int []index = findOptionWithName(name);
        if (index != null){
            opset.get(index[0]).setOpt(newName, newPrice, index[1]);
            System.out.printf("Successfully updated!\n");
            return 1;
        }
        System.out.printf("Update failed!\n");
        return -1;
    }

    public synchronized int updateOption(String name, String newName){
        int index[] = findOptionWithName(name);
        if (index != null){
            opset.get(index[0]).getOptByIndex(index[1]).setName(newName);
            System.out.printf("Successfully updated!\n");
            return 1;
        }
        System.out.printf("Update failed!\n");
        return -1;
    }
    public synchronized int updateOption(String name,double newPrice){
        int index[] = findOptionWithName(name);
        if (index != null){
            opset.get(index[0]).getOptByIndex(index[1]).setPrice(newPrice);
            System.out.printf("Successfully updated!\n");
            return 1;
        }
        System.out.printf("Update failed!\n");
        return -1;
    }

    public synchronized int updateOptionPrice(String OptionSetName, String OptionName, double newPrice){
        int index = findOptionSetWithName(OptionSetName);
        if (index != -1){
            int optIndex = findOptionWithNameWithKnownOptionSet(index, OptionName);
            if (optIndex != -1) {
                opset.get(index).getOptByIndex(optIndex).setPrice(newPrice);
                return 1;
            }
        }
        return -1;
    }

    //Delete methods, set the element to delete to null for now
    public synchronized int deleteOptionSet(String name){
        int index = findOptionSetWithName(name);
        if(index == -1) {
            System.out.printf("Option set doesn't exist!\n");
            return -1;
        }
        opset.get(index).setName("Null");
        for(int i = 0; i < opset.get(index).getOpt().size(); i++){
            opset.get(index).getOptByIndex(i).setName("Null");
            opset.get(index).getOptByIndex(i).setPrice(0);
            System.out.printf("Delete succeed!\n");
        }
        return 1;
    }

    public synchronized int deleteOptionSet(int index){
        try {
            opset.get(index).setName(null);
            for (int i = 0; i < opset.get(index).getOpt().size(); i++) {
                opset.get(index).getOptByIndex(i).setName(null);
                opset.get(index).getOptByIndex(i).setPrice(0);
                System.out.printf("Delete succeed!\n");
            }
            return 1;
        }catch (NullPointerException e){
            e.toString();
            return -1;
        }

    }

    public synchronized int deleteOption(int x, int y){
        try {
            opset.get(x).getOpt().remove(y);
            return 1;
        }catch (NullPointerException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public synchronized int deleteOption(String name) {
        int []indexArr = findOptionWithName(name);
        if (indexArr == null){
            System.out.println("No such element.");
            return -1;
        }
        opset.get(indexArr[0]).getOpt().remove(indexArr[1]);
        System.out.printf("------------------------------------------\n");
        System.out.printf(" -Option [" + name + "] deleting succeed -\n");
        System.out.printf("------------------------------------------\n");

        return 1;
    }

    public synchronized void addOption(String OpSetName, String name, double price) {
        int index = findOptionSetWithName(OpSetName);
        this.opset.get(index).addOpt(name, price);
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf(" -Option [" + name + ": $ " + price + "] adding succeed -\n");
        System.out.printf("---------------------------------------------------------\n");
    }


}