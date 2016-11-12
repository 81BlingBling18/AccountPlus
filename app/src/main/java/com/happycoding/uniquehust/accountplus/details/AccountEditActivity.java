package com.happycoding.uniquehust.accountplus.details;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.happycoding.uniquehust.accountplus.R;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnPageChange;

public class AccountEditActivity extends AppCompatActivity implements AccountTypeSelectFragment.OnFragmentInteractionListener
        , IncomeTypeSelectFragment.OnFragmentInteractionListener {

    private int mYear, mMonth, mDay;
    private String mRemark;
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
                if (!mEditText.getText().toString().equals("")) {
                    String string = mEditText.getText().toString();
                    mEditText.setText(string.substring(0, string.length() - 1));
                }
                break;
            case R.id.ok:
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.toolbar_button)
    void onToolbarButtonClick(View view) {
        Intent intent = new Intent(AccountEditActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.time_picker)
    void onTimePickerButtonClick(View view) {
        new DatePickerDialog(AccountEditActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                mTimePickerButton.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
            }
        }, mYear, mMonth, mDay).show();
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
                        mRemark = editText.getText().toString();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mYear = Calendar.getInstance().get(Calendar.YEAR);
        mMonth = Calendar.getInstance().get(Calendar.MONTH);
        mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mTimePickerButton.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);

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
    public void onFragmentInteraction(Uri uri) {

    }
}
