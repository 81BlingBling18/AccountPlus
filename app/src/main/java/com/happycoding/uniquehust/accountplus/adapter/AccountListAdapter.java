package com.happycoding.uniquehust.accountplus.adapter;

import android.accounts.Account;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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


    public AccountListAdapter() {

    }

    public void setList(ArrayList<AccountItem> list) {
        initList(list);
    }
    private void initList(ArrayList<AccountItem> list) {

        for (int i = 0;i<list.size();i++) {
            Lg.d("raw type is  " + list.get(i).getType());
        }

        int tempCounter = 0;
        int tempDay = 0;
        //将每一天的起始item添加进去
        ArrayList<AccountItem> tempList = new ArrayList<>();
        for (int i = 0;i<list.size();i++) {
            if (tempDay != list.get(i).getDay()) {
                Lg.d("size :" + list.size() + "current i " + i);
                AccountItem item = new AccountItem();
                item.setType(AccountPlusApp.TYPE_DAY_BEGIN);
                item.setDay(list.get(i).getDay());
                tempList.add(i + tempCounter, item);
                tempList.add(i + tempCounter + 1, list.get(i));
                tempDay = list.get(i).getDay();
                tempCounter++;
            }else {
                tempList.add(list.get(i));
            }
        }
        for (int i = 0;i<tempList.size();i++) {
            Lg.d("rate type is " + tempList.get(i).getType());
        }
        this.list = tempList;
        this.notifyDataSetChanged();
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
            holder.date.setText(list.get(position).getDay() + "日");
        }else {
            if (item.getPicTimeStamp() != 0){
                holder.picture.setImageBitmap(GetPicture.get(item.getPicTimeStamp()));
            }

            holder.icon.setImageResource(item.getIconID());
            holder.icon.setPressed(true);
            holder.icon.setEnabled(false);
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
        ImageButton icon;
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
                icon = (ImageButton) view.findViewById(R.id.icon);
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
