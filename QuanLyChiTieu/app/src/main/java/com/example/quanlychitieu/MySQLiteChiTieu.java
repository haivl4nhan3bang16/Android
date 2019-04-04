package com.example.quanlychitieu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteChiTieu extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SQLChiTieu.db";
    public static final String TABLE_NAME = "ChiTieu";
    public static final String ID_CT = "id";
    public static final String SOTIEN_CT = "SoTien";
    public static final String LOAITIEN_CT = "LoaiTien";
    public static final String DIADIEM_CT = "DiaDiem";
    public static final String THOIGIAN_CT = "ThoiGian";
    public static final String LyDo_CT = "LyDo";


    private String TABLE_NAMELOGIN = "Login";
    private String ID = "id";
    private String USER = "user";
    private String PASS = "pass";
    private String SODU = "sodu";


    public SQLiteDatabase db;

    public MySQLiteChiTieu(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String mQueryLogin = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT, %s TEXT, %s INT)",
                TABLE_NAMELOGIN, ID, USER, PASS, SODU);
        db.execSQL(mQueryLogin);


        String mQuery = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s INT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, ID_CT, SOTIEN_CT, LOAITIEN_CT, DIADIEM_CT, THOIGIAN_CT, LyDo_CT);
        db.execSQL(mQuery);


    }

    public void Update(int id, String sodu)
    {
        String query = "update " + TABLE_NAMELOGIN + " set " + SODU + "= '" + sodu + "' where " + ID + "=" + id;
        db.execSQL(query);
    }

    public int SumAll()
    {
        String query = String.format("SELECT SUM(%s) FROM %s", SOTIEN_CT, TABLE_NAME);
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int sum = cursor.getInt(0);
        return sum;
    }
    public void Insert(int ID, int SoTien, String LoaiTien, String DiaDiem, String ThoiGian, String LyDo){
        String insert = String.format("insert into %s (%s, %s , %s, %s, %s, %s) values (%s, %s, '%s', '%s', '%s', '%s')",
                TABLE_NAME, ID_CT, SOTIEN_CT,LOAITIEN_CT,DIADIEM_CT,THOIGIAN_CT,LyDo_CT,
                ID, SoTien, LoaiTien, DiaDiem, ThoiGian, LyDo);
        db.execSQL(insert);
    }

    public void InsertLogin(String user, String pass, int mSODU) {
        String insert = String.format("insert into %s (%s , %s, %s) values ('%s', '%s', %s)",
                TABLE_NAMELOGIN, USER, PASS, SODU, user, pass, mSODU);
        db.execSQL(insert);
    }

    public boolean CheckLogin(String user, String pass)
    {
        Cursor cursor;
        String check = "select * from " + TABLE_NAMELOGIN + " where " + USER + "='"+ user +"' and "+ PASS +"='"+ pass +"'";
        cursor = db.rawQuery(check, null);
        if(cursor.getCount() > 0)
        {
            return true;
        }
        cursor.close();
        return false;
    }

    public List<ModelLogin> getSoDu()
    {
        String query = "select * from " + TABLE_NAMELOGIN;
        List<ModelLogin> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        for(int i = 0; i < cursor.getCount(); i++)
        {
            cursor.moveToNext();
            ModelLogin item = new ModelLogin();
            item.setID(cursor.getInt(0));
            item.setUSER(cursor.getString(1));
            item.setPASS(cursor.getString(2));
            item.setSODU(cursor.getInt(3));
            item.setCHITIEU(cursor.getInt(4));
            list.add(item);
        }
        cursor.close();
        return list;
    }

    public List<ModelChiTieu> getAll()
    {
        String query = "select * from " + TABLE_NAME;
        List<ModelChiTieu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        for(int i = 0; i < cursor.getCount(); i++)
        {
            cursor.moveToNext();
            ModelChiTieu item = new ModelChiTieu();
            item.setID(cursor.getInt(0));
            item.setSoTien(cursor.getInt(1));
            item.setLoaiTien(cursor.getString(2));
            item.setDiaDiemChi(cursor.getString(3));
            item.setThoiGianChi(cursor.getString(4));
            item.setLyDoChi(cursor.getString(5));
            list.add(item);
        }
        cursor.close();
        return list;
    }

    public int getIDLogin(String user, String pass)
    {
        String query = String.format("select * from %s where %s = '%s' and %s = '%s'", TABLE_NAMELOGIN, USER, user, PASS, pass);
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int mID = cursor.getInt(cursor.getColumnIndex(ID));
        cursor.close();
        return mID;
    }

    public int getSoDu(int MSODU)
    {
        String query = String.format("select * from %s where %s = %s", TABLE_NAMELOGIN, ID, MSODU);
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int mSD = cursor.getInt(cursor.getColumnIndex(SODU));
        cursor.close();
        return mSD;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
