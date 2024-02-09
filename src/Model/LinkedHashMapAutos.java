package Model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class LinkedHashMapAutos {
    private LinkedHashMap<String, Automobile> LHMAutos;

    public LinkedHashMap<String, Automobile> getLHMAutos() {return LHMAutos;}
    public void setLHMAutos(LinkedHashMap<String, Automobile> LHMAutos) {this.LHMAutos = LHMAutos;}
    public LinkedHashMapAutos(){this.LHMAutos = new LinkedHashMap<>();}
    public LinkedHashMapAutos(LinkedHashMap<String, Automobile> LHMAutos) {this.LHMAutos = LHMAutos;}

    public void addAuto(String key, Automobile value){
        LHMAutos.put(key, value);
    }
    public void deleteAutoByName(String name){
        LHMAutos.remove(name);
    }
    public Automobile getAutoByName(String name){
        return LHMAutos.get(name);
    }
    public void printAll(){
        Set st = LHMAutos.keySet();
        Iterator itr = st.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
