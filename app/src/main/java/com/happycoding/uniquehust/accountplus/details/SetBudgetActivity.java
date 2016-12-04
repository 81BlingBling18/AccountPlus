package com.happycoding.uniquehust.accountplus.details;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetBudgetActivity extends AppCompatActivity {


    @OnClick({R.id.back_image, R.id.back_text})
    public void onBackClick() {
        onBackPressed();
    }
    @BindView(R.id.input_budget)EditText budget;

    @OnClick(R.id.save)
    public void onClick() {
        String str = budget.getText().toString();
        Float number = new Float(str);
        SharedPreferences sharedPreferences = getSharedPreferences("budget", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("budget", number);
        editor.commit();
        Toast.makeText(this, "设置成功!", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_budget_activity);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences("budget", MODE_PRIVATE);
        float number = sharedPreferences.getFloat("budget",0);
        budget.setText(number + "");
    }
}
