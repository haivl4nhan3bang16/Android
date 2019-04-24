package com.example.loginpost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassWord;
    Button btnLogin;
    String mUserName = "";
    String mPassword = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnInIt();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onValidateForm())
                {
                    Map<String,String> mMap = new HashMap<>();
                    mMap.put("user_name",mUserName);
                    mMap.put("password",mPassword);
                    new LoginAsyncTask(MainActivity.this,new ILoginView() {
                        @Override
                        public void onLoginSuccess(String m) {
                            Toast.makeText(MainActivity.this,m, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,Image.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onLoginFail(String m) {
                            Toast.makeText(MainActivity.this,m,Toast.LENGTH_SHORT).show();
                        }
                    },mMap).execute("http://www.vidophp.tk/api/account/signin");

                }
            }
        });
    }

    private void OnInIt() {
        edtUserName = findViewById(R.id.edtUser);
        edtPassWord = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
    }
    private boolean onValidateForm(){
        mUserName = edtUserName.getText().toString();
        if (mUserName.length() < 1){
            edtUserName.setError("Username field cannot be blank");
            return false;
        }

        mPassword = edtPassWord.getText().toString();
        if (mPassword.length() < 1){
            edtPassWord.setError("Password field cannot be blank");
            return  false;
        }
        return true;
    }
}
