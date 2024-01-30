/**
 * CIS 35B Assignment 2: CreateAuto.java
 *
 * @author Xuanyu Liu
 */

package Adapter;
import Exception.AutoException;
public interface CreateAuto {
    public void buildAuto(String fileName) throws AutoException;
    public void printAuto(String ModelName) throws AutoException;
}
