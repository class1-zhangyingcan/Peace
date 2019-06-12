package com.example.tide.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tide.R;
import com.example.tide.activity.TestActivity;
import com.example.tide.utils.SelectTimes;
import com.example.tide.utils.WheelView;

import java.util.ArrayList;
import java.util.List;

public class WakeupFragment extends Fragment{
    private ImageView back;
    private LinearLayout task;
    private LinearLayout shake;
    private ImageView taskheart;
    private ImageView shakeheart;
    private TextView times;
    private TextView setting;
    private String[] minStr = {"30"};
    private SelectTimes minute_pv;

    private TextView start;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sleep_wakeup, container, false);
        back = view.findViewById(R.id.back);
        task = view.findViewById(R.id.task);
        shake = view.findViewById(R.id.shake);
        taskheart = view.findViewById(R.id.taskheart);
        shakeheart = view.findViewById(R.id.shakeheart);
        times = view.findViewById(R.id.times);
        setting = view.findViewById(R.id.setting);
        start = view.findViewById(R.id.start);
        task.getBackground().setAlpha(100);
        shake.getBackground().setAlpha(100);


        //箭头监听器，点击返回

        //task监听器，点击改变样式，并返回“无任务”
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setBackground(getResources().getDrawable(R.drawable.wakeup_shape_selected));
                task.getBackground().setAlpha(130);
                taskheart.setImageDrawable(getResources().getDrawable(R.drawable.selected));
                shake.setBackground(getResources().getDrawable(R.drawable.wakeup_shape));
                shake.getBackground().setAlpha(100);
                shakeheart.setImageDrawable(getResources().getDrawable(R.drawable.select));
                setting.setVisibility(View.GONE);
            }
        });
        //shake监听器，点击改变样式，并返回“摇一摇”
        shake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shake.setBackground(getResources().getDrawable(R.drawable.wakeup_shape_selected));
                shake.getBackground().setAlpha(130);
                shakeheart.setImageDrawable(getResources().getDrawable(R.drawable.selected));
                task.setBackground(getResources().getDrawable(R.drawable.wakeup_shape));
                task.getBackground().setAlpha(100);
                taskheart.setImageDrawable(getResources().getDrawable(R.drawable.select));
                setting.setVisibility(View.VISIBLE);
            }
        });
        taskheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setBackground(getResources().getDrawable(R.drawable.wakeup_shape_selected));
                task.getBackground().setAlpha(130);
                taskheart.setImageDrawable(getResources().getDrawable(R.drawable.selected));
                shake.setBackground(getResources().getDrawable(R.drawable.wakeup_shape));
                shake.getBackground().setAlpha(100);
                shakeheart.setImageDrawable(getResources().getDrawable(R.drawable.select));
                setting.setVisibility(View.GONE);
            }
        });
        shakeheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shake.setBackground(getResources().getDrawable(R.drawable.wakeup_shape_selected));
                shake.getBackground().setAlpha(130);
                shakeheart.setImageDrawable(getResources().getDrawable(R.drawable.selected));
                task.setBackground(getResources().getDrawable(R.drawable.wakeup_shape));
                task.getBackground().setAlpha(100);
                taskheart.setImageDrawable(getResources().getDrawable(R.drawable.select));
                setting.setVisibility(View.VISIBLE);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });
        //点击开始按钮，跳到摇一摇界面
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), TestActivity.class);
                intent.putExtra("times",times.getText());
                startActivity(intent);
            }
        });



        return view;
    }
    private void showBottomDialog(){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(getActivity(),R.style.DialogTheme);
        //2、设置布局
        //View view = View.inflate(getActivity(),R.layout.dialog_layout,null);
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_layout, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        minute_pv = dialog.findViewById(R.id.message);
        //时间齿轮
        List<String> data = new ArrayList<String>();
        final List<String> seconds = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            data.add("0" + i);
        }
        for (int i = 0; i < 60; i++) {
            seconds.add(i < 10 ? "0" + i : "" + i);
        }
        minute_pv.setData(seconds);
        minute_pv.setOnSelectListener(new SelectTimes.onSelectListener(){
            @Override
            public void onSelect(String text) {
                minStr[0] = text;
            }
        });
        dialog.show();
        //确定按钮
        dialog.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                times.setText(minStr[0]);
            }
        });

        //取消按钮
        dialog.findViewById(R.id.negativeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });



    }




}
