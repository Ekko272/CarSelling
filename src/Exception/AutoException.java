package Exception;

import Model.Automobile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AutoException extends Exception{
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
            writer.close();
            writer.newLine();
        }
        catch(IOException e)
        {

        }
    }

    public String fixProblem(int errorNuum){
        Fix1to100 f1 = new Fix1to100();
        switch (errorNuum){
            case 1:
                return f1.fix1();
            case 2:
                return Double.toString(f1.fix2());
            case 3:
                return Integer.toString(f1.fix3());

            case 4:
                return f1.fix4();
            case 5:
                return f1.fix5();
            case 6:
                System.out.println("Now entering fix [" + errorNuum + "]");
                String correctFileName = f1.fix6();
                System.out.println("Problem Fixed.");
                return correctFileName;

            case 7:
                return Double.toString(f1.fix7());
            default:
                System.out.println("You Should Not See This Message...");
        }
        return "";
    }


}
