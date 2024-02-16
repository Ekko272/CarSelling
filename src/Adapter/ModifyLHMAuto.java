package Adapter;

import Model.Automobile;

public interface ModifyLHMAuto {
    public void initializeLHMAutos();
    void addAuto(String key, Automobile value);
    void deleteAutoByName(String name);
    Automobile getAutoByName(String name);
    void printAll();
}
