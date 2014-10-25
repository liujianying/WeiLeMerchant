package com.ydh.weile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.entity.LoginInfo;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.merchant.R;
import com.ydh.weile.net.HttpRequestBody;
import com.ydh.weile.net.mode.uitl.LoginMode;
import com.ydh.weile.system.config.NetExceptionUitl;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.uitl.LogUitl;
import com.ydh.weile.uitl.ToastUitl;
import com.ydh.weile.view.CircleImageView;

import org.json.JSONException;


/**
 * Created by liujianying on 14-10-10.
 * @登陆界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher{


    private LoginInfo logiInfo = new LoginInfo();            //用户信息

    private CircleImageView        user_avatar;            //头像
    private RelativeLayout        button_login;            //登陆按钮
    private EditText         editText_username;            //用户名
    private EditText         editText_password;            //用户密码
    private CheckBox       well_known_mark_img;            //自动登录
    private ScrollView             scroll_view;
    private ProgressBar        btn_progressbar;            //登录请求加载框
    private TextView                login_text;            //登录text
    private View                          view;


    private String editText_usernameStr;
    private String editText_passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initViews();
        initEvents();
    }


    @Override
    public void initViews() {
        setHeading(false, "登录");
        btn_progressbar = (ProgressBar) findViewById(R.id.btn_progressbar);
        user_avatar = (CircleImageView) findViewById(R.id.user_avatar);
        button_login = (RelativeLayout) findViewById(R.id.button_login);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_username = (EditText) findViewById(R.id.editText_username);
        well_known_mark_img = (CheckBox) findViewById(R.id.well_known_mark_img);
        scroll_view = (ScrollView) findViewById(R.id.scroll_view);
        login_text = (TextView) findViewById(R.id.login_text);
        view = (View) findViewById(R.id.view);

    }



    @Override
    public void initEvents() {

        scroll_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                HidKeyBoard(event);
                return false;
            }
        });



        editText_password.addTextChangedListener(this);
        editText_username.addTextChangedListener(this);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        logiInfo = SharePrefs.newSharePrefs().getLoginInfo();

        if(logiInfo.isAutomaticLogin()) {
            editText_password.setText(logiInfo.getPassword());
            editText_username.setText(logiInfo.getUserName());
            LoginMode.newLoginMode().startLogin(mContext, logiInfo.getUserName(), logiInfo.getPassword(), logiInfo.isAutomaticLogin(), handler);

        }else {
            button_login.setOnClickListener(this);
            button_login.setClickable(false);
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.button_login:
                getTextData();

                if(TextUtils.isEmpty(editText_usernameStr)) {
                    ToastUitl.showToast(this, "用户名不能为空！");
                    return;
                }

                if(TextUtils.isEmpty(editText_passwordStr)) {
                    ToastUitl.showToast(this, "密码不能为空！");
                    return;
                }

//                cu_blyj  123456

                LoginMode.newLoginMode().startLogin(mContext, "cu_blyj", "123456", well_known_mark_img.isChecked(), handler);
                btn_progressbar.setVisibility(View.VISIBLE);
                login_text.setText("登录中...");
                view.setVisibility(View.VISIBLE);
                break;
        }
    }


    /**
     * 获取输入框数据
     */
    private void getTextData() {
        editText_usernameStr = editText_username.getText().toString().trim();
        editText_passwordStr = editText_password.getText().toString().trim();
    }



    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
           btn_progressbar.setVisibility(View.GONE);
           login_text.setText("登录");
           view.setVisibility(View.GONE);

            switch (msg.what) {

                case NetCode.RequestSuccess:
                    //登录成功跳转到home界面
                    ToastUitl.showToast(mContext, "登录成功");
                    Intent intent = new Intent();
                    intent.setClass(mContext, HomePage.class);
                    startActivity(intent);
                    defaultPushFinish();
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    break;
                case NetCode.RequestFailed:
                    NetExceptionUitl.newNetExceptionUitl().showExceptionToast(mContext,(Integer)msg.obj);
                    break;
                case NetCode.System_Error:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            new CloseActivity.CloseActivityDialog(LoginActivity.this, false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        getTextData();
        if(TextUtils.isEmpty(editText_passwordStr) || TextUtils.isEmpty(editText_usernameStr)) {
            button_login.setClickable(false);
        } else {
            button_login.setClickable(true);
        }
    }
}
