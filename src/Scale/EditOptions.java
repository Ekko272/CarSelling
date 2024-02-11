package Scale;

import Adapter.ModifyLHMAutos;

public class EditOptions implements Runnable {
    private ModifyLHMAutos modifyLHMAutos;
    
    private String autoNameToEdit;
    private String optionSetName;
    private String oldOptName;
    private String newOptName;
    private double newOptPrice;
    private int operation;
    /*
    0: Update
    1: Delete
    2: Add
    */

    public EditOptions(ModifyLHMAutos auto){
        this.modifyLHMAutos = auto;
    }
    public EditOptions(ModifyLHMAutos auto, String m, String os, String o, String n, double p){
        this.modifyLHMAutos = auto;
        this.optionSetName = os;
        this.autoNameToEdit = m;
        this.oldOptName = o;
        this.newOptName = n;
        this.newOptPrice = p;
    }
    public void setNewOptName(String n){
        this.newOptName = n;
    }
    public void setNewOptPrice(double p){
        this.newOptPrice = p;
    }
    public void setAutoNameToEdit(String autoNameToEdit) {
        this.autoNameToEdit = autoNameToEdit;
    }
    public void setOldOptName(String oldOptName) {
        this.oldOptName = oldOptName;
    }
    public void setOperation(int x){this.operation = x;}
    public void setOptionSetName(String optionSetName) {
        this.optionSetName = optionSetName;
    }
    public String getNewOptName(){
        return this.newOptName;
    }
    public double getNewOptPrice(){
        return this.newOptPrice;
    }
    public String getAutoNameToEdit() {
        return this.autoNameToEdit;
    }
    public String getOldOptName() {
        return this.oldOptName;
    }
    public int getOperation(){return this.operation;}
    public String getOptionSetName() {
        return optionSetName;
    }


    public void run(){
        switch (operation) {
            case 0:
                modifyLHMAutos.getAutoByName(autoNameToEdit).updateOption(oldOptName, newOptName, newOptPrice);
                break;
            case 1:
                modifyLHMAutos.getAutoByName(autoNameToEdit).deleteOption(oldOptName);
                break;
            case 2:
                modifyLHMAutos.getAutoByName(autoNameToEdit).addOption(optionSetName, newOptName, newOptPrice);
            default:
                break;
        }
    }




}
