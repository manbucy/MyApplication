package com.manbu.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.OnViewTapListener;
import me.relex.photodraweeview.PhotoDraweeView;

public class MainActivity extends AppCompatActivity{
    private String StartTime = "2017-02-27 00:00:01";
    private Date fdate, odate;
    private int Week = 0;
    public static String TAG = "MainActivity";
    private TextView mTextView;
    private PhotoDraweeView mPhotoDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        fdate = stringToDate(StartTime);
        odate = new Date();
        Week = Caculate(daysOfTwo(fdate, odate),7);
        Log.d(TAG, "onCreate: " + Week);

        mPhotoDraweeView = (PhotoDraweeView) findViewById(R.id.photo_drawee_view);
        mPhotoDraweeView.setPhotoUri(Uri.parse("res:///" + R.drawable.timetable));
        mPhotoDraweeView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override public void onPhotoTap(View view, float x, float y) {
                Toast.makeText(view.getContext(), "onPhotoTap :  x =  " + x + ";" + " y = " + y,
                        Toast.LENGTH_SHORT).show();
            }
        });
        mPhotoDraweeView.setOnViewTapListener(new OnViewTapListener() {
            @Override public void onViewTap(View view, float x, float y) {
                Toast.makeText(view.getContext(), "onViewTap", Toast.LENGTH_SHORT).show();
            }
        });

        mPhotoDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "onLongClick", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.text_time);
        mTextView.setText(Week + "");
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
        if(day<0){
            return 0;
        }else{
            return Caculate(day,24*60*60*1000);
        }
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
