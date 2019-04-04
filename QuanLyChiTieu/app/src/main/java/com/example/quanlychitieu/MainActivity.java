package com.example.quanlychitieu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtUser;
    EditText edtPass;
    Button btnLogin;
    Button btnRegis;
    String mUser;
    String mPass;
    public static MySQLiteChiTieu mySQLiteChiTieu;
    int ID = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySQLiteChiTieu= new MySQLiteChiTieu(this);
        OnInIt();
        CheckLoged();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oncheck())
                {

                    if(mySQLiteChiTieu.CheckLogin(edtUser.getText().toString(), edtPass.getText().toString()))
                    {
                        ID = mySQLiteChiTieu.getIDLogin(edtUser.getText().toString(), edtPass.getText().toString());
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, ListFragmentChiTieu.class);
                        startActivity(i);
                        editor.putBoolean("login", true);
                        editor.putInt("id", ID);
                        editor.commit();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, Register.class);
                startActivity(i);
            }
        });
    }



    public boolean oncheck()
    {
        mUser = edtUser.getText().toString();
        mPass = edtPass.getText().toString();
        if(mUser.length() < 1)
        {
            edtUser.setError("Fill here please!!!!");

            return false;
        }
        else if(mPass.length() < 1)
        {
            edtPass.setError("Fill here please !!!!");

            return false;
        }
        return true;
    }

    private void OnInIt() {
        edtUser = findViewById(R.id.login_user);
        edtPass = findViewById(R.id.login_pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegis = findViewById(R.id.btnRegister);
        sharedPreferences = getSharedPreferences("save", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    void CheckLoged()
    {
        if(sharedPreferences.getBoolean("login", false))
        {
            startActivity(new Intent(MainActivity.this, ListFragmentChiTieu.class));
        }

    }

}
