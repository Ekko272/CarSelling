/**
 * CIS 35B Assignment 1: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Util;

import Model.Automobile;
import Model.OptionSet;

import java.io.*;
public class Util {
    private String filePath;
    public Util(){}
    public Util(String f){
        filePath = f;
    }
    public Automobile readFile(String fileName){
        Automobile automotive = new Automobile();
        int numOfOptionSets;
        int numOfOptions;
        String name;
        double price;
        int counter = 0;
        int optionCounter = 0;
        try {
            //FileReader and BufferedReader for standard file operation
            FileReader file = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(file);
            automotive.setName(buff.readLine());//set the first line in txt to automotive name
            automotive.setBasePrice(Double.parseDouble(buff.readLine()));//set the second line in txt to automotive basePrice
            numOfOptionSets = Integer.parseInt(buff.readLine());//set the third line in txt to the # of OptionSet
            automotive.openSpaceForOptionSet(numOfOptionSets);//open space for OptionSet Array
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line.equals("")){
                    name = buff.readLine();
                    numOfOptions = Integer.parseInt(buff.readLine());
                    automotive.setOpset(counter,name,numOfOptions);
                    counter++;
                    optionCounter = 0;
                }
                //The txt file ends with "#"
                else if (line.equals("#")){
                    eof = true;
                    System.out.println("Reading completed!");
                }
                else {
                    name = line;
                    price = Double.parseDouble(buff.readLine());
                    OptionSet optionSet = automotive.getOptionSet(counter - 1);
                    automotive.setOption(optionCounter, optionSet, name, price);
                    optionCounter++;
                }
            }
            buff.close();
        } catch (IOException e) {
            System.out.println("Error " + e.toString());
        }
        return automotive;
    }
    public void serializeAutomotive(Automobile automotive) throws IOException {
        ObjectOutputStream os = null;
        try {
            //Using StringBuilder to build the absolute file path to output .ser file
            //The file name is automotive name + .ser
            String prePath = "/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/";
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(prePath);
            stringBuilder.append(automotive.getName());
            stringBuilder.append(".ser");

            String outputPath = stringBuilder.toString();
            os = new ObjectOutputStream(new FileOutputStream(outputPath));
            os.writeObject(automotive);
            System.out.printf("Serialized object written to file: %s\n", outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    //Deserializing method that reads the .ser file and return the object
    public Automobile deserializeAutomotive(String name) throws IOException {
        ObjectInputStream os = null;
        Automobile automotive;
        String prePath = "/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/";
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(prePath);
        stringBuilder.append(name);
        stringBuilder.append(".ser");

        String inputPath = stringBuilder.toString();
        try
        {
            String fileName = prePath + name + ".ser";
            os = new ObjectInputStream(new FileInputStream(inputPath));
            automotive = (Automobile) os.readObject();
            System.out.printf("The file "+name+".ser is deserialized.\n");
            return automotive;
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (os!=null) {
                os.close();
            }
        }

    }
}