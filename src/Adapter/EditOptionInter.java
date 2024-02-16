package Adapter;

public interface EditOptionInter {
    /*
    0: Update
    1: Delete
    2: Add
    */
    void addOption(String autoName,String OpSetName, String name, double price);
    void deleteOption(String autoName, String optionName);
    void updateOption(String autoName, String name, String newName, double newPrice);

}
