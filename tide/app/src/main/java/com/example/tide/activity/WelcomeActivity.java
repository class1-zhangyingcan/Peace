package com.example.tide.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tide.adapter.WelcomeViewPagerAdapter;
import com.example.tide.utils.WelcomeVideo;

import java.util.ArrayList;
import java.util.List;
import com.example.tide.R;

public class WelcomeActivity extends Activity {
    private ViewPager viewPager;
    private List<View> alist;
    private WelcomeViewPagerAdapter mAdapter;
    private TextView tv1;
    private TextView tv2;
    private Button btn;
    private ImageView circle1;
    private ImageView circle2;
    private ImageView circle3;
    private WelcomeVideo videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_guide);

        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_title_about);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        //进入按钮
        btn = findViewById(R.id.welcome_guide_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到首页
                Intent i=new Intent(WelcomeActivity.this,about.class);
                startActivity(i);
            }
        });

        //背景视频
        videoView = findViewById(R.id.videoView);
        initView();

        //viewpager的设置
        viewPager = (ViewPager) findViewById(R.id.welcome_pager);
        alist = new ArrayList<View>();

        //设置第一个点未激活状态
        circle1 = findViewById(R.id.circle1);
        circle2 = findViewById(R.id.circle2);
        circle3 = findViewById(R.id.circle3);
        circle1.setImageResource(R.drawable.welcome_circle_later);

        //循环添加viewpager
        for (int i = 0; i < 6; i++) {
            alist.add(getGuidePage(i));
        }

        //实现方法类
        mAdapter = new WelcomeViewPagerAdapter(alist);
        //添加
        viewPager.setAdapter(mAdapter);

        //滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滚动过程中实现
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //滚动成功后实现
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        btn.setVisibility(View.GONE);
                        circle1.setImageResource(R.drawable.welcome_circle_later);
                        circle2.setImageResource(R.drawable.welcome_circle);
                        circle3.setImageResource(R.drawable.welcome_circle);
                        break;
                    case 1:
                        btn.setVisibility(View.GONE);
                        circle1.setImageResource(R.drawable.welcome_circle);
                        circle2.setImageResource(R.drawable.welcome_circle_later);
                        circle3.setImageResource(R.drawable.welcome_circle);

                        break;
                    case 2:
                        btn.setVisibility(View.VISIBLE);
                        circle1.setImageResource(R.drawable.welcome_circle);
                        circle2.setImageResource(R.drawable.welcome_circle);
                        circle3.setImageResource(R.drawable.welcome_circle_later);
                        break;
                }
            }
            //滚动成功前，即手指按下屏幕时
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private View getGuidePage(int i) {
        // TODO 自动生成的方法存根
        View v = View.inflate(this, R.layout.welcome_view, null);
        ImageView ivGuidePage = (ImageView) v.findViewById(R.id.iv);
        tv1 = v.findViewById(R.id.tv1);
        tv2 = v.findViewById(R.id.tv2);

        switch (i) {
            case 0:
                ivGuidePage.setImageResource(R.drawable.welcom_view_one);
                tv1.setText("在大自然中专注创造");
                tv2.setText("置身海边森林或雨天，来一场灵感风暴");

                break;
            case 1:
                ivGuidePage.setImageResource(R.drawable.welcome_view_two);
                tv1.setText("和万物声音一起入眠");
                tv2.setText("看蝉鸣晚风，听月明星河");
                break;
            case 2:
                ivGuidePage.setImageResource(R.drawable.welcome_view_three);
                tv1.setText("遇见更平和的自己");
                tv2.setText("从今天起，和平靜一起平静身心");
                break;

        }
        return v;
    }
    //背景视频
    private void initView() {
        //加载视频资源控件
        videoView = findViewById(R.id.videoView);
        //设置播放加载路径
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.welcome2));
        //播放
        videoView.start();
        //循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
    }

    //返回重启加载
    @Override
    protected void onRestart() {
        initView();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        videoView.stopPlayback();
        super.onStop();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


