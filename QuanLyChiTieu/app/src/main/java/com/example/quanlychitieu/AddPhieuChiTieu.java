package com.example.quanlychitieu;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPhieuChiTieu extends Fragment {
    ListView listView;
    TextView txtSodu;
    Button btnViewSoDu;
    static List<ModelChiTieu> modelList = new ArrayList<>();
    List<ModelChiTieu> modelChiTieus = new ArrayList<>();
    static ContactAdapterChiTieu adapter;
    static MySQLiteChiTieu mySQLiteChiTieu;
    Button btnAddChiTieu;



    public AddPhieuChiTieu() {
        // Required empty public constructor
    }
    @Override
    public void onResume()
    {
        super.onResume();
        modelChiTieus.clear();
        modelList.clear();
        modelList.addAll(mySQLiteChiTieu.getAll());

        Date date = new Date();
        try
        {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < modelList.size(); i++){
            Date getDay = new Date();
            try {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(modelList.get(i).getThoiGianChi());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if((getDay.getMonth() ==  date.getMonth()) && getDay.getDay() <= date.getDay()){
                modelChiTieus.add(modelList.get(i));
            }else
            {
                Toast.makeText(getContext(),"Lưu ý: Không thể tạo phiếu của tương lai", Toast.LENGTH_LONG).show();
            }
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_phieu_chi_tieu, container, false);
        listView = view.findViewById(R.id.listview);
        btnAddChiTieu = view.findViewById(R.id.btnAdd);
        btnViewSoDu = view.findViewById(R.id.btnSoDu);
        mySQLiteChiTieu = new MySQLiteChiTieu(getContext());
        modelList = mySQLiteChiTieu.getAll();
        if(modelList.isEmpty())
        {
            modelList = mySQLiteChiTieu.getAll();
        }
        adapter = new ContactAdapterChiTieu(getContext(), R.layout.custum_chitieu, modelChiTieus);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        btnAddChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddNewKhoanChiTieu.class);
                startActivity(i);
            }
        });

        btnViewSoDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getContext(), ViewSoDu.class);
                startActivity(i);
            }
        });



        return view;
    }

}
