package com.manbu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String StartTime = "2016-08-29 00:00:01";
    private Date fdate, odate;
    private int Week = 0;
    public static String TAG = "MainActivity";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        fdate = stringToDate(StartTime);
        odate = new Date();
        Week = Caculate(daysOfTwo(fdate, odate),7);
        Log.d(TAG, "onCreate: " + Week);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.text_time);
        mTextView.setText(Week + "");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private Date stringToDate(String date) {
        Date result = null;
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            Log.d(TAG, "stringToDate: 计算日期出错了");
        }
        return result;
    }
    private int daysOfTwo(Date fDate, Date oDate) {
        long day = oDate.getTime()-fDate.getTime();
        return Caculate(day,24*60*60*1000);
    }

    private int Caculate(int dividend,int divisor){
        int n;
        n=dividend/divisor;
        if(dividend%divisor!=0)
            n=n+1;
        return n;
    }
    private int Caculate(float dividend,float divisor){
        int n;
        n= (int) (dividend/divisor);
        if(dividend%divisor!=0)
            n=n+1;
        return n;
    }

}
