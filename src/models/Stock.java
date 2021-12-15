package models;

import java.io.Serializable;

public class Stock implements Serializable {

    String productName;
    int productQuantity;
    String source;
    int productID;
    double price;

    public Stock(String productName, int productQuantity, String source, int productID, double price) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.source = source;
        this.productID = productID;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductId() {
        return productID;
    }
}

