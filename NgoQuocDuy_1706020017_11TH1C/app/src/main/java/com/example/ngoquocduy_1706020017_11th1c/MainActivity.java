package com.example.ngoquocduy_1706020017_11th1c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    static List<OOPPHONE> ListPhone = new ArrayList<OOPPHONE>();
    ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnInIt();
        Add();
        adapter = new ContactAdapter(this,R.layout.contact_item, ListPhone);
        listView.setAdapter(adapter);
    }

    private void Add() {
        ListPhone.add(new OOPPHONE("SamSung J7 Prime", "SC123", "5500000", "Đen huyền"));
        ListPhone.add(new OOPPHONE("SamSung J7 Plus", "SC124", "6500000", "Bể màn hình sml"));
        ListPhone.add(new OOPPHONE("SamSung J7 Pro", "SC125", "7500000", "Xài dở lắm đưng xài"));
        ListPhone.add(new OOPPHONE("OPPO F3", "SC126", "8500000", "Không thích xài lắm"));
    }

    private void OnInIt() {
        listView = findViewById(R.id.listview);
    }
}
