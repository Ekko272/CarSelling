package Scale;

import Adapter.ModifyLHMAutos;

public class EditOptions implements Runnable {
    private ModifyLHMAutos modifyLHMAutos;
    private String autoNameToEdit;
    private String oldName;
    private String newName;
    private double newPrice;
    private int operation;
    /*
    0: Update
    1: Delete
    */

    public EditOptions(ModifyLHMAutos auto){
        this.modifyLHMAutos = auto;
    }
    public EditOptions(ModifyLHMAutos auto, String m, String o, String n, double p){
        this.modifyLHMAutos = auto;
        this.autoNameToEdit = m;
        this.oldName = o;
        this.newName = n;
        this.newPrice = p;
    }
    public void setNewName(String n){
        this.newName = n;
    }
    public void setNewPrice(double p){
        this.newPrice = p;
    }
    public void setAutoNameToEdit(String autoNameToEdit) {
        this.autoNameToEdit = autoNameToEdit;
    }
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
    public void setOperation(int x){this.operation = x;}
    public String getNewName(){
        return this.newName;
    }
    public double getNewPrice(){
        return this.newPrice;
    }
    public String getAutoNameToEdit() {
        return this.autoNameToEdit;
    }
    public String getOldName() {
        return this.oldName;
    }
    public int getOperation(){return this.operation;}

    public void run(){
        switch (operation) {
            case 0:
                modifyLHMAutos.getAutoByName(autoNameToEdit).updateOption(oldName, newName, newPrice);
                break;
            case 1:
                modifyLHMAutos.getAutoByName(autoNameToEdit).deleteOption(oldName);
                break;
            default:
                break;
        }
    }




}
