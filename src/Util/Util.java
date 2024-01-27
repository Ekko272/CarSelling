/**
 * CIS 35B Assignment 1: Automotive.java
 *
 * @author Xuanyu Liu
 */
package Util;

import Model.Automobile;
import Model.OptionSet;
import Exception.*;

import java.io.*;

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
        int numOfOptionSets;
        int numOfOptions;
        String name;
        String temp;
        double price;
        int counter = 0;
        int optionCounter = 0;

        int errorCodeMet = 0;
        String fixedValue = "";
        boolean pass = false;
        do {
            try {
                FileReader file = new FileReader(filePath);
                BufferedReader buff = new BufferedReader(file);
                pass = true;

                String valueHolder = buff.readLine(); //valueHolder for determine AutoException
                if(errorCodeMet == 1){
                    valueHolder = fixedValue;
                    errorCodeMet = 0;
                    pass = true;
                }
                if (valueHolder.equals("")) {
                    pass = false;
                    throw new AutoException(1, "Missing Automobile Name.");
                }

                automotive.setName(valueHolder);//set the first line in txt to automotive name

                valueHolder = buff.readLine();
                if(errorCodeMet == 2){
                    valueHolder = fixedValue;
                    errorCodeMet = 0;
                    pass = true;
                }
                if (valueHolder.equals("")) {
                    pass = false;
                    throw new AutoException(2, "Missing Automobile Base Price.");
                }
                automotive.setBasePrice(Double.parseDouble(valueHolder));//set the second line in txt to automotive basePrice

                valueHolder = buff.readLine();
                if(errorCodeMet == 3){
                    valueHolder = fixedValue;
                    errorCodeMet = 0;
                    pass = true;
                }
                if (valueHolder.equals("")) {
                    pass = false;
                    throw new AutoException(3, "Missing Number of OptionSets.");
                }
                numOfOptionSets = Integer.parseInt(valueHolder);//set the third line in txt to the # of OptionSet

                automotive.openSpaceForOptionSet(numOfOptionSets);//open space for OptionSet Array
                boolean eof = false;
                while (!eof) {
                    String line = buff.readLine();
                    if (line.equals("/")) {
                        temp = buff.readLine();
                        if (temp.equals("")) {
                            throw new AutoException(4, "Missing OptionSet Name.");
                        }
                        name = temp;
                        numOfOptions = Integer.parseInt(buff.readLine());
                        automotive.setOpset(counter, name, numOfOptions);
                        counter++;
                        optionCounter = 0;
                    }
                    //The txt file ends with "#"
                    else if (line.equals("#")) {
                        eof = true;
                        System.out.println("Reading completed!");
                    } else {
                        name = line;
                        if (name.equals("")) {
                            throw new AutoException(5, "Missing Option Name.");
                        }
                        price = Double.parseDouble(buff.readLine());
                        OptionSet optionSet = automotive.getOptionSet(counter - 1);
                        automotive.setOption(optionCounter, optionSet, name, price);
                        optionCounter++;
                    }
                }
                buff.close();
            } catch (IOException e) {
                AutoException a = new AutoException(6, "Unable to open file.");
                this.filePath = a.fixProblem(a.getErrorNum());
            } catch (NumberFormatException e) {
                System.out.println("Error " + e.toString());
                throw new AutoException(7, "Missing Price.");
            } catch (AutoException e) {
                errorCodeMet = e.getErrorNum();
                fixedValue = e.fixProblem(errorCodeMet);

            }
        } while(!pass);
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