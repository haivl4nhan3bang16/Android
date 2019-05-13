package com.example.detaicuoiky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailProduct extends AppCompatActivity {
    TextView txt_dt_id;
    EditText edt_dt_name;
    EditText edt_dt_price;
    EditText edt_dt_origin;
    Button btnEdit;
    Button btnSave;

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        OnIt();
        Disable();
        final Intent intent = getIntent();
        txt_dt_id.setText(intent.getStringExtra("id_product"));
        edt_dt_name.setText(intent.getStringExtra("name_product"));
        edt_dt_price.setText(intent.getStringExtra("price_product"));
        edt_dt_origin.setText(intent.getStringExtra("id_origin"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enable();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disable();
                MainActivity.myRef = MainActivity.db.getReference("product");
                MainActivity.myRef.child(txt_dt_id.getText().toString()).child("productName").setValue(edt_dt_name.getText().toString());
                MainActivity.myRef.child(txt_dt_id.getText().toString()).child("price").setValue(edt_dt_price.getText().toString());
                MainActivity.myRef.child(txt_dt_id.getText().toString()).child("origin").setValue(edt_dt_origin.getText().toString());
                finish();
            }
        });
    }

    private void OnIt() {
        txt_dt_id = findViewById(R.id.txt_detail_id);
        edt_dt_name = findViewById(R.id.edt_detail_name);
        edt_dt_price = findViewById(R.id.edt_detail_price);
        edt_dt_origin = findViewById(R.id.edt_detail_origin);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);
    }

    private void Enable()
    {
        edt_dt_name.setEnabled(true);
        edt_dt_price.setEnabled(true);
        edt_dt_origin.setEnabled(true);
    }

    private void Disable()
    {
        edt_dt_name.setEnabled(false);
        edt_dt_price.setEnabled(false);
        edt_dt_origin.setEnabled(false);
    }
}
