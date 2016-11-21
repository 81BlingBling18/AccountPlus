package com.happycoding.uniquehust.accountplus.adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.TypeKeyValue;
import com.happycoding.uniquehust.accountplus.items.AccountItem;

import java.util.ArrayList;

public class GraphListAdapter extends BaseAdapter {
    double[] outcomeType = new double[18];
    double[] incomeType = new double[18];
    double outsum = 0;
    ArrayList<AccountItem> arrayList = new ArrayList<>();

    public GraphListAdapter() {
        super();

        ArrayList<AccountItem> list = DatabaseHelper.getMonthDetail(2016, 11);

        for (AccountItem i : list) {
            if(i.getType() == AccountPlusApp.TYPE_OUTCOME) {
                outcomeType[i.getIconID() - R.drawable.button_bag] += i.getAmount();
            } else {
                incomeType[i.getIconID() - R.drawable.button_bag] += i.getAmount();
            }
        }


        for (int i =0;i<outcomeType.length;i++) {
            if (outcomeType[i] != 0) {
                outsum += outcomeType[i];
                AccountItem item = new AccountItem();
                item.setIconID(R.mipmap.bag + i);
                item.setTitle(AccountPlusApp.getInstance().getString(TypeKeyValue.idTypeMap.get(R.mipmap.bag + i)));
                arrayList.add(item);
            }
        }
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }
}