package com.example.xutils3_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.xutils3_demo.fragment.DBFragment;
import com.example.xutils3_demo.fragment.HttpFragment;
import com.example.xutils3_demo.fragment.ImageFragment;
import com.example.xutils3_demo.fragment.InjectFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)//1.Activity注解
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.viewpager)//3.加载控件
            ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);//2.Activity注解
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new InjectFragment();
                    case 1:
                        return new HttpFragment();
                    case 2:
                        return new ImageFragment();
                    case 3:
                        return new DBFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                switch (position) {
                    case 0:
                        return "注解模块";
                    case 1:
                        return "网络模块";
                    case 2:
                        return "图片模块";
                    case 3:
                        return "数据库模块";
                }
                return null;
            }
        });


    }


}
