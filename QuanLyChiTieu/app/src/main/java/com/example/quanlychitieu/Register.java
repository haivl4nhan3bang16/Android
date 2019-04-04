package com.example.quanlychitieu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText edt_signup_user;
    EditText edt_signup_pass;
    EditText edt_signup_repass;
    EditText edtSoDu;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final MySQLiteChiTieu dbManager = new MySQLiteChiTieu(this);
        OnInIt();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onValidateForm())
                {
                    if(edt_signup_pass.getText().toString().equals(edt_signup_repass.getText().toString()))
                    {
                        dbManager.InsertLogin(edt_signup_user.getText().toString(), edt_signup_pass.getText().toString(), Integer.parseInt(edtSoDu.getText().toString()));
                        Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Thất bại rồi ku", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean onValidateForm()
    {
        if(edt_signup_user.getText().toString().length() < 1)
        {
            edt_signup_user.setError("Can be null");
            return false;
        }
        if(edt_signup_pass.getText().toString().length() < 1)
        {
            edt_signup_pass.setError("Can be null");
            return false;
        }
        if(edt_signup_repass.getText().toString().length() < 1)
        {
            edt_signup_repass.setError("Can be null");
            return false;
        }
        if(edtSoDu.getText().toString().trim() == "")
        {
            edtSoDu.setError("Can be null");
            return false;
        }

        return true;
    }
    private void OnInIt() {
        edt_signup_user = findViewById(R.id.reUser);
        edt_signup_pass = findViewById(R.id.rePass);
        edt_signup_repass = findViewById(R.id.reSePass);
        btnSignUp = findViewById(R.id.btnSignUpNew);
        edtSoDu = findViewById(R.id.edtSoDu);
    }
}
