package com.happycoding.uniquehust.accountplus.util;

import android.content.SharedPreferences;

import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;

import java.util.logging.SocketHandler;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.PRINT_SERVICE;

/**
 * Created by qimeng on 16-11-14.
 */

public class PasswordSystem {
    private static PasswordSystem passwordSystem = new PasswordSystem();
    private SharedPreferences sharedPreferences = AccountPlusApp.getInstance().getSharedPreferences("protection_data", MODE_PRIVATE);

    public static PasswordSystem getInstance() {
        return passwordSystem;
    }
    private PasswordSystem() {
    }

    public void initPasswordSystem(){
        SharedPreferences sharedPreferences = AccountPlusApp.getInstance().getSharedPreferences("protection_data", MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("system_init",false)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("open", false);
            editor.putBoolean("password_init", false);
            editor.putBoolean("system_init", true);
            editor.putString("password", "");
            editor.apply();
        }
    }

    public boolean ifInitPassword() {
        return sharedPreferences.getBoolean("password_init",false);
    }

    public boolean ifOpenSystem() {
        return sharedPreferences.getBoolean("open",false);
    }

    public void initPassword() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("password_init", true);
        editor.apply();
    }

    public void openSystem() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("open", true);
        editor.apply();
    }

    public void closeSystem() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("open", false);
        editor.apply();
    }

    public String getPassword() {
        return sharedPreferences.getString("password",null);
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", password);
        editor.apply();
    }
}























