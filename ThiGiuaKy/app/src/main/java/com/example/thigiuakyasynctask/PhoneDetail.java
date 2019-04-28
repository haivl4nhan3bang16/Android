package com.example.thigiuakyasynctask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class PhoneDetail extends AppCompatActivity {
    EditText edtName;
    EditText edtPrice;
    EditText edtDescrip;
    EditText edtProduct;
    Button btnEdit;
    Button btnBack;
    Map<String,String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_detail);
        OnInIt();
        Intent i = getIntent();
        ModelPhone modelPhone = (ModelPhone) i.getSerializableExtra("phone");
        edtName.setText(modelPhone.getProductName());
        edtPrice.setText(String.valueOf(modelPhone.getPrice()));
        edtDescrip.setText(modelPhone.getDescription());
        edtProduct.setText(modelPhone.getProducer());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void OnInIt() {
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtDescrip = findViewById(R.id.edtDescrip);
        edtProduct = findViewById(R.id.edtProduct);
        btnEdit = findViewById(R.id.btnEdit);
        btnBack = findViewById(R.id.btnclose);

    }
}
