/**
 * CIS 35B Assignment 1: Automotive.java
 *
 * @author Xuanyu Liu
 */

package Model;

import java.io.Serializable;

public class Automobile implements Serializable {
    private String name;
    private double basePrice;
    private OptionSet opset[];

    public Automobile(){}
    public Automobile(int size, String n, Double b) {
        name = n;
        basePrice = b;
        opset = new OptionSet[size];
        for(int i = 0; i < size; i++){
            opset[i] = new OptionSet();
        }
    }
    //Print methods
    public void printModel(){
        System.out.println("Name: " + name);
    }

    public void printBasePrice(){
        System.out.printf("Base Price: " + basePrice + "\n");
    }

    public void printAllOptionSet(){
        for(int i = 0; i < opset.length; i++){
            System.out.printf(opset[i].getName() + "\n");
        }
    }

    public void printOneOptionSet(int index){
        opset[index].print();
    }

    public void print(){
        System.out.printf("Name: " + name + "\n");
        System.out.printf("\n");
        System.out.printf("Base Price: " + basePrice + "\n");
        System.out.printf("\n");
        for(int i = 0; i < opset.length; i++){
            printOneOptionSet(i);
            System.out.printf("\n");
        }
    }

    public void print(String modelName){
        System.out.printf("Name: " + modelName + "\n");
        System.out.printf("\n");
        System.out.printf("Base Price: " + basePrice + "\n");
        System.out.printf("\n");
        for(int i = 0; i < opset.length; i++){
            printOneOptionSet(i);
            System.out.printf("\n");
        }
    }

    //Open space for array
    public void openSpaceForOptionSet(int size){
        opset = new OptionSet[size];
        for(int i = 0; i < size; i++){
            opset[i] = new OptionSet();
        }
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public OptionSet getOptionSet(int i){
        try {
            return opset[i];
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setOpset(int index, OptionSet Op){
        opset[index] = Op;
    }

    public void setOpset(int index, String name, int optSize){
        opset[index] = new OptionSet(name, optSize);
    }

    public void setOption(int index, OptionSet Op, String name, double price){
        Op.setOpt(name, price, index);
    }

    //Find option set with given name, return the index number of it if found, return -1 if not
    public int findOptionSetWithName(String name){
        for(int i = 0; i < opset.length; i++){
            if (opset[i].getName().equals(name)){
                System.out.printf("Found [" + name + "] at index " + i + ".\n");
                return i;
            }
        }
        System.out.printf("[" + name + "] does not exist.\n");
        return -1;
    }

    // Find option with given name, if found, return an array which stores the index of OptionSet and the index of the option to find
    // return null if option doesn't exist
    public int[] findOptionWithName(String name){
        int indexArr[] = new int[2];
        for(int i = 0; i < opset.length; i++){
            for(int j = 0; j < opset[i].getOpt().length; j++){
                if(opset[i].getOptByIndex(j).getName().equals(name)){
                    System.out.printf("Found [" + name + "] in option set " + opset[i].getName() + " at index " + j + ".\n");
                    indexArr[0] = i;
                    indexArr[1] = j;
                    return indexArr;
                }
            }
        }
        System.out.printf("[" + name + "] does not exist.\n");
        return null;
    }

    public int findOptionWithNameWithKnownOptionSet(int optSetIndex, String name){
        for(int i = 0; i < opset[optSetIndex].getOpt().length; i++){
            if(opset[optSetIndex].getOptByIndex(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    // Update methods with three overload methods, they return 1 if updating succeed and return -1 if failed
    public int updateOptionSet(String name, String newName){
        int index = findOptionSetWithName(name);
        if (index != -1){
            opset[index].setName(newName);
            return 1;
        }
        return -1;
    }


    public int updateOption(String name, String newName, double newPrice){
        int index[] = findOptionWithName(name);
        if (index != null){
            opset[index[0]].setOpt(newName, newPrice, index[1]);
            System.out.printf("Successfully updated!\n");
            return 1;
        }
        System.out.printf("Update failed!\n");
        return -1;
    }

    public int updateOption(String name, String newName){
        int index[] = findOptionWithName(name);
        if (index != null){
            opset[index[0]].getOptByIndex(index[1]).setName(newName);
            System.out.printf("Successfully updated!\n");
            return 1;
        }
        System.out.printf("Update failed!\n");
        return -1;
    }
    public int updateOption(String name,double newPrice){
        int index[] = findOptionWithName(name);
        if (index != null){
            opset[index[0]].getOptByIndex(index[1]).setPrice(newPrice);
            System.out.printf("Successfully updated!\n");
            return 1;
        }
        System.out.printf("Update failed!\n");
        return -1;
    }

    public int updateOptionPrice(String OptionSetName, String OptionName, double newPrice){
        int index = findOptionSetWithName(OptionSetName);
        if (index != -1){
            int optIndex = findOptionWithNameWithKnownOptionSet(index, OptionName);
            if (optIndex != -1) {
                opset[index].getOptByIndex(optIndex).setPrice(newPrice);
                return 1;
            }
        }
        return -1;
    }

    //Delete methods, set the element to delete to null for now
    public int deleteOptionSet(String name){
        int index = findOptionSetWithName(name);
        if(index == -1) {
            System.out.printf("Option set doesn't exist!\n");
            return -1;
        }
        opset[index].setName("Null");
        for(int i = 0; i < opset[index].getOpt().length; i++){
            opset[index].getOptByIndex(i).setName("Null");
            opset[index].getOptByIndex(i).setPrice(0);
            System.out.printf("Delete succeed!\n");
        }
        return 1;
    }

    public int deleteOptionSet(int index){
        try {
            opset[index].setName(null);
            for (int i = 0; i < opset[index].getOpt().length; i++) {
                opset[index].getOptByIndex(i).setName(null);
                opset[index].getOptByIndex(i).setPrice(0);
                System.out.printf("Delete succeed!\n");
            }
            return 1;
        }catch (NullPointerException e){
            e.toString();
            return -1;
        }

    }

    public int deleteOption(int x, int y){
        try {
            opset[x].getOptByIndex(y).setName(null);
            opset[x].getOptByIndex(y).setPrice(0);
            return 1;
        }catch (NullPointerException e) {
            e.toString();
            return -1;
        }
    }

    public int deleteOption(String name){
        int[] indexArr = findOptionWithName(name);
        if (indexArr == null){
            System.out.printf("Option doesn't exist!\n");
            return -1;
        }
        opset[indexArr[0]].getOptByIndex(indexArr[1]).setName(null);
        opset[indexArr[0]].getOptByIndex(indexArr[1]).setPrice(0);
        System.out.printf("Delete succeed!\n");
        return 1;
    }
}