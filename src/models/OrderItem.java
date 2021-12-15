package models;

public class OrderItem {

    int productID;
    int orderQuantity;
    double price;


    public OrderItem(int productID, int orderQuantity) {
        this.productID = productID;
        this.orderQuantity = orderQuantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
