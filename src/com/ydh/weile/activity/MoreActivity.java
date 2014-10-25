package com.ydh.weile.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.android.WeiLeMerchantApp;
import com.ydh.weile.entity.UpdateVersionEntity;
import com.ydh.weile.entity.UserInfo;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.merchant.R;
import com.ydh.weile.net.mode.uitl.LoginMode;
import com.ydh.weile.system.config.NetExceptionUitl;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.system.config.UpdateApkUtil;
import com.ydh.weile.view.ActionSheet;
import com.ydh.weile.view.CircleImageView;

/**
 * Created by liujianying on 14-10-9.
 * @更多界面
 */
public class MoreActivity extends BaseActivity implements View.OnClickListener{

    private CircleImageView img_ico;                      //用户头像
    private TextView user_name;                     //用户名
    private TextView tv_shop_name;                  //公司名
    private TextView provinces;                     //省市区
    private TextView address;                       //地址

    private Button btn_help;                        //帮助中心
    private Button problem_feedback;                //问题反馈
    private Button check_update;                    //查看更新
    private Button about_wlm;                       //关于微乐商户
    private Button exit_app;                        //退出

    private UserInfo userInfo;
    private UpdateVersionEntity updateVersionEntity;//版本升级实体类


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_activity);
        initViews();
        initEvents();
    }

    @Override
    public void initViews() {

        setHeading(true, "更多");
        img_ico = (CircleImageView) findViewById(R.id.img_ico);             //用户头像
        user_name = (TextView) findViewById(R.id.user_name);                //用户名
        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);          //公司名
        provinces = (TextView) findViewById(R.id.provinces);                //省市区
        address = (TextView) findViewById(R.id.address);                    //地址

        btn_help = (Button) findViewById(R.id.btn_help);                    //帮助中心
        problem_feedback = (Button) findViewById(R.id.problem_feedback);    //问题反馈
        check_update = (Button) findViewById(R.id.check_update);            //查看更新
        about_wlm = (Button) findViewById(R.id.about_wlm);                  //关于微乐商户
        exit_app = (Button) findViewById(R.id.exit_app);                    //退出
        userInfo = SharePrefs.newSharePrefs().getUserInfo();
    }

    @Override
    public void initEvents() {

        exit_app.setOnClickListener(this);
        btn_help.setOnClickListener(this);
        about_wlm.setOnClickListener(this);
        check_update.setOnClickListener(this);
        problem_feedback.setOnClickListener(this);
        setUserData();
    }

    /**
     * 设置用户信息
     */
    private void setUserData() {
        if(userInfo == null) return;
        user_name.setText(userInfo.getUserName());
        tv_shop_name.setText(userInfo.getMerchantName());
        provinces.setText(userInfo.getAreaName());
        address.setText(userInfo.getAddress());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.exit_app:
                String [] showContent =  {"完全退出", "注销账号", "取消"};
                new SystemExitPop().showSheet(mContext, showContent);
                break;

            case R.id.btn_help:
                startActivity(HelpContent.class);
                break;

            case R.id.problem_feedback:
                startActivity(ProblemFeedback.class);
                break;

            case R.id.about_wlm:
                startActivity(AboutAcitity.class);
                break;
            case R.id.check_update:
                //获取版本升级
//                LoginMode.newLoginMode().appVersionUpdate(mContext, handler, 0);
                UpdateApkUtil.getUpdateApkUtil().checkAppUpdate(this, true);
                break;
            default:
                break;
        }
    }


    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case NetCode.RequestSuccess:
                    updateVersionEntity = (UpdateVersionEntity) msg.obj;


                    break;
                case NetCode.RequestFailed:
                    NetExceptionUitl.newNetExceptionUitl().showExceptionToast(mContext, msg.arg1);
                    break;
                case NetCode.System_Error:
                    break;
            }
        }
    };

    class SystemExitPop extends ActionSheet {

        @Override
        public void cancelMethod() {}

        @Override
        public void lastMethod() {//退出登录 之后的切换账号
            LoginMode.newLoginMode().requestManagerLogout(mContext, new Handler()
                {
                        @Override
                        public void handleMessage(Message msg) {

                            switch (msg.what) {
                                case NetCode.RequestSuccess:
                                    CloseActivity.closeActivity(mContext, CloseActivity.RESTART_APPTION);
                                    break;

                                case NetCode.RequestFailed:
                                    NetExceptionUitl.newNetExceptionUitl().showExceptionToast(mContext, (Integer)msg.obj);
                                    break;

                                case NetCode.System_Error:
                                    break;

                                default:
                                    break;
                            }
                        }
                    }
            );
        }

        @Override
        public void fristMethod() {//完全退出
            LoginMode.newLoginMode().requestManagerLogout(mContext, null);
        }
    }

}
