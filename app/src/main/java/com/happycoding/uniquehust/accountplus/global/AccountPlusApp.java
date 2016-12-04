package com.happycoding.uniquehust.accountplus.global;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by qimeng on 16-11-5.
 */

public class AccountPlusApp extends Application {
    private static AccountPlusApp accountPlusApp = null;
    public static final int TYPE_INCOME = 1;
    public static final int TYPE_OUTCOME = 0;
    public static final int TYPE_DAY_BEGIN = 2;
    private static Toast toast;

    @Override
    public void onCreate() {
        accountPlusApp = this;


        toast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_SHORT);

    }

    public static void toast(String str) {
        toast.setText(str);
        toast.show();
    }

    public static AccountPlusApp getInstance(){
        return accountPlusApp;
    }
}
