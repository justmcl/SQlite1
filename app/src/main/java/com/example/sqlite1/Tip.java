package com.example.sqlite1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.camera2.params.BlackLevelPattern;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Tip extends AppCompatActivity {
    private mdh dbHelper;
    private int max;
    private String data;
    TextView tv1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        tv1 = (TextView) findViewById(R.id.tv1);

        Intent intent= getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.e("TopPackage Name","d");//


//        Toast.makeText(Tip.this,data, Toast.LENGTH_LONG).show();

        tv1.clearComposingText();
        tv1.setText(data);
        tv1.setTextSize(36);
        tv1.setTypeface(null, Typeface.BOLD_ITALIC);






    }
}
