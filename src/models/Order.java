package models;

public class Order {

    String cusID;
    OrderItem order;

    public Order(String cusID, OrderItem order) {
        this.cusID = cusID;
        this.order = order;
    }

    public String getCusAccNum() {
        return cusID;
    }

    public void setCusAccNum(String cusID) {
        this.cusID = cusID;
    }

    public OrderItem getOrder() {
        return order;
    }

    public void setOrder(OrderItem order) {
        this.order = order;
    }
}
