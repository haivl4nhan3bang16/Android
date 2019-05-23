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

import java.util.ArrayList;

public class DetailProduct extends AppCompatActivity {
    EditText edt_dt_code;
    EditText edt_dt_name;
    EditText edt_dt_credits;
    EditText edt_dt_descrip;
    Button btnEdit;
    Button btnSave;
    Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        OnIt();
        Enable();
        final Intent intent = getIntent();
        model = (Model) intent.getSerializableExtra("data");
        edt_dt_code.setText(model.getSubject_code());
        edt_dt_name.setText(model.getSubject_name());
        edt_dt_credits.setText(String.valueOf(model.getCredits()));
        edt_dt_descrip.setText(model.getDescription());


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disable();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disable();
                model.setSubject_code(edt_dt_code.getText().toString());
                model.setSubject_name(edt_dt_name.getText().toString());
                model.setCredits(Integer.parseInt(edt_dt_credits.getText().toString()));
                model.setDescription(edt_dt_descrip.getText().toString());
                DatabaseReference chil = MainActivity.myRef.child(model.getKeyParent());
                chil.setValue(model);
                finish();
            }
        });
    }

    private void OnIt() {
        edt_dt_code = findViewById(R.id.edt_detail_subcode);
        edt_dt_name = findViewById(R.id.edt_detail_subname);
        edt_dt_credits = findViewById(R.id.edt_detail_credits);
        edt_dt_descrip = findViewById(R.id.edt_detail_Descrip);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);

    }

    private void Enable()
    {
        edt_dt_code.setEnabled(false);
        edt_dt_name.setEnabled(false);
        edt_dt_credits.setEnabled(false);
        edt_dt_descrip.setEnabled(false);
    }

    private void Disable()
    {
        edt_dt_code.setEnabled(true);
        edt_dt_name.setEnabled(true);
        edt_dt_credits.setEnabled(true);
        edt_dt_descrip.setEnabled(true);
    }
}
