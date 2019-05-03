package com.example.thigiuakyasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {
    EditText edt_add_name;
    EditText edt_add_price;
    EditText edt_add_descrip;
    EditText edt_add_producer;
    Button btnAddNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        OnInIt();
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Map<String, String> mMap = new HashMap<>();
                    mMap.put("name", edt_add_name.getText().toString());
                    mMap.put("number", edt_add_price.getText().toString());
                    mMap.put("code", edt_add_descrip.getText().toString());
                    mMap.put("description", edt_add_producer.getText().toString());
                    mMap.put("user_id", String.valueOf(PhoneActivity.id));
                    new PhoneAsyncTask(mMap, new IData() {
                        @Override
                        public void onDataSuccess(String s, JSONArray jsonArray) {

                        }
                    }).execute("http://www.vidophp.tk/api/account/dataaction?context=insert");
                    finish();

            }
        });
    }

    private void OnInIt() {
        edt_add_name = findViewById(R.id.edtName);
        edt_add_price = findViewById(R.id.edtPrice);
        edt_add_descrip = findViewById(R.id.edtDescrip);
        edt_add_producer = findViewById(R.id.edtProduct);
        btnAddNew = findViewById(R.id.btnAddNew);

    }
}
