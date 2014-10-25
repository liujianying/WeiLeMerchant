package com.ydh.weile.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ydh.weile.merchant.R;
import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.system.config.TelephoneUtil;

/**
 * Created by liujianying on 14-10-10.
 * @关于界面
 */
public class AboutAcitity extends BaseActivity{


    private TextView tv_go_website;         //官网
    private TextView tv_version_name;       //微乐商户版本


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        initViews();
        initEvents();
    }

    @Override
    public void initViews() {
        setHeading(true, "关于");
        tv_go_website = (TextView) findViewById(R.id.tv_go_website);
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
    }

    @Override
    public void initEvents() {
        tv_version_name.setText("微乐商户"+ TelephoneUtil.getVersionName(mContext));
        tv_go_website.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        tv_go_website.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.yidinghuo.com.cn");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }

        });
    }
}
