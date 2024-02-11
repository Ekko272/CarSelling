package Adapter;

import Model.OptionSet;

public interface ChoiceAuto {
    String getOptionChoice(String opSetName);
    void setOptionChoice(String setName, String optionName);
    double getOptionChoicePrice(String optionName);
    double getTotalPrice();
}
