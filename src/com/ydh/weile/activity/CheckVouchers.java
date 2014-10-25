package com.ydh.weile.activity;

import android.os.Bundle;

import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.merchant.R;

/**
 * Created by liujianying on 14-10-11.
 * @验乐商券
 */
public class CheckVouchers extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.check_vouchers);
        initViews();
        initEvents();

    }

    @Override
    public void initViews() {
        setHeading(true, "验乐商券");
    }

    @Override
    public void initEvents() {

    }
}
