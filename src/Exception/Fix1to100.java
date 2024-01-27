package Exception;

import java.util.Scanner;

public class Fix1to100 {
    private int errorNum;

    public Fix1to100(){}
    public Fix1to100(int errorNum) {
        this.errorNum = errorNum;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public String fix6(){
        System.out.println("Problem [Unable To Open File]");
        return "/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/info.txt";
    }

    /*
    public String fix2(){
        System.out.println("Problem [Missing Name/Price]");
        Scanner s1 =
    }

     */


}
