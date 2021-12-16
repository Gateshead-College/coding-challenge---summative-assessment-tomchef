package models;

public class OrderItem {

    int productID;
    int orderQuantity;
    double price;
    double unitCost;
    String productName;


    public OrderItem(int productID, int orderQuantity) {
        this.productID = productID;
        this.orderQuantity = orderQuantity;
    }

    public String toString() {
        return getClass().getSimpleName() + " = " + productName + " : $" + unitCost + " : x" + orderQuantity + " : $" + price + " : " + productID;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
