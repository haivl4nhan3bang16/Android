package com.example.quanlychitieu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment {
    ListView listView_history;
    List<ModelChiTieu> modelChiTieus = new ArrayList<>();
    static ContactAdapterChiTieu adapter;
    static MySQLiteChiTieu mySQLiteChiTieu;

    @Override
    public void onResume()
    {
        super.onResume();
        modelChiTieus.clear();
        AddPhieuChiTieu.modelList.clear();
        AddPhieuChiTieu.modelList.addAll(mySQLiteChiTieu.getAll());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < AddPhieuChiTieu.modelList.size(); i++){
            Date getDay = new Date();
            try {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(AddPhieuChiTieu.modelList.get(i).getThoiGianChi());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(getDay.getMonth() <  date.getMonth()){
                modelChiTieus.add(AddPhieuChiTieu.modelList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    public History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listView_history = view.findViewById(R.id.listview_history);

        mySQLiteChiTieu = new MySQLiteChiTieu(getContext());
        AddPhieuChiTieu.modelList = mySQLiteChiTieu.getAll();
        if(AddPhieuChiTieu.modelList.isEmpty())
        {
            AddPhieuChiTieu.modelList = mySQLiteChiTieu.getAll();
        }
        adapter = new ContactAdapterChiTieu(getContext(), R.layout.custum_chitieu, modelChiTieus);
        listView_history.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

}
