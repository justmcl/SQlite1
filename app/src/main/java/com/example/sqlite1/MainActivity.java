package com.example.sqlite1;

import android.app.usage.UsageStatsManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private mdh dbHelper;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    List<Person> personList=new ArrayList<Person>();

    TextView tv1;
    TextView tv2;
    Calendar cal;
    String year;
    String month;
    String day;
    String hour;
    String minute;
    String second;
    String my_time_1;
    String my_time_2;
    int int1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivityForResult(intent,3);

        dbHelper = new mdh(this, "BookStore.db", null, 1);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);


        Button CreatDatebase = (Button) findViewById(R.id.bt1);
        Button query = (Button) findViewById(R.id.bt2);
        Button update = (Button) findViewById(R.id.bt7);
        Button creat = (Button) findViewById(R.id.bt3);
        Button time = (Button) findViewById(R.id.bt4);
        Button search = (Button) findViewById(R.id.bt5);
        Button delete = (Button) findViewById(R.id.bt6);
        Button start = (Button) findViewById(R.id.bt8);
        Button stop = (Button) findViewById(R.id.bt9);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        CreatDatebase.setOnClickListener(this);
        search.setOnClickListener(this);
        delete.setOnClickListener(this);
        CreatDatebase.setOnClickListener(this);
        query.setOnClickListener(this);
        creat.setOnClickListener(this);
        time.setOnClickListener(this);
        update.setOnClickListener(this);


        LinearLayout ll=(LinearLayout) findViewById(R.id.ll);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);

        LineChart chart = (LineChart) findViewById(R.id.chart);


        //YourData[] dataObjects = ...;


        List<Entry> values = new ArrayList<Entry>();
        values.add(new Entry(5, 50));
        values.add(new Entry(10, 66));
        values.add(new Entry(15, 120));
        values.add(new Entry(20, 30));
        values.add(new Entry(35, 10));
        values.add(new Entry(40, 110));
        values.add(new Entry(45, 30));
        values.add(new Entry(50, 160));
        values.add(new Entry(100, 30));

        /*for (YourData data : dataObjects) {

            // turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));

        }*/

        LineDataSet dataSet1 = new LineDataSet(values, "Label"); // add entries to dataset
        dataSet1.setColor(Color.BLACK);
        dataSet1.setValueTextColor(Color.BLUE); // styling, ...

        LineData lineData = new LineData(dataSet1);
        chart.setData(lineData);
        chart.invalidate(); // refresh

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                String inputText = et2.getText().toString();
                String inputText3 = et1.getText().toString();
                String inputText4 = et3.getText().toString();


//                if(inputText3!=null) {
//                    ContentValues values = new ContentValues(); // 开始组装第一条数据
//                    values.put("id", inputText3);
//                    values.put("name", inputText);
//                    values.put("author", "Dan Brown");
//                    values.put("pages", 454);
//                    values.put("price", 16.96);
//
//                    db1.insert("Book", null, values); // 插入第一条数据
//                    values.clear();
//                }
//                else{
                    ContentValues values = new ContentValues(); // 开始组装第一条数据
                    values.put("name", inputText);
                    values.put("author", "Dan Brown");
                    values.put("pages", inputText4);
                    values.put("price", 16.96);

                    db1.insert("Book", null, values); // 插入第一条数据
                    values.clear();
                    Toast.makeText(MainActivity.this, "my_time_1", Toast.LENGTH_LONG).show();
//                }

                break;
            case R.id.bt2:
                LinearLayout ll=(LinearLayout) findViewById(R.id.ll);
                ll.removeAllViews();
                SQLiteDatabase db2 = dbHelper.getWritableDatabase(); // 查询 Book 表中所有的数据
                Cursor cursor = db2.query("Book", null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    int id =cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages =cursor.getInt(cursor.getColumnIndex("pages"));
                    float prices =(float)13.14;//cursor.getInt(cursor.getColumnIndex("prices"));
                    Person p=new Person(id,name, author, pages, prices);

                    TextView tv=new TextView(this);
                    //2.把人物的信息设置为文本的内容
                    tv.setText(p.toString());
                    tv.setTextSize(18);
                    //3.把TextView设置成线性布局的子节点
                    ll.addView(tv);

                   //personList.add(p);
                }

                /*LinearLayout ll=(LinearLayout) findViewById(R.id.ll);
                //把数据显示到屏幕
                for(Person p:personList)
                {
                    //1.集合中每有一条数据，就new一个TextView
                    TextView tv=new TextView(this);
                    //2.把人物的信息设置为文本的内容
                    tv.setText(p.toString());
                    tv.setTextSize(18);
                    //3.把TextView设置成线性布局的子节点
                    ll.addView(tv);
                }*/

                break;
            case R.id.bt3://CREAT
                //dbHelper.getWritableDatabase();
                Toast.makeText(MainActivity.this, my_time_1, Toast.LENGTH_LONG).show();
                break;
            case R.id.bt4://TIME
                cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                year = String.valueOf(cal.get(Calendar.YEAR));
                month = String.valueOf(cal.get(Calendar.MONTH)+1);
                day = String.valueOf(cal.get(Calendar.DATE));
                if (cal.get(Calendar.AM_PM) == 0)
                    hour = String.valueOf(cal.get(Calendar.HOUR));
                else
                    hour = String.valueOf(cal.get(Calendar.HOUR)+12);
                minute = String.valueOf(cal.get(Calendar.MINUTE));
                second = String.valueOf(cal.get(Calendar.SECOND));
                my_time_1 = " "+year + "-" + month + "-" + day+" ";
                my_time_2 = " "+hour + "-" + minute + "-" + second+" ";
                tv1.setText(my_time_1);
                tv2.setText(my_time_2);
                break;
            case R.id.bt6://SEARCH
                String inputText2 = et1.getText().toString();
                int i1 = Integer.parseInt(inputText2);
                SQLiteDatabase db6 = dbHelper.getWritableDatabase();
                db6.delete("Book", "id = ?",new String[] { inputText2 });
                break;
            case R.id.bt7://UPDATE
                SQLiteDatabase db3 = dbHelper.getWritableDatabase();
                String inputText1 = et2.getText().toString();
                String inputText5 = et1.getText().toString();
                String inputText6 = et3.getText().toString();

                int i = Integer.parseInt(inputText5);

                ContentValues values1 = new ContentValues(); // 开始组装第一条数据
                values1.put("id", i);
                values1.put("name", inputText1);
                values1.put("author", "Dan Brown");
                values1.put("pages", inputText6);
                values1.put("price", 16.96);

                db3.insert("Book", null, values1); // 插入第一条数据
                values1.clear();
                ContentValues values2 = new ContentValues();
                values2.put("name", inputText1);
                ContentValues values3 = new ContentValues();
                values3.put("pages", inputText6);
                db3.update("Book", values2, "id = ?", new String[] { inputText5});
                db3.update("Book", values3, "id = ?", new String[] { inputText5});
            case R.id.bt8:
                Intent startIntent = new Intent(this, MyService.class); startService(startIntent); // 启动服务reak;
            case R.id.bt9:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); // 停止服务
                break;

        }
    }

}
