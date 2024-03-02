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


    public String promptAllModelsAndChose(Object obj){
        if(obj instanceof ArrayList){
            ArrayList <String> allModels = (ArrayList<String>) obj;
            System.out.println("Choose one model from below to configure");
            for (String allModel : allModels) {
                System.out.println(allModel);
            }
            return in.nextLine();
        }
        System.err.println("Not an ArrayList here");
        return null;
    }


    public void configureAuto(Object obj) throws InterruptedException {
        EditOptionInter editAuto = new BuildAuto();
        EditOptions eo = new EditOptions(editAuto);
        Automobile auto = (Automobile) obj;
        auto.print();
        System.out.println("Enter the Option name you want to configure: ");
        System.out.print("Old: ");
        String OptionName = in.nextLine();
        System.out.print("New: ");
        String newOptionName = in.nextLine();
        double newPrice;
        System.out.print("Option has price: " + auto.getOptionChoicePrice(OptionName) + ". Do you want to change it? (y/n)");
        if (in.nextLine().equalsIgnoreCase("y")){
            System.out.print("Enter the new price: ");
            newPrice = Double.parseDouble(in.nextLine());
            System.out.println("Starting configure...");
            auto.updateOption(OptionName, newOptionName, newPrice);
        }
        else{
            System.out.println("Starting configure...");
            auto.updateOption(OptionName, newOptionName);
        }




//        eo.setAutoNameToEdit(auto.getName());
//        eo.setOldOptName(OptionName);
//        eo.setNewOptName(newOptionName);
//        eo.setOperation(0); //Update operation
//
//        Thread t1 = new Thread(eo);
//        t1.start();
//        t1.join();

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
