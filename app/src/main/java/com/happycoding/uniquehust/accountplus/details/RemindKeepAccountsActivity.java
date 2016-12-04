package com.happycoding.uniquehust.accountplus.details;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qimeng on 16-12-1.
 */

public class RemindKeepAccountsActivity extends AppCompatActivity {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @OnClick({R.id.back_image, R.id.back_text})
    public void onBackClick() {
        onBackPressed();
    }
    @BindView(R.id.open_remind)
    Switch openRemind;
    @BindView(R.id.time)
    TextView time;

    @OnClick(R.id.set_time)
    public void onClick() {
        SharedPreferences sharedPreferences = getSharedPreferences("remind", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("ifRemind", false)){
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    year = i;
                    month = i1;
                    day = i2;

                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    Date date = new Date(year, month, day, hour, minute);
                    AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    Intent intent = new Intent("ELITOR_CLOCK");
                    intent.putExtra("msg", "该记账了");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AccountPlusApp.getInstance(), 0, intent, 0);

                    manager.set(AlarmManager.RTC_WAKEUP,date.getTime(),pendingIntent);
                    time.setText(year + "年" +month+ "月"  + day+ "日"  + hour + "时" + minute + "分");
                    AccountPlusApp.toast("设置成功！");
                }
            });

            datePickerDialog.show();
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hour = i;
                    minute = i1;
                }
            },0,0,true).show();
        }else {
            AccountPlusApp.toast("未打开提醒功能");
        }
    }
    @Override
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.remind_keep_accounts_activity);
        ButterKnife.bind(this);

        final SharedPreferences.Editor editor = getSharedPreferences("remind",MODE_PRIVATE).edit();
        openRemind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor.putBoolean("ifRemind", true);
                    editor.apply();
                    AccountPlusApp.toast("提醒已开启！");
                } else {
                    editor.putBoolean("ifRemind", false);
                    editor.apply();
                    AccountPlusApp.toast("提醒已关闭！");
                }
            }
        });

    }
}
