package com.example.quanlychitieu;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewKhoanChiTieu extends AppCompatActivity {
        EditText edtadd_sotien;
        Spinner spinner_add_loaitien;
        EditText edtadd_diadiem;
        EditText edtadd_thoigian;
        EditText edtadd_lydo;
        Button btnSave;
        Button btnAddTime;
        String mSoTien;
        String mLoaiTien;
        String mDiaDiem;
        String mThoiGian;
        String mLyDo;
        MySQLiteChiTieu mySQLiteChiTieu;
        int ID;
        Button btnHuy;

        DatePickerDialog datePickerDialog;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_new_khoan_chi_tieu);
            OnInIt();

            mySQLiteChiTieu = new MySQLiteChiTieu(AddNewKhoanChiTieu.this);
            SetSpinner();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onValidateForm()) {
                        if (AddPhieuChiTieu.modelList.size() > 0) {
                            ID = AddPhieuChiTieu.modelList.get(AddPhieuChiTieu.modelList.size() - 1).getID() + 1;
                            mySQLiteChiTieu.Insert(ID, Integer.parseInt(mSoTien), mLoaiTien, mDiaDiem, mThoiGian, mLyDo);
                            finish();

                        } else if (AddPhieuChiTieu.modelList.size() == 0) {
                            ID = 0;
                            mySQLiteChiTieu.Insert(ID, Integer.parseInt(mSoTien), mLoaiTien, mDiaDiem, mThoiGian, mLyDo);
                            finish();
                        }

                    }

                }
            });
        btnAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog  = new DatePickerDialog(AddNewKhoanChiTieu.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edtadd_thoigian.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        }

    private void OnInIt() {
            edtadd_sotien = findViewById(R.id.edtSoTienChi);
            spinner_add_loaitien = findViewById(R.id.spinner_LoaiChiTieu);
            edtadd_diadiem = findViewById(R.id.edtDiaDiemChi);
            edtadd_thoigian = findViewById(R.id.edtThoiGianChi);
            edtadd_lydo = findViewById(R.id.edtLyDoChi);
            btnSave = findViewById(R.id.btnsave);
            btnAddTime = findViewById(R.id.btnAddTime);
            btnHuy = findViewById(R.id.btnHuy);
    }
    private boolean onValidateForm()
    {
        mSoTien = edtadd_sotien.getText().toString();
        mLoaiTien = spinner_add_loaitien.getSelectedItem().toString();
        mDiaDiem = edtadd_diadiem.getText().toString();
        mThoiGian = edtadd_thoigian.getText().toString();
        mLyDo = edtadd_lydo.getText().toString();
        if(mSoTien.trim().equals(""))
        {
            edtadd_sotien.setError("Can be null");
            return false;
        }
        if(mDiaDiem.trim().equals(""))
        {
            edtadd_diadiem.setError("Can be null");
            return false;
        }
        if(mThoiGian.trim().equals(""))
        {
            edtadd_thoigian.setError("Can be null");
            return false;
        }
        if(mLyDo.trim().equals(""))
        {
            edtadd_lydo.setError("Can be null");
            return false;
        }
        return true;
    }

    void SetSpinner()
    {
        Spinner spinner = findViewById(R.id.spinner_LoaiChiTieu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
