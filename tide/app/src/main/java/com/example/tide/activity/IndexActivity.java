package com.example.tide.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import com.example.tide.R;
import com.example.tide.fragment.WakeupFragment;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView indexSign;
    private ImageView indeximg1;
    private ImageView indeximg2;
    private ImageView indeximg3;
    private ImageView indeximg4;
    private ImageView indexperson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        indexSign=findViewById(R.id.index_sign);
        indeximg1=findViewById(R.id.index_img1);
        indeximg2=findViewById(R.id.index_img2);
        indeximg3=findViewById(R.id.index_img3);
        indeximg4=findViewById(R.id.index_img4);
        indexperson = findViewById(R.id.index_person);

        indeximg1.setOnClickListener(this);
        indeximg2.setOnClickListener(this);
        indeximg3.setOnClickListener(this);
        indeximg4.setOnClickListener(this);
        indexperson.setOnClickListener(this);


        getDate(indexSign);
        rectRoundBitmap();
    }

    private void getDate(TextView tv){
        Date d = new Date();
        if(d.getHours()<11){
            tv.setText("早上好");
        }else if(d.getHours()<13){
            tv.setText("中午好");
        }else if(d.getHours()<18){
            tv.setText("下午好");
        }else if(d.getHours()<24){
            tv.setText("晚上好");
        }
    }
    private void rectRoundBitmap(){
        //得到资源文件的BitMap
        Bitmap image1= BitmapFactory.decodeResource(getResources(),R.drawable.indexbtn1);
        Bitmap image2= BitmapFactory.decodeResource(getResources(),R.drawable.indexbtn2);
        Bitmap image3= BitmapFactory.decodeResource(getResources(),R.drawable.indexbtn3);
        Bitmap image4= BitmapFactory.decodeResource(getResources(),R.drawable.indexbtn4);
        //创建RoundedBitmapDrawable对象
        RoundedBitmapDrawable roundImg1 = RoundedBitmapDrawableFactory.create(getResources(),image1);
        RoundedBitmapDrawable roundImg2 = RoundedBitmapDrawableFactory.create(getResources(),image2);
        RoundedBitmapDrawable roundImg3 = RoundedBitmapDrawableFactory.create(getResources(),image3);
        RoundedBitmapDrawable roundImg4 = RoundedBitmapDrawableFactory.create(getResources(),image4);
        //抗锯齿
        roundImg1.setAntiAlias(true);
        roundImg2.setAntiAlias(true);
        roundImg3.setAntiAlias(true);
        roundImg4.setAntiAlias(true);
        //设置圆角半径
        roundImg1.setCornerRadius(50);
        roundImg2.setCornerRadius(50);
        roundImg3.setCornerRadius(50);
        roundImg4.setCornerRadius(50);
        //设置显示图片
        indeximg1.setImageDrawable(roundImg1);
        indeximg2.setImageDrawable(roundImg2);
        indeximg3.setImageDrawable(roundImg3);
        indeximg4.setImageDrawable(roundImg4);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){

            case R.id.index_img1:

                intent.setClass(IndexActivity.this,NoticeActivity.class);

                break;
            case R.id.index_img2:
                intent.setClass(IndexActivity.this,WakeupActivity.class);
                //intent.putExtra("id",1);

                break;
            case R.id.index_img3:
                intent.setClass(IndexActivity.this,RelaxActivity.class);
                break;
            case R.id.index_img4:
                break;
            case R.id.index_person:
                intent.setClass(IndexActivity.this,Login.class);
                break;
        }
        startActivity(intent);
    }
}
