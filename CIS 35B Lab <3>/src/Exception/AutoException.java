/**
 * CIS 35B Assignment 3: Automotive.java
 *
 * @author Xuanyu Liu
 */

package Exception;

import Adapter.FixAuto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AutoException extends Exception implements FixAuto{
    private int errorNum;
    private String errorMsg;

    public AutoException(){}
    public AutoException(int errorNum){
        super();
        this.errorNum = errorNum;
        printMyProblem();
    }

    public AutoException(String errorMsg){
        super();
        this.errorMsg = errorMsg;
        printMyProblem();
    }
    public AutoException(int errorNum, String errorMsg){
        super();
        this.errorNum = errorNum;
        this.errorMsg = errorMsg;
        printMyProblem();
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void printMyProblem(){
        System.out.println("Problem: Error #[" + errorNum + "], Error Message: [" + errorMsg + "]");
        writeToFile();
    }



    public void writeToFile()
    {
        try
        {
            FileWriter a1 = new FileWriter("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/Logging.txt", true);
            BufferedWriter writer = new BufferedWriter(a1);

            //Java.time methods
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = currentTime.format(formatter);

            writer.append(time);
            writer.append(" Error Number: ").append(String.valueOf(errorNum)).append(" ").append(errorMsg).append("\n");
            writer.newLine();
            writer.close();
        }
        catch(IOException e)
        {

        }
    }

    public void writeFixMsgToFile()
    {

        try
        {
            FileWriter a1 = new FileWriter("/Users/liuxuanyu/Desktop/CIS 35B/Lab1/src/Logging.txt", true);
            BufferedWriter writer = new BufferedWriter(a1);

            //Java.time methods
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String time = currentTime.format(formatter);
            writer.append(time);
            writer.append(" ----The Error Has Been Fixed----\n");
            writer.newLine();
            writer.close();
        }
        catch(IOException e)
        {

        }
    }


    public void fixProblem(int errorNuum, String[] temp){

        Fix1to100 f1 = new Fix1to100();
        switch (errorNuum){
            case 1:
                writeFixMsgToFile();
                temp[0] = f1.fix1();
                break;

            case 2:
                writeFixMsgToFile();
                temp[0] = f1.fix2();
                break;
            case 3:
                writeFixMsgToFile();
                temp[0] = f1.fix3();
                break;
            case 4:
                writeFixMsgToFile();
                temp[0] = f1.fix4();
                break;
            case 5:
                writeFixMsgToFile();
                temp[0] = f1.fix5();
                break;
            case 6:
                System.out.println("Now entering fix [" + errorNuum + "]");
                //f1.fix6(temp);
                System.out.println("Problem Fixed.");
                writeFixMsgToFile();
                break;
            case 7:
                writeFixMsgToFile();
                temp[0] = f1.fix7();
                break;


            default:
                System.out.println("You Should Not See This Message...");
        }

    }


}
