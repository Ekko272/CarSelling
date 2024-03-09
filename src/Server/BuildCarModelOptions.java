package Server;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Adapter.ProxyAutomobile;
import Exception.AutoException;
import Model.Automobile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class BuildCarModelOptions extends ProxyAutomobile {
    private static final int WAITING = 0;
    private static final int REQUEST_BUILD_AUTO = 1;
    private static final int REQUEST_CONFIGURE_AUTO = 2;
    private int state = WAITING;


    public void setState(int x){this.state = x;}
    public Object processRequest(Object obj) throws AutoException, IOException {
        Object toClient = null;

        if (state == REQUEST_BUILD_AUTO) {
            if (obj instanceof StringBuffer) {
                StringBuffer stringBuffer = (StringBuffer) obj;
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
                writer.write(stringBuffer.toString());
                writer.close();
                addAutoToLHMAuto(buildAutoByText("output.txt"));
            }
            if (obj instanceof Properties) {
                addAutoToLHMAuto(buildAutoByProps((Properties)obj));
            }
            toClient = "Automobile object successfully added to database\n"
                    + "Do you want to continue on menu? (y/n)";
        }
        else if (state == REQUEST_CONFIGURE_AUTO) {

            toClient = getAutoByName(obj.toString());
        }
        else {
            toClient = "Invalid Option. Exiting...";
            System.exit(1);
        }
        this.state = WAITING;

        return toClient;
    }


}
