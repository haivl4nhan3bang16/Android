package com.example.quanlychitieu;

public class ModelLogins {
    private int ID;
    private String USER;
    private String PASS;
    private int SODU;
    private int CHITIEU;
    public ModelLogins(){}

    public ModelLogins(int mID, String mUSER, String mPass, int mSoDu, int mChiTieu)
    {
        this.ID = mID;
        this.USER = mUSER;
        this.PASS = mPass;
        this.SODU = mSoDu;
        this.CHITIEU = mChiTieu;
    }

    public ModelLogins(String mUSER, String mPass, int mSoDu, int mChiTieu)
    {
        this.USER = mUSER;
        this.PASS = mPass;
        this.SODU = mSoDu;
        this.CHITIEU = mChiTieu;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public int getSODU() {
        return SODU;
    }

    public void setSODU(int SODU) {
        this.SODU = SODU;
    }

    public int getCHITIEU() {
        return CHITIEU;
    }

    public void setCHITIEU(int CHITIEU) {
        this.CHITIEU = CHITIEU;
    }
}
