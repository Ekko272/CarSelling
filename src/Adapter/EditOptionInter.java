package Adapter;

public interface EditOptionInter {
    /*
    0: Update
    1: Delete
    2: Add
    */
    public void addOption(String autoName,String OpSetName, String name, double price);
    public void deleteOption(String autoName, String optionName);
    public void updateOption(String autoName, String name, String newName, double newPrice);
    //TODO: implement these methods in proxyAuto and manipulate the LHMAuto.
    // Then make the EditOption class implement Runnable and run() method for multithreading

}
