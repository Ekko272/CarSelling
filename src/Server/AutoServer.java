package Server;

import Model.Automobile;
import Exception.*;

import java.util.ArrayList;
import java.util.Properties;

public interface AutoServer {
    Automobile buildAutoByProps(String fileName);
    Automobile buildAutoByProps(Properties props);
    Automobile buildAutoByText(String fileName)throws AutoException;
    ArrayList<String> getAllExistedModel();
    void addAutoToLHMAuto(Automobile automobile);
}
