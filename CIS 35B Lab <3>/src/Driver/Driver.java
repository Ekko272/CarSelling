/**
 * CIS 35B Assignment 3: Driver.java
 *
 * @author Xuanyu Liu
 */
package Driver;

import Adapter.BuildAuto;
import Adapter.ProxyAutomobile;
import Exception.AutoException;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Driver {
    public static void main(String[] args) throws AutoException {
        LinkedHashMap<String, ProxyAutomobile> automobileLinkedHashMap = new LinkedHashMap<>();

        //Code works well after changing some properties to ArrayList
        BuildAuto newAuto = new BuildAuto();
        newAuto.buildAuto("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt");

        newAuto.printAuto();
        newAuto.updateOptionSetName("zws", "Color", "NewNameColor");
        newAuto.printAuto();

        BuildAuto secAuto = new BuildAuto();
        secAuto.buildAuto("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info2.txt");

        BuildAuto thirdAuto = new BuildAuto();
        secAuto.buildAuto("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info3.txt");

        //Populate some automobile instances and save them into LinkedHashMap
        automobileLinkedHashMap.put("ZWS", newAuto);
        automobileLinkedHashMap.put("Second Car", secAuto);
        automobileLinkedHashMap.put("Third Car", thirdAuto);

        //Iterator travels through the LinkedHashMap
        Set st = automobileLinkedHashMap.keySet();
        Iterator itr = st.iterator();

        while (itr.hasNext()){
            System.out.println(itr.next());
        }

    }

}