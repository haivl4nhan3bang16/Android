package com.example.quanlychitieu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewSoDu extends AppCompatActivity {
    TextView txtViewSoDu;
    Button btnAddSodu;
    EditText edtAddSodu;
    Button getBtnAddSodu_Success;
    SharedPreferences sharedPreferences;
    MySQLiteChiTieu mySQLiteChiTieu;
    TextView sudo;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_so_du);
        txtViewSoDu = findViewById(R.id.txtViewSoDu);
        btnAddSodu = findViewById(R.id.btnadd_SoDu);
        edtAddSodu = findViewById(R.id.edtAdd_SoDu);
        getBtnAddSodu_Success = findViewById(R.id.btnAdd_success);
        btnBack = findViewById(R.id.btnBack);

        mySQLiteChiTieu = new MySQLiteChiTieu(this);
        sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE);
        final int mmID = sharedPreferences.getInt("id", 0);


        txtViewSoDu.setText(String.valueOf(mySQLiteChiTieu.getSoDu(mmID) - mySQLiteChiTieu.SumAll()));
        sudo = findViewById(R.id.sudo_tong);
        sudo.setText(String.valueOf(mySQLiteChiTieu.SumAll()) + " đồng");


        btnAddSodu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAddSodu.setEnabled(true);
                getBtnAddSodu_Success.setEnabled(true);
            }
        });
        getBtnAddSodu_Success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = txtViewSoDu.getText().toString();
                int num1 = Integer.parseInt(a);
                String b = edtAddSodu.getText().toString();
                int num2 = Integer.parseInt(b);
                int Sum = num1 + num2;
                txtViewSoDu.setText(String.valueOf(Sum));
                edtAddSodu.setText("");
                mySQLiteChiTieu.Update(mmID, txtViewSoDu.getText().toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
