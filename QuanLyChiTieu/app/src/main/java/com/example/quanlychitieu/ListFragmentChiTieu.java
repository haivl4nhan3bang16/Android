package com.example.quanlychitieu;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListFragmentChiTieu extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment_chi_tieu);
        tabLayout = findViewById(R.id.taglayout);
        viewPager = findViewById(R.id.viewpager);


      tabLayout.setupWithViewPager(viewPager);

        adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddPhieuChiTieu(), "Phiếu chi tiêu");
        adapter.addFragment(new History(), "Lịch sử chi tiêu");

        viewPager.setAdapter(adapter);
    }
}

