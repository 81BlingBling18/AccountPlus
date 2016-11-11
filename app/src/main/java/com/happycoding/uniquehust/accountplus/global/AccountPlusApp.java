package com.happycoding.uniquehust.accountplus.global;

import android.app.Application;

/**
 * Created by qimeng on 16-11-5.
 */

public class AccountPlusApp extends Application {
    private static AccountPlusApp accountPlusApp = null;
    public static final int TYPE_INCOME = 1;
    public static final int TYPE_OUTCOME = 0;

    @Override
    public void onCreate() {
        accountPlusApp = this;
    }

    public static AccountPlusApp getInstance(){
        return accountPlusApp;
    }
}
