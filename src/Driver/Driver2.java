package Driver;
import Client.*;
public class Driver2 {
    public static void main(String[] args) {
        DefaultClientSocket client = new DefaultClientSocket("127.0.0.1", 4455);
        client.start();
    }

}
