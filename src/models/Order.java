package models;

import java.util.ArrayList;

public class Order {

    String cusID;
    ArrayList<OrderItem> order;

    public Order(String cusID, ArrayList<OrderItem> order) {
        this.cusID = cusID;
        this.order = order;
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
}
