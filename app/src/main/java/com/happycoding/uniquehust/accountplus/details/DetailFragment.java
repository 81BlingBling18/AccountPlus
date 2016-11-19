package com.happycoding.uniquehust.accountplus.details;


import android.content.Intent;
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
import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.yuan.waveview.WaveView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this,view);

        //ArrayList<AccountItem>list = DatabaseHelper.getAll();
//        if (list.size() == 0) {
//            account_list.setVisibility(GONE);
//        }else{
//            emptyPage.setVisibility(GONE);
//        }

        budget.setMax(100);
        budget.setProgress(50);
        Date date = new Date();
        Log.d("holo",date.getTime() + "shijian ");
//        AccountItem item = new AccountItem(AccountPlusApp.TYPE_INCOME,"heihei",2.33,"test",2016,1,22,11,R.drawable.button_bag,AccountPlusApp.TYPE_INCOME);
//        DatabaseHelper.add(item);
//        account_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//        account_list.setAdapter(new AccountListAdapter(DatabaseHelper.getAll()));
        return view;
    }
}
