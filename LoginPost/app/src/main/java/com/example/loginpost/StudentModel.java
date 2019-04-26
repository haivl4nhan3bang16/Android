package com.example.loginpost;

public class StudentModel {
    private int ID;
    private String Code;
    private String Name;
    private int Age;
    private String Classs;
    private String Email;
    public StudentModel(){}
    public StudentModel(int mID, String mCode, String mName, int mAge, String mClasss, String mEmail) {
        this.ID = mID;
        Code = mCode;
        Name = mName;
        Age = mAge;
        Classs = mClasss;
        Email = mEmail;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getClasss() {
        return Classs;
    }

    public void setClasss(String classs) {
        Classs = classs;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
