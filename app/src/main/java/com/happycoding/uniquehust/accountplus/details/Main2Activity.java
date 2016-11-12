package com.happycoding.uniquehust.accountplus.details;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.happycoding.uniquehust.accountplus.adapter.AccountListAdapter;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.yuan.waveview.WaveView;

import com.happycoding.uniquehust.accountplus.R;

import org.w3c.dom.Text;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.budget_message) WaveView budget;
    @BindView(R.id.account_list) RecyclerView account_list;
    @BindView(R.id.remained_budget) TextView remainedBudget;
    @BindView(R.id.month_income) TextView monthIncome;
    @BindView(R.id.month_outcome)TextView monthOutcome;
    @BindView(R.id.income_number)TextView income_Number;
    @BindView(R.id.outcome_number) TextView outcomeNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        budget.setMax(100);
        budget.setProgress(50);
        Date date = new Date();
        Log.d("holo",date.getTime() + "shijian ");
        AccountItem item = new AccountItem(AccountPlusApp.TYPE_INCOME,"写作业",20,"好想写作业啊"
                , 12345678,2232982,66666);
        DatabaseHelper.add(item);
        account_list.setLayoutManager(new LinearLayoutManager(this));
        account_list.setAdapter(new AccountListAdapter(DatabaseHelper.getAll()));
    }
}
