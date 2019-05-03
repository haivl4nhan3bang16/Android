package com.example.thigiuakyasynctask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    static MyViewAdapter adapter;
    static List<ModelPhone> modelPhones = new ArrayList<>();
    static Map<String, String> map = new HashMap<>();
    static int id;
    Button btnAdd;
    @Override
    protected void onResume() {
        super.onResume();
        Update();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        recyclerView = findViewById(R.id.recycle_view);
        btnAdd = findViewById(R.id.btnAddItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Intent intent = getIntent();
        id = intent.getIntExtra("id_us", 0);
        map.put("id", String.valueOf(id));

        adapter = new MyViewAdapter(PhoneActivity.this,R.layout.phone_item, modelPhones);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PhoneActivity.this, AddProduct.class);
                startActivity(i);
            }
        });
    }
    public static void Update(){
        modelPhones.clear();
        new PhoneAsyncTask(map, new IData() {
            @Override
            public void onDataSuccess(String s, JSONArray jsonArray) {
                for (int i = 0; i <  jsonArray.length(); i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ModelPhone model = new ModelPhone();
                        model.setIDUser(id);
                        model.setID(jsonObject.getInt("id"));
                        model.setProductName(jsonObject.getString("product_name"));
                        model.setPrice(jsonObject.getInt("price"));
                        model.setDescription(jsonObject.getString("description"));
                        model.setProducer(jsonObject.getString("producer"));
                        modelPhones.add(model);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }




        }).execute("http://www.vidophp.tk/api/account/getdata");


    }
}
