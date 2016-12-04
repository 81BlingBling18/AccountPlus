package com.happycoding.uniquehust.accountplus.details;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.adapter.GraphFragmentAdapter;
import com.happycoding.uniquehust.accountplus.adapter.GraphListAdapter;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;
import com.happycoding.uniquehust.accountplus.items.AccountItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphFragment extends Fragment {

    public static final String TAG = "GraphFragment";
    private int mYear, mMonth, mDay;
//    @BindView(R.id.graph_pager )ViewPager graphPager;

    @BindView(R.id.graph_pie_chart)
    PieChart pieChart;
    @BindView(R.id.graphList)
    ListView graphList;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MainActivity.MessageEvent3 event) {
        Log.d(TAG, "onMessageEvent: Message Received");
        mYear = event.year;
        mMonth = event.month;
        mDay = event.day;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        mMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;
        mDay = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_graph, container, false);
        ButterKnife.bind(this,view);

//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(new IncomeGraphFragment());
//        fragments.add(new OutcomeGraphFragment());
//        GraphFragmentAdapter adapter = new GraphFragmentAdapter(getActivity().getSupportFragmentManager(), fragments);
//        graphPager.setAdapter(adapter);




        pieChart.setUsePercentValues(true);//设置value是否用显示百分数,默认为false
//pieChart.setDescriptionColor(); //设置描述颜色
//pieChart.setDescriptionTypeface();//设置描述字体
        Description description = new Description();
        description.setText(" ");
        pieChart.setDescription(description);
        pieChart.setExtraOffsets(5, 5, 5, 5);//设置饼状图距离上下左右的偏移量

        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置阻尼系数,范围在[0,1]之间,越小饼状图转动越困难

        pieChart.setDrawCenterText(true);//是否绘制中间的文字
        pieChart.setCenterTextSize(24);//中间的文字字体大小

        pieChart.setDrawHoleEnabled(true);//是否绘制饼状图中间的圆
        pieChart.setHoleColor(Color.WHITE);//饼状图中间的圆的绘制颜色
        pieChart.setHoleRadius(45f);//饼状图中间的圆的半径大小

        pieChart.setTransparentCircleColor(Color.BLACK);//设置圆环的颜色
        pieChart.setTransparentCircleAlpha(25);//设置圆环的透明度[0,255]
        pieChart.setTransparentCircleRadius(55f);//设置圆环的半径值

// enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);//设置饼状图是否可以旋转(默认为true)
        pieChart.setRotationAngle(10);//设置饼状图旋转的角度

        pieChart.setHighlightPerTapEnabled(true);//设置旋转的时候点中的tab是否高亮(默认为true)

// entry label styling
        pieChart.setDrawEntryLabels(true);//设置是否绘制Label
        pieChart.setEntryLabelColor(Color.BLACK);//设置绘制Label的颜色
//pieChart.setEntryLabelTypeface(mTfRegular);
        pieChart.setEntryLabelTextSize(10f);//设置绘制Label的字体大小


        ArrayList<AccountItem> list = DatabaseHelper.getMonthDetail(mYear, mMonth);
        double[] outcomeType = new double[18];
        double[] incomeType = new double[18];
        for (AccountItem i : list) {
            if(i.getType() == AccountPlusApp.TYPE_OUTCOME) {
                outcomeType[i.getIconID() - R.drawable.button_bag] += i.getAmount();
            } else {
                incomeType[i.getIconID() - R.drawable.button_bag] += i.getAmount();
            }
        }

        ArrayList<PieEntry> entriesList = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            if (outcomeType[i] != 0) {
                entriesList.add(new PieEntry((float)outcomeType[i], getResources().getString(R.string.type_bag + i)));
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String s = df.format(DatabaseHelper.getMonthOutcome(mYear, mMonth));
        String centerText = "总支出\n¥"+ s;
        Log.d(TAG, "onCreateView: " + centerText);

        pieChart.setDrawCenterText(false);
        pieChart.setCenterText(centerText);

        PieData pieData = new PieData();
        PieDataSet pieDataSet = new PieDataSet(entriesList, "");
        pieData.setDataSet(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FF8A8A"));
        colors.add(Color.parseColor("#FFAE72"));
        colors.add(Color.parseColor("#68C9BF"));
        colors.add(Color.parseColor("#7DB0EF"));
        colors.add(Color.parseColor("#8C9AAB"));
        colors.add(Color.parseColor("#7FAEE8"));
        colors.add(Color.parseColor("#F68FD5"));
        colors.add(Color.parseColor("#EF7DAC"));

        pieDataSet.setColors(colors);
        pieChart.setData(pieData);

        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);//设置每个tab的显示位置
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);//设置tab之间Y轴方向上的空白间距值
        l.setYOffset(0f);

        graphList.setAdapter(new GraphListAdapter(mYear, mMonth));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
