package Adapter;

import Model.Automobile;

public interface ModifyLHMAutos {
    void addAuto(String key, Automobile value);
    void deleteAutoByName(String name);
    Automobile getAutoByName(String name);
    void printAll();
}
