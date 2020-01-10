package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean bImageShow;

    private ProgressDialog mProgressDialog;

    private Tesseract mOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);
        textView.setVisibility(View.GONE);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ocr_test);

        bImageShow = true;

        mOCR = new Tesseract(this, "eng");
    }

    public void onButtonClick(View v) {
        TextView textView = findViewById(R.id.text);
        ImageView imageView = findViewById(R.id.imageView);

        if (bImageShow) {
            doOCR(((BitmapDrawable)imageView.getDrawable()).getBitmap());

            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }

        bImageShow = !bImageShow;
    }

    private void doOCR(final Bitmap bitmap) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, "Processing",
                                          "Doing OCR...", true);
        } else {
            mProgressDialog.show();
        }
        final TextView textView = findViewById(R.id.text);
        new Thread(new Runnable() {
            public void run() {
                final String resultText = mOCR.getOCRResult(bitmap);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resultText != null && !resultText.equals("")) {
                            textView.setText(resultText);
                        } else {
                            textView.setText("Error");
                        }
                        mProgressDialog.dismiss();
                    }
                });
            }
        }).start();
    }
}
