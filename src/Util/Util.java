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
                
                temp = buff.readLine();
                try {
                    if (temp.equals("")) {
                        pass = false;
                        problemMet = true;
                        throw new AutoException(3, "Missing Number of OptionSets.");
                    }
                }catch (AutoException e){
                    e.fixProblem(3, arr);
                    numOfOptionSets = Integer.parseInt(arr[0]);

                    pass = true;
                }
                if(problemMet == false) {
                    numOfOptionSets = Integer.parseInt(temp);//set the third line in txt to the # of OptionSet
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
                            numOfOptions = Integer.parseInt(buff.readLine());
                            automotive.addOpset(counter, name, numOfOptions);
                            counter++;
                            optionCounter = 0;
                            pass = true;
                        }

                        if (problemMet == false) {
                            name = temp;
                            numOfOptions = Integer.parseInt(buff.readLine());
                            automotive.addOpset(counter, name, numOfOptions);
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