package com.happycoding.uniquehust.accountplus.add_item;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.adapter.IconsPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qimeng on 16-11-5.
 */

public class AddEditAccountActivity extends AppCompatActivity{

    @BindView(R.id.icons)
    ViewPager icons;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_account_activity);
        ButterKnife.bind(this);
        initIcons();
    }

    private void initIcons(){
        GridView pageOne = new GridView(this);
        pageOne.setNumColumns(5);
        int[] icon = {R.drawable.normal, R.drawable.food, R.drawable.book, R.drawable.gift,
                R.drawable.transportation, R.drawable.tel_fare, R.drawable.rent, R.drawable.movie,
                R.drawable.game, R.drawable.internet_fare, R.drawable.purse, R.drawable.medicine,
                R.drawable.snack, R.drawable.travel, R.drawable.clothes};
        String[] iconName = {"一般", "餐饮", "书费", "礼物", "交通", "话费", "房租", "电影",
                "游戏", "网费", "箱包", "医药", "零食", "旅游", "衣服"};
        ArrayList<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0;i<iconName.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            dataList.add(map);
        }
        String[] from = {"image", "text"};
        int[] to = {R.id.icon, R.id.text};
        SimpleAdapter sm = new SimpleAdapter(this, dataList, R.layout.icon_item, from, to);
        pageOne.setAdapter(sm);


        GridView pageTwo = new GridView(this);
        pageTwo.setNumColumns(5);
        int[] icon2 = {R.drawable.mobile_phone,R.drawable.comb,R.drawable.lipstick,R.drawable.pen
        ,R.drawable.car,R.drawable.ktv,R.drawable.camera,R.drawable.kfc,R.drawable.fruit,
        R.drawable.pot,R.drawable.letter,R.drawable.rice,R.drawable.cosmetology,R.drawable.tie};
        String[] iconName2 = {"手机", "梳子", "化妆品", "文具", "车", "KTV", "相机", "KFC",
                "水果", "厨具", "信件", "米饭", "饭店", "美容", "领带"};
        ArrayList<Map<String, Object>> dataList2 = new ArrayList<>();
        for (int i = 0;i<iconName2.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon2[i]);
            map.put("text", iconName2[i]);
            dataList2.add(map);
        }
        SimpleAdapter sm2 = new SimpleAdapter(this, dataList, R.layout.icon_item, from, to);
        pageTwo.setAdapter(sm2);

        ArrayList<View> list = new ArrayList<>();
        list.add(pageOne);
        list.add(pageTwo);

        IconsPagerAdapter adapter = new IconsPagerAdapter(list);

        icons.setAdapter(adapter);
    }
}
