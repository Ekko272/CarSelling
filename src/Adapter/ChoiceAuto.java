package Adapter;

import Model.OptionSet;

public interface ChoiceAuto {
    public String getOptionChoice(String opSetName);
    public void setOptionChoice(String setName, String optionName);
    public double getOptionChoicePrice(String optionName);
    public double getTotalPrice();
}
