/**
 * CIS 35B Assignment 2: CreateAuto.java
 *
 * @author Xuanyu Liu
 */

package Adapter;
import Exception.AutoException;
public interface CreateAuto {
    void buildAuto(String fileName, String fileType) throws AutoException;
    void printAuto(String ModelName) throws AutoException;
}
