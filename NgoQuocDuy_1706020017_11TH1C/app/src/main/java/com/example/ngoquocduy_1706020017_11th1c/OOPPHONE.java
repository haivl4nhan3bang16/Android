package com.example.ngoquocduy_1706020017_11th1c;

public class OOPPHONE {
    private String NameSubject;
    private String MaDT;
    private String PricePhone;
    private String Description;


    public OOPPHONE(String mNameSubject, String mMaDT, String mPricePhone, String mDescription)
    {
        this.NameSubject = mNameSubject;
        this.MaDT = mMaDT;
        this.PricePhone = mPricePhone;
        this.Description = mDescription;
    }
    public String getNameSubject() {
        return NameSubject;
    }

    public void setNameSubject(String nameSubject) {
        NameSubject = nameSubject;
    }

    public String getMaDT() {
        return MaDT;
    }

    public void setMaDT(String maDT) {
        MaDT = maDT;
    }

    public String getPricePhone() {
        return PricePhone;
    }

    public void setPricePhone(String pricePhone) {
        PricePhone = pricePhone;
    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
