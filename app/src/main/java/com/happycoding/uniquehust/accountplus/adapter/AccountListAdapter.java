package com.happycoding.uniquehust.accountplus.adapter;

import android.accounts.Account;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.happycoding.uniquehust.accountplus.util.GetPicture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qimeng on 16-11-5.
 */

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.ViewHolder>{
    private ArrayList<AccountItem> list;


    public AccountListAdapter(ArrayList<AccountItem> list) {
        this.list = list;
                int dayPassed = 0;
        LinkedList<AccountItem> linkedList = new LinkedList<>(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd日");
        String dayNow = simpleDateFormat.format(new Date(list.get(0).getTimeStamp()));
        AccountItem item = new AccountItem();
        item.setType(AccountPlusApp.TYPE_DAY_BEGIN);
        item.setDate(dayNow);
        linkedList.add(0,item);
        //至要少有一个
        for (int i = 1;i<linkedList.size();i++) {
            String tempDay = simpleDateFormat.format(new Date(linkedList.get(i).getTimeStamp()));
            if(!tempDay.equals(dayNow)){
                item.setDate(tempDay);
                linkedList.add(i,item);
                dayNow = tempDay;
            }
        }
        this.list = new ArrayList<AccountItem>(linkedList);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Lg.d(list.get(position).getType() + "");
        return list.get(position).getType();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AccountItem item = list.get(position);
        if(item.getType() == AccountPlusApp.TYPE_DAY_BEGIN){
            holder.date.setText(list.get(position).getDate());
        }else {
            if (item.getPicTimeStamp() != 0){
                holder.picture.setImageBitmap(GetPicture.get(item.getPicTimeStamp()));
            }

            String str;
            //收入与支出的价格和名字顺序相反
            if (item.getType() == AccountPlusApp.TYPE_OUTCOME){
                str = item.getTitle() + "  " + item.getAmount();
            }else{
                str = item.getAmount() + "  " + item.getTitle();
            }
            holder.remark.setText(item.getDescription());
            holder.typeAndMoney.setText(str);
            Lg.d(item.getDescription() + str);
            //holder.icon.setImageResource(item.getIconID());
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if (viewType == AccountPlusApp.TYPE_OUTCOME){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_outcome_item, null);
        }else if (viewType == AccountPlusApp.TYPE_INCOME){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_income_item,null);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_day_tip_item, null);
        }
        return new ViewHolder(view,viewType);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeAndMoney;
        TextView remark;
        ImageView picture;
        Button edit;
        Button delete;
        TextView date;
        boolean visible = false;
        public ViewHolder(View view,int type) {
            super(view);
            if (type == AccountPlusApp.TYPE_DAY_BEGIN){
                date = (TextView) view.findViewById(R.id.date);
            }else{
                typeAndMoney = (TextView) view.findViewById(R.id.type_and_money);
                remark = (TextView) view.findViewById(R.id.remark);
                picture = (ImageView) view.findViewById(R.id.picture);
                edit = (Button) view.findViewById(R.id.edit);
                delete = (Button) view.findViewById(R.id.delete);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO:start new activity
                    }
                });

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO:delete item
                    }
                });
            }

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (edit != null && delete != null) {
                        if (visible){
                            edit.setVisibility(View.INVISIBLE);
                            delete.setVisibility(View.INVISIBLE);
                            visible = false;
                        }else {
                            edit.setVisibility(View.VISIBLE);
                            delete.setVisibility(View.VISIBLE);
                            visible = true;
                        }
                    }
                    return false;
                }
            });
        }
    }
}
