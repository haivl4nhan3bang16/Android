package com.example.thigiuakyasynctask;

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
    EditText edtUser;
    EditText edtPass;
    Button btnLogin;
    String mUser = "";
    String mPass = "";
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
                    mMap.put("user_name",mUser);
                    mMap.put("password",mPass);
                    new LoginAsyncTask(MainActivity.this, new ILoginView() {
                        @Override
                        public void onLoginSuccess(String m, int userID) {

                            Intent intent = new Intent(MainActivity.this, PhoneActivity.class);
                            intent.putExtra("id_us",userID);
                            Toast.makeText(MainActivity.this,m, Toast.LENGTH_SHORT).show();
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
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
    }
    public boolean onValidateForm()
    {
        mUser = edtUser.getText().toString();
        mPass = edtPass.getText().toString();
        if(mUser.equals(""))
        {
            edtUser.setError("cc");
            return false;
        }
        if(mPass.equals(""))
        {
            edtPass.setError("cc");
            return false;
        }
        return true;
    }
}
