package com.example.loginpost;

import android.content.Intent;
import android.graphics.Bitmap;
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

public class StudentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<StudentModel> models = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        new StudentAsyncTask(new IView() {
            @Override
            public void onRequestSuccess(Bitmap bitmap) {

            }

            @Override
            public void onGetDataSuccess(JSONArray jsonArray) {
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        StudentModel model = new StudentModel();
                        model.setCode(jsonObject.getString("student_code"));
                        model.setName(jsonObject.getString("name"));
                        model.setAge(Integer.valueOf(jsonObject.getString("age")));
                        model.setClasss(jsonObject.getString("class"));
                        model.setEmail(jsonObject.getString("email"));
                        model.setID(Integer.valueOf(jsonObject.getString("id")));
                        models.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                MyViewAdapter adapter = new MyViewAdapter(StudentActivity.this,R.layout.student_item, models);
                recyclerView.setAdapter(adapter);
            }
        }).execute("http://www.vidophp.tk/api/student/getall");
    }
}
