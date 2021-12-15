package models;

public class Customer {
    String cusName;
    String cusAccNum;
    String cusemail;
    String cusphone;
    String cusadd;

    public Customer(String cusName, String cusAccNum, String cusemail, String cusphone, String cusadd) {
        this.cusName = cusName;
        this.cusAccNum = cusAccNum;
        this.cusemail = cusemail;
        this.cusphone = cusphone;
        this.cusadd = cusadd;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAccNum() {
        return cusAccNum;
    }

    public void setCusAccNum(String cusAccNum) {
        this.cusAccNum = cusAccNum;
    }

    public String getCusemail() {
        return cusemail;
    }

    public void setCusemail(String cusemail) {
        this.cusemail = cusemail;
    }

    public String getCusphone() {
        return cusphone;
    }

    public void setCusphone(String cusphone) {
        this.cusphone = cusphone;
    }

    public String getCusadd() {
        return cusadd;
    }

    public void setCusadd(String cusadd) {
        this.cusadd = cusadd;
    }
}
