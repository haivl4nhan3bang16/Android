package com.example.thigiuakyasynctask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ModelPhone> modelPhones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        Map<String, String> map = new HashMap<>();
        map.put("id", "20");
        new PhoneAsyncTask(PhoneActivity.this, new IData() {
            @Override
            public void onDataSuccess(JSONArray jsonArray) {
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ModelPhone model = new ModelPhone();
                        model.setProductName(jsonObject.getString("product_name"));
                        model.setPrice(jsonObject.getInt("price"));
                        model.setDescription(jsonObject.getString("description"));
                        model.setProducer(jsonObject.getString("producer"));
                        model.setID(Integer.valueOf(jsonObject.getString("id")));
                        modelPhones.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                MyViewAdapter adapter = new MyViewAdapter(PhoneActivity.this,R.layout.phone_item, modelPhones);
                recyclerView.setAdapter(adapter);
            }
        },map).execute("http://www.vidophp.tk/api/account/getdata");





    }
}
