package com.happycoding.uniquehust.accountplus.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.happycoding.uniquehust.accountplus.util.GetPicture;

import java.util.ArrayList;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        AccountItem item = list.get(position);
        if (item.getPicTimeStamp() != 0){
            holder.picture.setImageBitmap(GetPicture.get(item.getPicTimeStamp()));
        }

        String str = null;
        //收入与支出的价格和名字顺序相反
        if (item.getType() == AccountPlusApp.TYPE_INCOME){
            str = item.getTitle() + "  " + item.getDescription();
        }else{
            str = item.getDescription() + "  " + item.getTitle();
        }
        holder.remark.setText(item.getDescription());
        holder.typeAndMoney.setText(str);
        holder.icon.setImageResource(item.getIconID());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.type_and_money) TextView typeAndMoney;
        @BindView(R.id.remark)TextView remark;
        @BindView(R.id.picture)ImageView picture;
        @BindView(R.id.icon)ImageView icon;
        @OnClick(R.id.edit)
        void start(View view){
            //TODO:start activity
        }
        @OnClick(R.id.delete)
        void delete(View view) {
            //TODO:delete item
        }
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
