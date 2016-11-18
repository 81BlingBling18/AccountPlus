package com.happycoding.uniquehust.accountplus.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.util.PasswordSystem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qimeng on 16-11-12.
 */

public class PasswordProtection extends AppCompatActivity {

    @BindView(R.id.open_password_protection) Switch openPasswordProtection;

    @OnClick(R.id.set_password)
    public void setPassword(View v) {
        PasswordSystem system = PasswordSystem.getInstance();
        if (system.ifOpenSystem()){
            Intent intent = new Intent(PasswordProtection.this, SetPassword.class);
            startActivity(intent);
        }else {
            AccountPlusApp.toast("请先开启密码保护功能");
        }

    }
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_protection_activity);
        ButterKnife.bind(this);

        openPasswordProtection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PasswordSystem system = PasswordSystem.getInstance();
                Lg.d(b + "");
                if (b) {
                    boolean passwordInit = system.ifInitPassword();
                    system.openSystem();
                    if (!passwordInit) {
                        Intent intent = new Intent(PasswordProtection.this, SetPassword.class);
                        startActivity(intent);
                    }
                }else{
                    system.closeSystem();
                }

            }
        });


    }
}
