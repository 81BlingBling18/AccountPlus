package com.happycoding.uniquehust.accountplus.details;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.items.AccountItem;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessInformActivity extends AppCompatActivity {

    @OnClick(R.id.back_to_main)
    public void onClick(View view) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_inform);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("flag", false)) {
            AccountItem item = new AccountItem(
                    AccountPlusApp.TYPE_OUTCOME,
                    "房租",
                    800.00,
                    "",
                    2016, 12, 1,
                    System.currentTimeMillis(),
                    R.drawable.button_house
            );

            DatabaseHelper.add(item);
        }
    }
}
