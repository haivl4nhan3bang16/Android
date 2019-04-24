package com.example.loginpost;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Image extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image2);
        OnInIt();
        new MyAsyncTask(new IView() {
            @Override
            public void onRequestSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }).execute("http://www.vidophp.tk/api/imageservice/getrandomimage");
    }

    private void OnInIt() {
        imageView = findViewById(R.id.img_view);
    }
}
