package com.happycoding.uniquehust.accountplus.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.items.AccountItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qimeng on 16-11-19.
 */

public class FeedBackActivity extends AppCompatActivity {

    @BindView(R.id.suggestion)
    Button suggestion;

    @BindView(R.id.problem)
    Button problem;

    @BindView(R.id.text)
    EditText inputText;

    @OnClick(R.id.suggestion)
    public void suggestion (){
        suggestion.setBackgroundResource(R.drawable.feedback_button_pressed);
        suggestion.setTextColor(getResources().getColor(R.color.white));

        problem.setBackgroundResource(R.drawable.feedback_button_not_pressed);
        problem.setTextColor(getResources().getColor(R.color.black));
    }

    @OnClick(R.id.problem)
    public void problem() {
        problem.setBackgroundResource(R.drawable.feedback_button_pressed);
        problem.setTextColor(getResources().getColor(R.color.white));

        suggestion.setBackgroundResource(R.drawable.feedback_button_not_pressed);
        suggestion.setTextColor(getResources().getColor(R.color.black));
    }

    @OnClick(R.id.send)
    public void sendMessage() {
        AccountPlusApp.toast("感谢您的反馈！");
        this.finish();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity_layout);
        ButterKnife.bind(this);
        suggestion.setBackgroundResource(R.drawable.feedback_button_pressed);

    }
}
