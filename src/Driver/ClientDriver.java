/**
 * CIS 35B Assignment 5: ClientDriver.java
 *
 * @author Xuanyu Liu
 */
package Driver;
import Client.*;

//This class represents client side
public class ClientDriver {
    public static void main(String[] args) {
        DefaultClientSocket client = new DefaultClientSocket("127.0.0.1", 4455);
        client.start();
    }

}
