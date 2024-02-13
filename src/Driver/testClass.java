package Driver;
/*
 I think after I run the program, the result should be:

 Waiting....
 In notifyy()...
 We can delete now

 What I'm thinking is, first, Thread t1 runs into wait() since it calls deleteAA() and isOpen = false.
 After Thread.sleep(1000), Thread t2 calls notifyyyy() which set isOpen = true and calls notifyAll().
 Now wait() has been awaken, and it jumps out from while loop, System.out.println("We can delete now.") is executed.


 But the first thread seems stuck in the while loop even though isOpen is set to true by notifyy()

 */

import java.util.ArrayList;

public class testClass implements Runnable {
    int x;
    private static ArrayList<Integer> arrayList;
    private boolean isOpen = false;

    public testClass(int x) {
        this.x = x;
    }
    public void run() {
        switch (x) {
            case 1:
                try {
                    deleteAA();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    notifyyyy();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                break;
        }
    }
    public synchronized void deleteAA() throws InterruptedException {
        while (!isOpen) {
            System.out.println("Waiting...");
            wait();
        }
        System.out.println("We can delete now.");
    }

    public synchronized void notifyyyy() throws InterruptedException {
        Thread.sleep(1000);
        isOpen = true;
        System.out.println("In notifyyyy()...");
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        testClass ts = new testClass(1);
        testClass ts2 = new testClass(2);
        Thread t1 = new Thread(ts);
        Thread t2 = new Thread(ts2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

