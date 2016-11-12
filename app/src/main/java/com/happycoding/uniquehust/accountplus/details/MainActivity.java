package com.happycoding.uniquehust.accountplus.details;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.Lg;

import butterknife.BindView;
import android.icu.util.Calendar;
import android.provider.ContactsContract;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.adapter.AccountListAdapter;
import com.happycoding.uniquehust.accountplus.add_item.AddEditAccountActivity;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.yuan.waveview.WaveView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.budget_message) WaveView budget;
    @BindView(R.id.account_list) RecyclerView account_list;
    @BindView(R.id.remained_budget) TextView remainedBudget;
    @BindView(R.id.month_income) TextView monthIncome;
    @BindView(R.id.month_outcome)TextView monthOutcome;
    @BindView(R.id.income_number)TextView income_Number;
    @BindView(R.id.outcome_number) TextView outcomeNumber;

    private ImageButton mButtonAdd;
    @OnClick(R.id.add)
    void startAdd(){
        Intent intent = new Intent(AccountPlusApp.getInstance().getApplicationContext(), AddEditAccountActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mButtonAdd = (ImageButton)findViewById(R.id.button_add);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountEditActivity.class);
                Log.d("MainActivity", "onAddClick: ");
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        budget.setMax(100);
        budget.setProgress(50);
        Date date = new Date();
        AccountItem item = new AccountItem(AccountPlusApp.TYPE_OUTCOME,"写作业",20,"好想写作业啊"
                , new Date().getTime(),2232982,66666);
        DatabaseHelper.add(item);
        DatabaseHelper.add(item);
        account_list.setLayoutManager(new LinearLayoutManager(this));
        account_list.setAdapter(new AccountListAdapter(DatabaseHelper.getAll()));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.set_budget) {
            Log.d("drawer", "onNavigationItemSelected: 1");
        } else if (id == R.id.password_protection) {
            Log.d("drawer", "onNavigationItemSelected: 2");
        } else if (id == R.id.account_notification) {
            Log.d("drawer", "onNavigationItemSelected: 3");
        } else if (id == R.id.feedback) {
            Log.d("drawer", "onNavigationItemSelected: 4");
        } else if (id == R.id.more) {
            Log.d("drawer", "onNavigationItemSelected: 5");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

        }


