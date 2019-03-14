package com.example.ngoquocduy_1706020017_11th1c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText edtUser;
    EditText edtPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OnInIt();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oncheck())
                {
                    if(edtUser.getText().toString().equals("1") && edtPass.getText().toString().equals("1"))
                    {

                        Toast.makeText(Login.this, "Login Success", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Login.this, MainActivity.class);
                        MainActivity.ListPhone.clear();
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Something wrong here !!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public boolean oncheck()
    {
        if(edtUser.getText().toString().length() < 1)
        {
            edtUser.setError("Fill here please!!!!");

            return false;
        }
        else if(edtPass.getText().toString().length() < 1)
        {
            edtPass.setError("Fill here please !!!!");

            return false;
        }
        return true;
    }



    private void OnInIt() {
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnlogin);
    }
}
