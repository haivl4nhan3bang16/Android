package com.example.thigiuakyasynctask;

import java.io.Serializable;

public class ModelPhone implements Serializable {
    private int ID;
    private String ProductName;
    private int Price;
    private String Description;
    private String Producer;

    public ModelPhone(){}
    public ModelPhone(int mID, String mProductName, int mPrice, String mDescription, String mProducer) {
        this.ID = mID;
        ProductName = mProductName;
        Price = mPrice;
        Description = mDescription;
        Producer = mProducer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }
}
