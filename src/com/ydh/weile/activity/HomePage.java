package com.ydh.weile.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.android.WeiLeMerchantApp;
import com.ydh.weile.entity.NowDayConsumeInfoEntity;
import com.ydh.weile.entity.UpdateVersionEntity;
import com.ydh.weile.entity.UserInfo;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.merchant.R;
import com.ydh.weile.net.mode.uitl.HomePageMode;
import com.ydh.weile.net.mode.uitl.LoginMode;
import com.ydh.weile.system.config.NetExceptionUitl;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.uitl.LogUitl;
import com.ydh.weile.uitl.PullToRefresh;
import com.ydh.weile.uitl.ScreenUtils;
import com.ydh.weile.uitl.StringUtils;

/**
 * Created by liujianying on 14-10-9.
 */
@SuppressLint("NewApi")
public class HomePage extends BaseActivity implements View.OnTouchListener, NetCode{

    /** 获取用户信息标识 */
    private final int appGetUserInfoFlag       = 10086;
    /** 获取今日交易信息标识 */
    private final int appNowDayConsumeInfoFlag = 10087;
    /** 获取版本升级标识 */
    private final int appVersionUpdateFlag     = 10088;

    private PullToRefresh pull;
    private TextView shop_name;              //店铺名称
    private TextView employees_name;         //员工名称
    private TextView today_total_money;      //今天交易金额
    private TextView total_number;           //总共笔数
    private TextView coupons;                //优惠券
    private TextView vouchers;               //代金券
    private TextView membership_card;        //会员卡
    private TextView cards;                  //储值卡
    private ImageView iv_more_btn;            //跳转更多界面图标
    private View line;                       //虚线
    private ScrollView scroll_all;
    private LinearLayout ll_num1, ll_num2, ll_num3, layout_inner;
    private Context context;
    /** button1 扫一扫  button2 消息中心  button3 验证商券 button4 交易中心 button5更多 */
    private Button button1, button2, button3, button4, button5;
    private UserInfo userInfo;
    private NowDayConsumeInfoEntity nowDayConsumeInfoEntity = new NowDayConsumeInfoEntity();
    private UpdateVersionEntity updateVersionEntity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        setContentView(R.layout.wl_merchant);
        this.context = this;
        initViews();
        initEvents();
    }




    @Override
    public void initViews() {

        userInfo = SharePrefs.newSharePrefs().getUserInfo();
        scroll_all = (ScrollView) findViewById(R.id.scroll_all);
        iv_more_btn = (ImageView) findViewById(R.id.iv_more_btn);
        line = (View) findViewById(R.id.line);                                  //虚线
        button1 = (Button) findViewById(R.id.button1);                          //扫一扫
        button2 = (Button) findViewById(R.id.button2);                          //消息中心
        button3 = (Button) findViewById(R.id.button3);                          //验证商券
        button4 = (Button) findViewById(R.id.button4);                          //交易中心
        button5 = (Button) findViewById(R.id.button5);                          //更多
        shop_name = (TextView) findViewById(R.id.shop_name);                    //店铺名称
        employees_name = (TextView) findViewById(R.id.employees_name);          //员工名称
        today_total_money = (TextView) findViewById(R.id.today_total_money);    //今天交易金额
        total_number = (TextView) findViewById(R.id.total_number);              //总共笔数
        coupons = (TextView) findViewById(R.id.coupons);                        //优惠券
        shop_name = (TextView) findViewById(R.id.shop_name);
        vouchers = (TextView) findViewById(R.id.vouchers);                      //代金券
        membership_card = (TextView) findViewById(R.id.membership_card);        //会员卡
        cards = (TextView) findViewById(R.id.cards);                            //储值卡
        ll_num1 = (LinearLayout) findViewById(R.id.ll_num1);
        ll_num2 = (LinearLayout) findViewById(R.id.ll_num2);
        ll_num3 = (LinearLayout) findViewById(R.id.ll_num3);
        layout_inner = (LinearLayout) findViewById(R.id.layout_inner);

        int height = (int)((mScreenWidth - (int)ScreenUtils.dpToPx(this, 30))/2 * 0.6);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, height);
        layoutParams.bottomMargin = (int) ScreenUtils.dpToPx(this, 10);
        ll_num1.setLayoutParams(layoutParams);
        ll_num2.setLayoutParams(layoutParams);
        ll_num3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, height));
        if (android.os.Build.VERSION.SDK_INT >= 11) {
        line.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        iv_more_btn.setVisibility(View.VISIBLE);
        pull = new PullToRefresh(scroll_all, layout_inner, this);
        pull.setFooterIsUsed(false);

    }





    @Override
    public void initEvents() {

        //获取版本升级
        LoginMode.newLoginMode().appVersionUpdate(mContext, handler, appVersionUpdateFlag);
        //获取用户信息
        HomePageMode.newHomePageMode().appGetUserInfo(mContext, handler, appGetUserInfoFlag);
        //获取今日收款金额,今日卡券消费数
        HomePageMode.newHomePageMode().appNowDayConsumeInfo(mContext, handler, appNowDayConsumeInfoFlag);

        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);
        button5.setOnTouchListener(this);
        iv_more_btn.setOnClickListener(this);

        pull.setOnHeaderUpdatingListener(new PullToRefresh.OnUpdatingListener() {

            @Override
            public void onHeaderUpdating(PullToRefresh puller) { HomePageMode.newHomePageMode().appNowDayConsumeInfo(mContext, handler, appNowDayConsumeInfoFlag);
            }

            @Override
            public void onFooterUpdating(PullToRefresh puller) { puller.setFooterUpdatingComplete();
            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(R.id.iv_more_btn == v.getId())
        {

            startActivity(MoreActivity.class);
        }
    }

    /**
     * 设置用户信息
     */
    private void setUserInfoData() {

        if(userInfo ==  null) return;
        //商家名
        shop_name.setText(userInfo.getMerchantName());
        //操作员设置
        if("1".equals(userInfo.getIsSon())) {
            employees_name.setText(userInfo.getUserName());
            employees_name.setVisibility(View.VISIBLE);
        } else {
            employees_name.setVisibility(View.GONE);
        }

    }

    /**
     * 设置今日收款金额,今日卡券消费数
     */
    private void setnowDayConsumeInfoData() {

        if(nowDayConsumeInfoEntity == null) return;
//        private TextView today_total_money;      //今天交易金额
//        private TextView total_number;           //总共笔数
//        private TextView coupons;                //优惠券
//        private TextView vouchers;               //代金券
//        private TextView membership_card;        //会员卡
//        private TextView cards;                  //储值卡
//        "consumeAmount":"今日交易款",
//        "consumeNum":"今日交易卡券数",
//        "vcardNum":"储值卡消费数",
//        "cashCouponNum":"代金券消费数",
//        "couponNum":"优惠券消费数",
//        "mcardNum":"会员卡消费数",
        today_total_money.setText(getString(R.string.home_page_total_money, nowDayConsumeInfoEntity.getConsumeAmount()));
        total_number.setText(getString(R.string.home_page_number, nowDayConsumeInfoEntity.getConsumeNum()));
        coupons.setText(nowDayConsumeInfoEntity.getCouponNum());
        vouchers.setText(nowDayConsumeInfoEntity.getVcardNum());
        membership_card.setText(nowDayConsumeInfoEntity.getMcardNum());
        cards.setText(nowDayConsumeInfoEntity.getVcardNum());
        StringUtils.setTextColor(today_total_money, 6, today_total_money.getText().toString().length(), getResources().getColor(R.color.title_color));
    }


    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        int action = event.getAction();
        Animation anim = null;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                anim = AnimationUtils.loadAnimation(context, R.anim.scale_in);
                v.startAnimation(anim);
                myAnimationListener.setView(v);
                myAnimationListener.startAnimation();
                anim.setAnimationListener(myAnimationListener);
                break;
            case MotionEvent.ACTION_CANCEL:
                myAnimationListener.stopAnimation();
                break;
            case MotionEvent.ACTION_UP:
                myAnimationListener.stopAnimation();
                anim = AnimationUtils.loadAnimation(context, R.anim.scale_out);
                v.startAnimation(anim);

                anim.setAnimationListener(new MyAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        myOnClick(v);
                    }
                });
                break;
        }

        LogUitl.SystemOut(action);
        return false;
    }


    private void myOnClick(View v) {
        switch (v.getId()) {
            case R.id.button1://扫一扫
                startActivity(QrCodeActivity.class);
                break;
            case R.id.button2://消息中心
                startActivity(MessageCenter.class);
                break;
            case R.id.button3://验乐商券
                startActivity(CheckVouchers.class);
                break;
            case R.id.button4://交易记录
                startActivity(TransactionRecords.class);
                break;
            case R.id.button5://更多
                startActivity(MoreActivity.class);
                break;
        }
    }

    private MyAnimationListener myAnimationListener = new MyAnimationListener();

    private class MyAnimationListener implements Animation.AnimationListener {

        private View v;

        private boolean isStop;

        private void setView(View v) {
            this.v = v;
        }

        private void stopAnimation() {
            isStop = true;
        }

        private void startAnimation() {
            isStop = false;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (!isStop) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in_static);
                v.startAnimation(anim);
                anim.setAnimationListener(this);
            }
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            new CloseActivity.CloseActivityDialog(HomePage.this, true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if(appNowDayConsumeInfoFlag == msg.arg1) {
                pull.finshRefresh();
            }

            switch (msg.what)
            {
                case NetCode.RequestSuccess:


                    switch (msg.arg1) {
                        case appNowDayConsumeInfoFlag: //获取今日收款金额,今日卡券消费数
                            nowDayConsumeInfoEntity = (NowDayConsumeInfoEntity) msg.obj;
                            setnowDayConsumeInfoData();
                            break;
                        case appGetUserInfoFlag:      //获取用户信息
                            userInfo = (UserInfo)msg.obj;
                            setUserInfoData();
                            //保存用户数据数据
                            SharePrefs.set(WeiLeMerchantApp.wlmApp, SharePrefs.USER_INFO, JSON.toJSONString(userInfo));
                            break;
                        case appVersionUpdateFlag:     //版本升级

                            break;
                    }

                    break;

                case NetCode.System_Error:
                    break;

                case NetCode.RequestFailed:
                    NetExceptionUitl.newNetExceptionUitl().showExceptionToast(mContext,(Integer)msg.obj);
                    break;

                default:
                    break;
            }
        }
    };

}
