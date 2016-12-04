package com.happycoding.uniquehust.accountplus.adapter;

import android.app.Application;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.TypeKeyValue;
import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.ysyao.bottomtabbar.BottomTextViewGroup;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GraphListAdapter extends BaseAdapter {

    double[] outcomeType = new double[18];
    double[] incomeType = new double[18];
    double outsum = 0;
    int mYear, mMonth;
    ArrayList<AccountItem> arrayList = new ArrayList<>();

    public GraphListAdapter(int year, int month) {
        super();
        mYear = year;
        mMonth = month;

        ArrayList<AccountItem> list = DatabaseHelper.getMonthDetail(year, month);

        for (AccountItem i : list) {
            if (i.getType() == AccountPlusApp.TYPE_OUTCOME) {
                outcomeType[i.getIconID() - R.drawable.button_bag] += i.getAmount();
            } else {
                incomeType[i.getIconID() - R.drawable.button_bag] += i.getAmount();
            }
        }


        for (int i = 0; i < 18; i++) {
            if (outcomeType[i] != 0) {
                outsum = outcomeType[i];
                AccountItem item = new AccountItem();
                item.setIconID(R.drawable.button_bag + i);
                item.setTitle(AccountPlusApp.
                        getInstance().
                        getResources().
                        getString(TypeKeyValue.idTypeMap.get(R.drawable.button_bag + i)));
                item.setAmount(outsum);
                arrayList.add(item);
            }
        }
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(AccountPlusApp.getInstance()).inflate(R.layout.graph_list_item, null);
            holder = new ViewHolder();
            holder.icon = (ImageButton) convertView.findViewById(R.id.item_image);
            holder.type = (TextView) convertView.findViewById(R.id.item_type);
            holder.amount = (TextView) convertView.findViewById(R.id.item_amount);
            holder.percent = (TextView) convertView.findViewById(R.id.item_percent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.icon.setEnabled(false);
        holder.icon.setImageResource(arrayList.get(i).getIconID());
        holder.type.setText(arrayList.get(i).getTitle());
        DecimalFormat df = new DecimalFormat("#.##");

        holder.amount.setText(df.format(arrayList.get(i).getAmount()));
        holder.percent.setText(df.format(arrayList.get(i).getAmount() * 100/ DatabaseHelper.getMonthOutcome(mYear, mMonth)) + "%");
        return convertView;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    public final class ViewHolder {
        public ImageButton icon;
        public TextView type;
        public TextView amount;
        public TextView percent;
    }
}