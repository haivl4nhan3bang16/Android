package com.example.detaicuoiky;

public class Model{
    private String userName;
    private String userEmail;
    private String userID;
    private String ProductID;
    private String ProductName;
    private String Price;
    private String Origin;

    public Model(String userName, String userEmail, String userID, String mName, String mPrice, String mOrigin) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userID = userID;
        this.ProductName = mName;
        this.Price = mPrice;
        this.Origin = mOrigin;
    }

    public Model() {
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }
}
