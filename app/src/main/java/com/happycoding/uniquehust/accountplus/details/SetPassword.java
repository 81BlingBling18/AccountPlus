package com.happycoding.uniquehust.accountplus.details;

import android.content.pm.InstrumentationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.global.Lg;
import com.happycoding.uniquehust.accountplus.util.PasswordSystem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetPassword extends AppCompatActivity {

    private final int PROCESS_INPUT_OLD_PASSWORD = 10010;
    private final int PROCESS_INPUT_NEW_PASSWORD = 10011;
    private final int PROCESS_INPUT_AFFIRM_PASSWORD = 10012;
    private int PROCESS_NOW = 10010;
    private String newPassword;
    @BindView(R.id.message) TextView tip;
    @BindView(R.id.input_password) EditText inputPassword;
    @BindView(R.id.dot1) ImageView dot1;
    @BindView(R.id.dot2) ImageView dot2;
    @BindView(R.id.dot3) ImageView dot3;
    @BindView(R.id.dot4) ImageView dot4;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_password_activity);
        ButterKnife.bind(this);

        init();
    }

    private void setDot(int number) {

        if (number > 0) {
            dot1.setVisibility(View.VISIBLE);
        }else {
            dot1.setVisibility(View.INVISIBLE);
        }

        if (number > 1) {
            dot2.setVisibility(View.VISIBLE);
        }
        else {
            dot2.setVisibility(View.INVISIBLE);
        }

        if (number > 2) {
            dot3.setVisibility(View.VISIBLE);
        }
        else {
            dot3.setVisibility(View.INVISIBLE);
        }

        if (number > 3) {
            dot4.setVisibility(View.VISIBLE);
        }
        else {
            dot4.setVisibility(View.INVISIBLE);
        }
        dot1.invalidate();
        dot2.invalidate();
        dot3.invalidate();
        dot4.invalidate();
    }

    private void handleProcess(String input) {

        PasswordSystem system = PasswordSystem.getInstance();
        if (PROCESS_NOW == PROCESS_INPUT_OLD_PASSWORD) {
            if (input.equals(system.getPassword())) {
                PROCESS_NOW = PROCESS_INPUT_NEW_PASSWORD;
                setDot(0);
                inputPassword.setText("");
                tip.setText(getString(R.string.input_new_password));
            }else {
                AccountPlusApp.toast(getString(R.string.wrong_password));
                setDot(0);
                inputPassword.setText("");
            }
        }else if (PROCESS_NOW == PROCESS_INPUT_NEW_PASSWORD){
            newPassword = inputPassword.getText().toString();
            inputPassword.setText("");
            setDot(0);
            tip.setText(getString(R.string.input_password_again));
            PROCESS_NOW = PROCESS_INPUT_AFFIRM_PASSWORD;
        }else if (PROCESS_NOW == PROCESS_INPUT_AFFIRM_PASSWORD){
            if (inputPassword.getText().toString().equals(newPassword)) {
                AccountPlusApp.toast(getString(R.string.set_password_success));
                system.setPassword(inputPassword.getText().toString());
                this.finish();
            }else {
                inputPassword.setText("");
                setDot(0);
                AccountPlusApp.toast(getString(R.string.wrong_password));
            }
        }
    }

    private void init() {
        PasswordSystem system = PasswordSystem.getInstance();
        if (!system.ifInitPassword()){
            system.initPassword();
            PROCESS_NOW = PROCESS_INPUT_NEW_PASSWORD;
            tip.setText(getString(R.string.input_new_password));
        }

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.toString().length();
                setDot(inputPassword.getText().toString().length());
                if (length == 4) {
                    SetPassword.this.handleProcess(inputPassword.getText().toString());
                }
            }
        });
    }
}
