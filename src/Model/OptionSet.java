/**
 * CIS 35B Assignment 1: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Model;

import java.io.Serializable;

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
    private Option opt [];
    private String name;
    protected OptionSet(){}
    protected OptionSet(String n, int size)
    {
        name = n;
        opt = new Option[size];
        for(int i = 0; i < size; i++){
            opt[i] = new Option();
        }
    }

    protected Option[] getOpt() {
        return opt;
    }

    protected Option getOptByIndex(int i){return opt[i];}

    protected void setOpt(Option[] opt) {
        this.opt = opt;
    }

    protected void setOpt(String name, double price, int index){
        this.opt[index] = new Option(name, price);
    }
    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }
    protected void print(){
        System.out.printf(name + "\n");
        for(int i = 0; i < opt.length; i++){
            opt[i].print();
        }
    }

}
