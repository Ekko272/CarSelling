package Exception;

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
    }

    public String fixProblem(int errorNuum){
        Fix1to100 f1 = new Fix1to100();
        switch (errorNuum){
            case 6:
                String correctFileName = f1.fix6();
                return correctFileName;

            default:
                errorNuum = 0;
                System.out.println("No Problem Has Been Solved For Now...");
        }
        return "";
    }


}
