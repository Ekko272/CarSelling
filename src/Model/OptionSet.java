/**
 * CIS 35B Assignment 3: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable {
    //Inner class
    class Option implements Serializable {
        private String name;
        private double price;
        protected Option(){}
        protected Option(String name, double price){
            this.name = name;
            this.price = price;
        }
        protected void setName(String n){name = n;}
        protected String getName(){return name;}
        protected void setPrice(double n){price = n;}
        protected double getPrice(){return price;}
        protected void print(){
            System.out.printf("Name: " + name +"\n");
            System.out.printf("Price: $" + price + "\n");
        }

    }


    private ArrayList<Option> opt;
    private String name;
    private Option choice;
    public Option getOptionChoice(){
        return this.choice;
    }
    public void setOptionChoice(String optionName){
        for(Option option : opt){
            if(optionName == option.getName()){
                this.choice = option;
            }
        }
    }
    protected OptionSet(){
        this.opt = new ArrayList<>();
    }
    protected OptionSet(String n)
    {
        name = n;
        opt = new ArrayList<>();
    }

    public void setOpt(ArrayList<Option> opt) {
        this.opt = opt;
    }

    protected ArrayList<Option> getOpt() {
        return opt;
    }

    protected Option getOptByIndex(int i){return opt.get(i);}


    protected void setOpt(String name, double price, int index){
        opt.set(index, new Option(name, price));
    }

    protected void addOpt(String name, double price, int index){
        opt.add(index, new Option(name, price));
    }
    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
    protected void print(){
        System.out.printf(name + "\n");
        for(Option op : opt){
            System.out.println(op.getName() + "  $" + op.getPrice());
        }
    }

}
