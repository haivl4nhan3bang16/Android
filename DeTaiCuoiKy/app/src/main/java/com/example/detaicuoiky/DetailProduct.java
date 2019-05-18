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
    EditText edt_dt_code;
    EditText edt_dt_name;
    EditText edt_dt_credits;
    EditText edt_dt_descrip;
    Button btnEdit;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        OnIt();
        Enable();
        final Intent intent = getIntent();
        edt_dt_code.setText(intent.getStringExtra("code"));
        edt_dt_name.setText(intent.getStringExtra("name"));
        edt_dt_credits.setText(intent.getStringExtra("credits"));
        edt_dt_descrip.setText(intent.getStringExtra("description"));

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
                MainActivity.myRef = MainActivity.db.getReference("AdvancedAndroidFinalTest");
                MainActivity.myRef.child(edt_dt_code .getText().toString()).child("subject_code").setValue(edt_dt_code.getText().toString());
                MainActivity.myRef.child(edt_dt_code.getText().toString()).child("subject_name").setValue(edt_dt_name.getText().toString());
                MainActivity.myRef.child(String.valueOf(Integer.parseInt(edt_dt_code.getText().toString()))).child("credits").setValue(Integer.parseInt(edt_dt_credits.getText().toString()));
                MainActivity.myRef.child(edt_dt_code.getText().toString()).child("description").setValue(edt_dt_descrip.getText().toString());
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
