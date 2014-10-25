package com.ydh.weile.activity;

import android.os.Bundle;
import android.view.View;

import com.ydh.weile.merchant.R;
import com.ydh.weile.android.BaseActivity;

/**
 * Created by liujianying on 14-10-10.
 * @帮助中心
 */
public class HelpContent extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_content);
        initViews();
        initEvents();
    }

    @Override
    public void initViews() {
        setHeading(true, "帮助中心");
    }

    @Override
    public void initEvents() {

    }
}
