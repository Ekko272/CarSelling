package Exception;

import Model.Automobile;

import java.util.Scanner;

public class Fix1to100 {
    private int errorNum;
    private Scanner s1 = new Scanner(System.in);

    public Fix1to100(){}
    public Fix1to100(int errorNum) {
        this.errorNum = errorNum;
    }

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

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public String fix1(){
        System.out.println("Enter the Automobile Name: ");
        return s1.nextLine();
    }

    public double fix2(){
        System.out.println("Enter the Automobile Base Price: ");
        double temp = ensureInputValidDouble();
        return temp;
    }

    public int fix3(){
        System.out.println("Enter the Number of OptionSets of the Automobile: ");
        int temp = ensureInputValidInt();
        return temp;
    }

    public String fix4(){
        System.out.println("Enter the OptionSet Name: ");
        return s1.nextLine();
    }

    public String fix5(){
        System.out.println("Enter the Option Name: ");
        return s1.nextLine();
    }

    public String fix6(){
        System.out.println("Problem [Unable To Open File]");
        System.out.println("Problem Fixing...");
        return "/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt";
    }

    public double fix7(){
        System.out.println("Enter the Option Price: ");
        double temp = ensureInputValidDouble();
        return temp;
    }

}
