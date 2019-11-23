package com.example.sqlite1;

import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class MyService extends Service {
    public MyService() {
    }

    public String getLauncherPackageName(Context context)
    {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);
        if(res.activityInfo == null)
        {
            return "";
        }
        //如果是不同桌面主题，可能会出现某些问题，这部分暂未处理
        if(res.activityInfo.packageName.equals("android"))
        {
            return "";
        }
        else
        {
            return res.activityInfo.packageName;
        }
    }
    private Timer mTimer;
    private UsageStatsManager mUsageStatsManager;
    public String string1="gg";

    private mdh dbHelper;
    private int max=1;
    private String text;
    private SQLiteDatabase db2;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    private void getTopApp() {
//        UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);//usagestats

        mUsageStatsManager =(UsageStatsManager)getSystemService(Context.USAGE_STATS_SERVICE);

        long time = System.currentTimeMillis();
        List<UsageStats> usageStatsList = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time - 400, time);





        if (usageStatsList != null && !usageStatsList.isEmpty()) {
            SortedMap<Long, UsageStats> usageStatsMap = new TreeMap<>();
            for (UsageStats usageStats : usageStatsList) {
                usageStatsMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (!usageStatsMap.isEmpty()) {
                String topPackageName = usageStatsMap.get(usageStatsMap.lastKey()).getPackageName();

                if (getLauncherPackageName(MyService.this).equals(topPackageName) || "com.android.systemui".equals(topPackageName)|| "com.example.sqlite1".equals(topPackageName)|| string1.equals(topPackageName)) {
                    Log.e("A", string1);//
                    Log.e("B", topPackageName);//
                    return;
                }

                Log.e("TopPackage Name", string1);//
                Log.e("TopPackage Name", topPackageName);//

                //模拟home键点击
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                startActivity(intent);

                //启动提示页面

//                Toast.makeText(MyService.this, string1, Toast.LENGTH_LONG).show();


                dbHelper = new mdh(this, "BookStore.db", null, 1);

                SQLiteDatabase db2 = dbHelper.getWritableDatabase(); // 查询 Book 表中所有的数据
                Cursor cursor1 =db2.query("Book",new String [] {"id"},null,null,null,null,null);
//                        query("Book", null, null, null, null, null, null;
                max=-1;
                while (cursor1.moveToNext()) {
//                    cursor1.moveToPrevious();
                    max++;


                }
                Log.e("mmmmmmmmmax",String.valueOf(max));

                cursor1.close();


                int randomnum = (int) (Math.random() * max+1);
//                randomnum=5;
                String str2=String.valueOf(randomnum);
                Log.e("RAN",str2);


                Cursor cursor2 =db2.query("Book",new String[] {"name"}, "id=?",new String[] {str2},null,null,null);
                if (cursor2.moveToFirst()) {
                    text=cursor2.getString(cursor2.getColumnIndex("name"));
                    Log.e("TopPackage Name",text);//
                }

//                int nameColumnIndex = cursor2.getColumnIndex("id");
//                String text = cursor2.getString(nameColumnIndex);
                cursor2.close();


//                Looper.prepare();//增加部分



                Intent intent1 = new Intent(this, Tip.class);
                intent1.putExtra("extra_data", text);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent1);
                string1=topPackageName;
                Log.e("TopPackage Name","c");//

//                Looper.loop();//增加部分

            }
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();

        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                getTopApp();
            }
        };

        mTimer.schedule(task, 1000, 500);




    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
