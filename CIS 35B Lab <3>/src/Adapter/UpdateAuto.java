/**
 * CIS 35B Assignment 2: UpdateAuto.java
 *
 * @author Xuanyu Liu
 */

package Adapter;
import Exception.AutoException;
public interface UpdateAuto {
    public void updateOptionSetName(String ModelName, String OptionSetName, String newName) throws AutoException;
    public void updateOptionPrice(String ModelName, String OptionName, String Option, double newPrice) throws AutoException;
}
