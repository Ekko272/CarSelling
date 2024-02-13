package Scale;

import Adapter.EditOptionInter;

public class EditOptions implements Runnable {
    private EditOptionInter eoi;
    
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

    public EditOptions(EditOptionInter auto){
        this.eoi = auto;
    }
    public EditOptions(EditOptionInter auto, String m, String os, String o, String n, double p){
        this.eoi = auto;
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
                eoi.updateOption(autoNameToEdit, oldOptName, newOptName, newOptPrice);
                break;
            case 1:
                eoi.deleteOption(autoNameToEdit, oldOptName);
                break;
            case 2:
                eoi.addOption(autoNameToEdit, optionSetName, newOptName, newOptPrice);
            default:
                break;
        }
    }




}
