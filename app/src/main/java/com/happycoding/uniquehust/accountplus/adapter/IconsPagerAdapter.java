package com.happycoding.uniquehust.accountplus.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;



/**
 * Created by qimeng on 16-11-11.
 */

public class IconsPagerAdapter extends PagerAdapter {
    private ArrayList<View> list;
    public IconsPagerAdapter(ArrayList<View>list){
        this.list = list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
}
