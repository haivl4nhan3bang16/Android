package com.example.ngoquocduy_1706020017_11th1c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationContact extends AppCompatActivity {
    TextView txtName;
    TextView txtMaDT;
    TextView txtPrice;
    TextView txtDescrip;
    Button btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_contact);
        OnInIt();
        final Intent intent = getIntent();
        txtName.setText(intent.getStringExtra("name"));
        txtMaDT.setText(intent.getStringExtra("ma"));
        txtPrice.setText(intent.getStringExtra("price"));
        txtDescrip.setText(intent.getStringExtra("descrip"));
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void OnInIt() {
        txtName = findViewById(R.id.information_namephone);
        txtMaDT = findViewById(R.id.information_MaDT);
        txtPrice = findViewById(R.id.information_price);
        txtDescrip = findViewById(R.id.information_mota);
        btnclose = findViewById(R.id.btnclose);

    }
}
