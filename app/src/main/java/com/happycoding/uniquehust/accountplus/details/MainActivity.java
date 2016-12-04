package com.happycoding.uniquehust.accountplus.details;

import android.Manifest;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.happycoding.uniquehust.accountplus.R;

import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.global.TypeKeyValue;
import com.happycoding.uniquehust.accountplus.items.AccountItem;
import com.happycoding.uniquehust.accountplus.util.PasswordSystem;
import com.happycoding.uniquehust.accountplus.util.SmsMessageHandler;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.happycoding.uniquehust.accountplus.R.id.toolbar;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int mYear, mMonth, mDay;
    DetailFragment detailFragment;
    GraphFragment graphFragment;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_textview)
    TextView title;

    @OnClick(R.id.calender_button)
    public void onCalenderButtonClick(View view) {
        EventBus.getDefault().post(new MessageEvent3(mYear, mMonth, mDay));
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear + 1;
                title.setText(mYear + "年" + mMonth + "月");
            }
        }, mYear, mMonth - 1, mDay).show();
    }

    @OnClick(R.id.list)
    public void startDetail() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, detailFragment);
        transaction.commit();
    }

    @OnClick(R.id.graph)
    public void startGraph() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, graphFragment);
        transaction.commit();
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @OnClick(R.id.navigation)
    public void startDrawer(View v) {
        drawer.openDrawer(Gravity.LEFT);
    }

    private ImageButton mButtonAdd;
    private DatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("");

        mYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        mMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;
        mDay = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
        title.setText(mYear + "年" + mMonth + "月");
        detailFragment = new DetailFragment();
        graphFragment = new GraphFragment();

        startDetail();
        PasswordSystem.getInstance().initPasswordSystem();
//        DetailFragment detailFragment = new DetailFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.empty_fragment, detailFragment);
//        transaction.commit();


        mHelper = new DatabaseHelper(this);

        mButtonAdd = (ImageButton) findViewById(R.id.button_add);
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
        toggle.setDrawerIndicatorEnabled(false);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String msg = null;
        if (intent != null && (msg = intent.getStringExtra("sms")) != null) {
            msg = intent.getStringExtra("sms");
            ArrayList<String> list = SmsMessageHandler.handleMessage(msg);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            final View view = LayoutInflater.from(this)
                    .inflate(R.layout.dialog_sms, null);

            builder.setTitle("识别内容")
                    .setView(view);

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText type = (EditText) (view.findViewById(R.id.type));
                    EditText amount = (EditText) (view.findViewById(R.id.amount));
                    EditText description = (EditText) (view.findViewById(R.id.description));
                    DecimalFormat df = new DecimalFormat("#####.##");
                    String s = df.format(Double.parseDouble(amount.getText().toString()));
                    AccountItem item = new AccountItem(
                            type.getText().toString().equals("支取") ? AccountPlusApp.TYPE_OUTCOME : AccountPlusApp.TYPE_INCOME,
                            type.getText().toString(),
                            Double.parseDouble(s),
                            description.getText().toString(),
                            mYear,
                            mMonth,
                            mDay,
                            System.currentTimeMillis(),
                            R.drawable.button_normal);
                    DatabaseHelper.add(item);
                    startGraph();
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            EditText type = (EditText) (view.findViewById(R.id.type));
            EditText amount = (EditText) (view.findViewById(R.id.amount));
            EditText description = (EditText) (view.findViewById(R.id.description));

            type.setText(list.get(3));
            amount.setText(list.get(5));
            description.setText(list.get(2) + " " + list.get(4));
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}
                    , 0xaaff);
        }

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
            startActivity(new Intent(MainActivity.this, SetBudgetActivity.class));
        } else if (id == R.id.password_protection) {
            Intent intent = new Intent(MainActivity.this, PasswordProtection.class);
            startActivity(intent);
            Log.d("drawer", "onNavigationItemSelected: 2");
        } else if (id == R.id.account_notification) {
            Intent intent = new Intent(MainActivity.this, RemindKeepAccountsActivity.class);
            startActivity(intent);
            Log.d("drawer", "onNavigationItemSelected: 3");
        } else if (id == R.id.feedback) {
            Intent intent = new Intent(MainActivity.this, FeedBackActivity.class);
            startActivity(intent);
            Log.d("drawer", "onNavigationItemSelected: 4");
        } else if (id == R.id.more) {
            Log.d("drawer", "onNavigationItemSelected: 5");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static class MessageEvent3 {
        public int year;
        public int month;
        public int day;

        public MessageEvent3(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
}
