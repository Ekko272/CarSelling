/**
 * CIS 35B Assignment 3: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Util;

import Model.Automobile;
import Model.OptionSet;
import Exception.*;
import java.io.*;
import java.util.Properties;

public class Util {
    private String filePath;
    public Util(){}
    public Util(String f){
        filePath = f;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Automobile readFile() throws AutoException{
        Automobile automotive = new Automobile();
        String name;
        String temp = "";
        double price;
        int counter = 0;
        int optionCounter = 0;
        String arr[] = new String[1];
        boolean pass = false;
        boolean problemMet = false;
        do {
            try {
                FileReader file = new FileReader(filePath);
                BufferedReader buff = new BufferedReader(file);
                pass = true;
                automotive.setName(buff.readLine()); //valueHolder for determine AutoException
                try {
                    if (automotive.getName().equals("")) {
                        pass = false;
                        throw new AutoException(1, "Missing Automobile Name.");
                    }
                } catch (AutoException e) {
                    e.fixProblem(1, arr);
                    automotive.setName(arr[0]);
                    pass = true;
                }
                temp = buff.readLine();
                
                try {
                    if (temp.equals("")) {
                        pass = false;
                        problemMet = true;
                        throw new AutoException(2, "Missing Automobile Base Price.");
                    }
                } catch (AutoException e) {
                    e.fixProblem(2, arr);
                    automotive.setBasePrice(Double.parseDouble(arr[0]));
                    pass = true;

                }

                if(problemMet == false) {
                    automotive.setBasePrice(Double.parseDouble(temp));//set the second line in txt to automotive basePrice
                }


                problemMet = false;

                boolean eof = false;
                while (!eof) {
                    String line = buff.readLine();
                    if (line.equals("/")) {
                        temp = buff.readLine();
                        try {
                            if (temp.equals("")) {
                                problemMet = true;
                                pass = false;
                                throw new AutoException(4, "Missing OptionSet Name.");
                            }
                        } catch (AutoException e) {
                            e.fixProblem(4, arr);
                            name = arr[0];
                            automotive.addOpset(counter, name);
                            counter++;
                            optionCounter = 0;
                            pass = true;
                        }

                        if (problemMet == false) {
                            name = temp;
                            automotive.addOpset(counter, name);
                            counter++;
                            optionCounter = 0;
                        }
                    }
                        //The txt file ends with "#"
                        else if (line.equals("#")) {
                            eof = true;
                            System.out.println("Reading completed!");
                        } else {
                            name = line;
                            try {
                                if (name.equals("")) {
                                    pass = false;
                                    problemMet = true;
                                    throw new AutoException(5, "Missing Option Name.");
                                }
                            }catch(AutoException e){
                                e.fixProblem(5, arr);
                                price = Double.parseDouble(buff.readLine());
                                OptionSet optionSet = automotive.getOptionSet(counter - 1);
                                automotive.addOption(optionCounter, optionSet, name, price);
                                optionCounter++;
                                pass = true;
                            }
                            if(problemMet == false) {
                                price = Double.parseDouble(buff.readLine());
                                OptionSet optionSet = automotive.getOptionSet(counter - 1);
                                automotive.addOption(optionCounter, optionSet, name, price);
                                optionCounter++;
                            }
                        }
                    }
                buff.close();
            } catch (IOException e) {
            AutoException a = new AutoException(6, "Unable to open file.");
            a.fixProblem(6, arr);
            this.filePath = arr[0];
            } catch (NumberFormatException e) {
                throw new AutoException(7, "Missing Price.");
            }
        } while(!pass);
        return automotive;
    }

    public Properties getProps(){
        Properties props = new Properties();
        try {
            FileInputStream in = new FileInputStream(filePath);
            props.load(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        return props;
    }

    public Automobile readProperties(Properties props){
        Automobile automobile = new Automobile();
        String CarMake = props.getProperty("CarMake");
        if(CarMake != null) {
            String CarName = props.getProperty("CarName");
            String CarModel = props.getProperty("CarModel");
            double basePrice = Double.parseDouble(props.getProperty("BasePrice"));
            automobile.setName(CarName);
            automobile.setMake(CarMake);
            automobile.setModel(CarModel);
            automobile.setBasePrice(basePrice);
            String OptionSetTemp = "";
            String OptionNameTemp = "";
            double OptionPriceTemp = 0;
            int i = 1;
            int j = 1;
            while(true){
                OptionSetTemp = props.getProperty("OptionSet" + i);
                if(OptionSetTemp == null){
                    break;
                }
                automobile.addOpset(OptionSetTemp);
                while(true){
                    OptionNameTemp = props.getProperty("OptionSet" + i + "Name" + j);
                    if(OptionNameTemp == null){
                        break;
                    }
                    OptionPriceTemp = Double.parseDouble(props.getProperty("OptionSet" + i + "Price" + j));
                    automobile.addOption(OptionSetTemp, OptionNameTemp, OptionPriceTemp);
                    j++;
                }
                i++;
                j = 1;
            }
        }
        return automobile;
    }

    public Automobile readProperties(){
        Automobile automobile = new Automobile();
        Properties props = new Properties();
        try {
            FileInputStream in = new FileInputStream(filePath);
            props.load(in);
            String CarMake = props.getProperty("CarMake");
            if(CarMake != null) {
                String CarName = props.getProperty("CarName");
                String CarModel = props.getProperty("CarModel");
                double basePrice = Double.parseDouble(props.getProperty("BasePrice"));
                automobile.setName(CarName);
                automobile.setMake(CarMake);
                automobile.setModel(CarModel);
                automobile.setBasePrice(basePrice);
                String OptionSetTemp = "";
                String OptionNameTemp = "";
                double OptionPriceTemp = 0;
                int i = 1;
                int j = 1;
                while(true){
                    OptionSetTemp = props.getProperty("OptionSet" + i);
                    if(OptionSetTemp == null){
                        break;
                    }
                    automobile.addOpset(OptionSetTemp);
                    while(true){
                        OptionNameTemp = props.getProperty("OptionSet" + i + "Name" + j);
                        if(OptionNameTemp == null){
                            break;
                        }
                        OptionPriceTemp = Double.parseDouble(props.getProperty("OptionSet" + i + "Price" + j));
                        automobile.addOption(OptionSetTemp, OptionNameTemp, OptionPriceTemp);
                        j++;
                    }
                    i++;
                    j = 1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return automobile;
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

    public Automobile deserializeAutomotive(String name) throws IOException, AutoException {
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
            System.out.printf("Error -- " + e.toString());
            throw new AutoException(1,"Unable to open file");
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