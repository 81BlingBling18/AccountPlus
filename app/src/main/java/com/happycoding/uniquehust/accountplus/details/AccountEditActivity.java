package com.happycoding.uniquehust.accountplus.details;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.database.DatabaseHelper;
import com.happycoding.uniquehust.accountplus.global.TypeKeyValue;
import com.happycoding.uniquehust.accountplus.items.AccountItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnPageChange;

public class AccountEditActivity extends AppCompatActivity implements AccountTypeSelectFragment.OnFragmentInteractionListener
        , IncomeTypeSelectFragment.OnFragmentInteractionListener {

    public static final String TAG = "AccountEditActivity";

    private int mYear, mMonth, mDay;
    private int mType;
    private String mTitle;
    private String mDescription;
    private int drawableId;
    private int isIncome;
    @BindView(R.id.account_edit_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.account_edit_radiogroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.account_edit_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.radioButton1)
    RadioButton mRadioButton1;
    @BindView(R.id.radioButton2)
    RadioButton mRadioButton2;
    @BindView(R.id.account_edit_text)
    EditText mEditText;
    @BindView(R.id.toolbar_button)
    ImageButton mToolbarButton;
    @BindView(R.id.time_picker)
    Button mTimePickerButton;
    @BindView(R.id.type_icon_preview)
    ImageButton iconView;
    @BindView(R.id.type_text_preview)
    TextView iconText;

    @OnCheckedChanged(R.id.radioButton1)
    void onCheckedFirst(boolean checked) {
        if (checked) {
            mViewPager.setCurrentItem(0, false);
        }
    }

    @OnCheckedChanged(R.id.radioButton2)
    void onCheckedSecond(boolean checked) {
        if (checked) {
            mViewPager.setCurrentItem(1, false);
        }
    }

    @OnPageChange(R.id.account_edit_viewpager)
    void onPageSelected(int position) {
        if (position == 0 && !mRadioButton1.isChecked()) {
            mRadioGroup.check(R.id.radioButton1);
        } else if (position == 1 && !mRadioButton2.isChecked()) {
            mRadioGroup.check(R.id.radioButton2);
        }
    }

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven,
            R.id.eight, R.id.nine, R.id.zero, R.id.dot, R.id.backspace, R.id.ok})
    void onClick(View view) {
        if (mEditText.getText().toString().equals("0.00")) {
            mEditText.setText("");
        }
        switch (view.getId()) {
            case R.id.one:
                mEditText.setText(mEditText.getText().toString() + "1");
                break;
            case R.id.two:
                mEditText.setText(mEditText.getText().toString() + "2");
                break;
            case R.id.three:
                mEditText.setText(mEditText.getText().toString() + "3");
                break;
            case R.id.four:
                mEditText.setText(mEditText.getText().toString() + "4");
                break;
            case R.id.five:
                mEditText.setText(mEditText.getText().toString() + "5");
                break;
            case R.id.six:
                mEditText.setText(mEditText.getText().toString() + "6");
                break;
            case R.id.seven:
                mEditText.setText(mEditText.getText().toString() + "7");
                break;
            case R.id.eight:
                mEditText.setText(mEditText.getText().toString() + "8");
                break;
            case R.id.nine:
                mEditText.setText(mEditText.getText().toString() + "9");
                break;
            case R.id.zero:
                mEditText.setText(mEditText.getText().toString() + "0");
                break;
            case R.id.dot:
                mEditText.setText(mEditText.getText().toString() + ".");
                break;
            case R.id.backspace:
                if (!mEditText.getText().toString().equals("") && !mEditText.getText().toString().equals("0.00")) {
                    String string = mEditText.getText().toString();
                    mEditText.setText(string.substring(0, string.length() - 1));
                }
                break;
            case R.id.ok:
                if(mTitle != null) {
                    double amount = Double.parseDouble(mEditText.getText().toString());
                    Calendar calendar = Calendar.getInstance();

                    AccountItem item = new AccountItem(mType, mTitle, amount, mDescription, mYear, mMonth, mDay,
                            System.currentTimeMillis(), drawableId);
                    if(amount != 0) {
                        DatabaseHelper.add(item);
                        Log.d(TAG, "onClick: " + mYear + ' ' + mDay);
                        Toast.makeText(this, "创建成功！", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(this, "你确定金额为0喵？", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "请先选择一个类型！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.toolbar_button)
    void onToolbarButtonClick(View view) {
        onBackPressed();
    }

    @OnClick(R.id.time_picker)
    void onTimePickerButtonClick(View view) {
        new DatePickerDialog(AccountEditActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear + 1;
                mDay = dayOfMonth;
                mTimePickerButton.setText(mYear + "-" + mMonth + "-" + mDay);

            }
        }, mYear, mMonth - 1, mDay).show();
    }

    @OnClick(R.id.account_edit_detail)
    void onDetailButtonClick(View view) {
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("备注")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDescription = editText.getText().toString();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AccountTypeSelectFragment.MessageEvent event) {
        Log.d(TAG, "onMessageEvent: Message Received");
        iconView.setPressed(true);
        mTitle = event.type;
        drawableId = event.drawableId;
        mType = (event.isIncome)? 1: 0;
        iconText.setText(event.type);
        iconView.setBackground(getResources().getDrawable(event.drawableId));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent2(IncomeTypeSelectFragment.MessageEvent2 event) {
        Log.d(TAG, "onMessageEvent: Message2 Received");
        iconView.setPressed(true);
        mTitle = event.type;
        drawableId = event.drawableId;
        mType = (event.isIncome)? 1: 0;
        iconText.setText(event.type);
        iconView.setBackground(getResources().getDrawable(event.drawableId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);
        ButterKnife.bind(this);
        new TypeKeyValue();
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mYear = Calendar.getInstance().get(Calendar.YEAR);
        mMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mTimePickerButton.setText(mYear + "-" + mMonth + "-" + mDay);
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AccountTypeSelectFragment());
        fragments.add(new IncomeTypeSelectFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
