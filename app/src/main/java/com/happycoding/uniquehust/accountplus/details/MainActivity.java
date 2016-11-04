package com.happycoding.uniquehust.accountplus.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.yuan.waveview.WaveView;

import com.happycoding.uniquehust.accountplus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.budget_message) WaveView budget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        budget.setProgress(500);
        budget.setMax(1000);

    }
}
