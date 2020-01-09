package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);
        textView.setVisibility(View.GONE);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.sample_image);

        bImageShow = true;
    }

    public void onButtonClick(View v) {
        TextView textView = findViewById(R.id.text);
        ImageView imageView = findViewById(R.id.imageView);

        if (bImageShow) {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }

        bImageShow = !bImageShow;
    }

    private boolean bImageShow;

}
