package models;

import java.util.ArrayList;

public class Order {

    String cusID;
    ArrayList<OrderItem> order;
    double price;
    String cusName;
    String cusAdd;

    public Order(String cusID, ArrayList<OrderItem> order) {
        this.cusID = cusID;
        this.order = order;
    }

    public String toString() {
        return getClass().getSimpleName() + " : " + cusID + " : " + cusName + " : " + price + " : " + order + " : " + cusAdd;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAdd() {
        return cusAdd;
    }

    public void setCusAdd(String cusAdd) {
        this.cusAdd = cusAdd;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public ArrayList<OrderItem> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<OrderItem> order) {
        this.order = order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
