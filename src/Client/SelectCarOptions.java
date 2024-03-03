package Client;

import Adapter.BuildAuto;
import Adapter.EditOptionInter;
import Model.Automobile;
import Scale.EditOptions;
import Server.AutoServer;

import java.util.ArrayList;
import java.util.Scanner;

public class SelectCarOptions {
    private Scanner in = new Scanner(System.in);
    public SelectCarOptions() {}


    //Takes an object, which should be an ArrayList that contains all the Model names inside the linkedHashMapAuto and received from server.
    //Prompt the client to choose one of them, and check if the client has chosen the one that the linkedHashMapAuto contains.
    public String promptAllModelsAndChose(Object obj){
        if(obj instanceof ArrayList){
            String clientChoice = "";
            boolean validChoice = false;
            ArrayList <String> allModels = (ArrayList<String>) obj;

            while(!validChoice) {
                System.out.println("Choose one model from below to configure");
                for (String allModel : allModels) {
                    System.out.println(allModel);
                }
                clientChoice = in.nextLine();
                if (allModels.contains(clientChoice)) {
                    validChoice = true;
                }else {
                    System.out.println("Invalid choice. Please choose from the available models.");
                }
            }
            return clientChoice;

        }
        System.err.println("Model List does not exist/Something wrong with the BuildCarModelOptions method");
        return null;
    }


    //Takes an object, which should be an Automobile and received from server.
    //Prompt client to configure it on the CLIENT SIDE
    public void configureAuto(Object obj){
        try {
            Automobile auto = (Automobile) obj;
            auto.print();
            System.out.println("Enter the Option name you want to configure: ");
            System.out.print("Old: ");
            String OptionName = in.nextLine();
            System.out.print("New: ");
            String newOptionName = in.nextLine();
            double newPrice;
            System.out.print("Option has price: " + auto.getOptionChoicePrice(OptionName) + ". Do you want to change it? (y/n)");
            if (in.nextLine().equalsIgnoreCase("y")) {
                System.out.print("Enter the new price: ");
                newPrice = Double.parseDouble(in.nextLine());
                System.out.println("Starting configure...");
                auto.updateOption(OptionName, newOptionName, newPrice);
            } else {
                System.out.println("Starting configure...");
                auto.updateOption(OptionName, newOptionName);
            }

            auto.print();
            System.out.println("Configuration succeed!");
        }catch (NumberFormatException e){
            System.err.println("Please enter a number for new price");
        }

    }

    public boolean isAutomobile(Object obj) {
        boolean isAutomobile = false;

        try {
            Automobile a1 = (Automobile) obj;
            isAutomobile = true;
        }
        catch (ClassCastException e) {
            isAutomobile = false;
        }

        return isAutomobile;
    }
}
