package com.happycoding.uniquehust.accountplus.details;


import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.adapter.AccountListAdapter;
import com.happycoding.uniquehust.accountplus.add_item.AddEditAccountActivity;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.global.TypeKeyValue;

import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.yuan.waveview.WaveView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.GONE;

/**
 * Created by qimeng on 16-11-12.
 */

public class DetailFragment extends Fragment {


    @BindView(R.id.budget_message) WaveView budget;
    @BindView(R.id.account_list) RecyclerView account_list;
    @BindView(R.id.remained_budget) TextView remainedBudget;
    @BindView(R.id.month_income) TextView monthIncome;
    @BindView(R.id.month_outcome)TextView monthOutcome;
    @BindView(R.id.income_number)TextView income_Number;
    @BindView(R.id.outcome_number) TextView outcomeNumber;
    @BindView(R.id.empty_page) ImageView emptyPage;

    private int mYear, mMonth, mDay;
    private AccountListAdapter adapter;
    private ArrayList<AccountItem> list;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MainActivity.MessageEvent3 event) {
        mYear = event.year;
        mMonth = event.month;
        mDay = event.day;
        DecimalFormat df = new DecimalFormat("#.##");
        String outcome = df.format(DatabaseHelper.getMonthOutcome(mYear, mMonth));
        String income = df.format(DatabaseHelper.getMonthIncome(mYear, mMonth));
        outcomeNumber.setText(outcome);
        income_Number.setText(income);
        ArrayList<AccountItem> list = DatabaseHelper.getMonthDetail(mYear, mMonth);
        adapter.setList(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this,view);


        mYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        mMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;
        mDay = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
        DecimalFormat df = new DecimalFormat("#.##");

        String outcome = df.format(DatabaseHelper.getMonthOutcome(mYear, mMonth));
        String income = df.format(DatabaseHelper.getMonthIncome(mYear, mMonth));
        outcomeNumber.setText(outcome);
        income_Number.setText(income);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AccountItem item;


        SharedPreferences sharedPreferences = AccountPlusApp
                .getInstance()
                .getSharedPreferences("budget", MODE_PRIVATE);
        budget.setMax(100);
        budget.setProgress(100 - (int)(100*(DatabaseHelper.getMonthOutcome(mYear, mMonth) / sharedPreferences.getFloat("budget", 0))
        ));
//        for (int i = 0;i<3;i++) {
//            item = new AccountItem(AccountPlusApp.TYPE_INCOME,"写作业",20,"好想写作业啊"
//                    , 2016,10,22, i,R.drawable.button_bag);
//            DatabaseHelper.add(item);
//        }
        ArrayList<AccountItem>list = DatabaseHelper.getMonthDetail(mYear, mMonth);
        Lg.d("size is " + list.size());
        for (int i= 0;i<list.size();i++) {
            item = list.get(i);
            Lg.d(item.getAmount() + "   "  );
        }

        if (list.size() == 0) {
            account_list.setVisibility(GONE);
        }else{
            emptyPage.setVisibility(GONE);
        }

        adapter = new AccountListAdapter();
        adapter.setList(list);

        account_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        account_list.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
