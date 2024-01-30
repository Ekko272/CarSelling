/**
 * CIS 35B Assignment 2: Fix1to100.java
 *
 * @author Xuanyu Liu
 */

package Exception;

import java.util.Scanner;

public class Fix1to100 {
    private Scanner s1 = new Scanner(System.in);
    public Fix1to100(){}

    //This method avoid Exceptions from user input
    public double ensureInputValidDouble() {
        boolean flag = false;
        double num = 0;
        while (!flag) {
            if (s1.hasNextDouble()) {
                num = s1.nextDouble();
                flag = true;
            } else {
                System.out.println("Your enter is not valid. Please enter a valid number.");
                s1.next();
            }
        }
        return num;
    }
    public int ensureInputValidInt() {
        boolean flag = false;
        int num = 0;
        while (!flag) {
            if (s1.hasNextInt()) {
                num = s1.nextInt();
                flag = true;
            } else {
                System.out.println("Your enter is not valid. Please enter a valid number.");
                s1.next();
            }
        }
        return num;
    }

    public String fix1() {
        System.out.println("Enter the Automobile Name: ");
        String name = s1.nextLine();
        return name;
    }

    public String fix2(){
        System.out.println("Enter the Automobile Base Price: ");
        return String.valueOf(ensureInputValidDouble());
    }

    public String fix3(){
        System.out.println("Enter the Number of OptionSets of the Automobile: ");
        return String.valueOf(ensureInputValidInt());
    }

    public String fix4(){
        System.out.println("Enter the OptionSet Name: ");
        return s1.nextLine();
    }

    public String fix5(){
        System.out.println("Enter the Option Name: ");
        return s1.nextLine();
    }

    public String fix6(String fName){
        System.out.println("Problem [Unable To Open File]");
        System.out.println("Problem Fixing...");
        return "/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt";
    }

    public String fix7(){
        System.out.println("Enter the Option Price: ");
        return String.valueOf(ensureInputValidDouble());

    }

}
