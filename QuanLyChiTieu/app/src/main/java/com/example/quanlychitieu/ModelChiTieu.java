package com.example.quanlychitieu;

import java.util.Date;

public class ModelChiTieu {
    private int ID;
    private int SoTien;
    private String LoaiTien;
    private String DiaDiemChi;
    private String ThoiGianChi;
    private String LyDoChi;
    public ModelChiTieu(){}

    public ModelChiTieu(int mID, int mSoTien, String mLoaiTien, String mDiaDiemChi, String mThoiGianChi, String mLyDoChi)
    {
        this.ID = mID;
        this.SoTien = mSoTien;
        this.LoaiTien = mLoaiTien;
        this.DiaDiemChi = mDiaDiemChi;
        this.ThoiGianChi = mThoiGianChi;
        this.LyDoChi = mLyDoChi;
    }

    public ModelChiTieu(int mSoTien, String mLoaiTien, String mDiaDiemChi, String mThoiGianChi, String mLyDoChi)
    {
        this.SoTien = mSoTien;
        this.LoaiTien = mLoaiTien;
        this.DiaDiemChi = mDiaDiemChi;
        this.ThoiGianChi = mThoiGianChi;
        this.LyDoChi = mLyDoChi;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int soTien) {
        SoTien = soTien;
    }

    public String getLoaiTien() {
        return LoaiTien;
    }

    public void setLoaiTien(String loaiTien) {
        LoaiTien = loaiTien;
    }

    public String getDiaDiemChi() {
        return DiaDiemChi;
    }

    public void setDiaDiemChi(String diaDiemChi) {
        DiaDiemChi = diaDiemChi;
    }

    public String  getThoiGianChi() {
        return ThoiGianChi;
    }

    public void setThoiGianChi(String thoiGianChi) {
        ThoiGianChi = thoiGianChi;
    }

    public String getLyDoChi() {
        return LyDoChi;
    }

    public void setLyDoChi(String lyDoChi) {
        LyDoChi = lyDoChi;
    }
}
