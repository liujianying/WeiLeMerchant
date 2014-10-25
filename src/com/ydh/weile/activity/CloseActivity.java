package com.ydh.weile.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.ydh.weile.entity.LoginInfo;
import com.ydh.weile.merchant.R;
import com.ydh.weile.net.mode.uitl.LoginMode;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.uitl.FileUtils;
import com.ydh.weile.uitl.StringUtils;
import com.ydh.weile.view.UiAlertViewDialog;

import java.io.File;
import java.util.List;


/**
 * app退出界面（首页点击返回键弹出的对话框）
 * @author 林建鹰
 * */
public class CloseActivity extends Activity {

    public static final  int CLOSE_APPTION = 0;
    public static final  int RESTART_APPTION = 1;
    public static final  int flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;
    private Context mContext;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不显示程序的标题栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);//不显示系统的标题栏
        final View view = View.inflate(this, R.layout.logo, null);
        setContentView(view);

        mContext = this;

        Intent i = this.getIntent();
        if("close".equals(i.getType())){
            //关闭当前页面并且APP退出
            this.finish();
            System.exit(0);
        }else if("restart".equals(i.getType())){
            //选择不退出
            startActivity(new Intent(this,  LogoActivity.class).setFlags(flags));
            return;
        }else {
            //渐变展示启动屏
            AlphaAnimation aa = new AlphaAnimation(1.0f,1.0f);
            aa.setDuration(500);
            view.startAnimation(aa);
            aa.setAnimationListener(new Animation.AnimationListener()
            {
                @Override
                public void onAnimationEnd(Animation arg0) {
                    startActivity(new Intent(CloseActivity.this, LogoActivity.class).setFlags(flags));
                }
                @Override
                public void onAnimationRepeat(Animation animation) {}
                @Override
                public void onAnimationStart(Animation animation) {}

            });
        }
    }

    /**
     *
     * @param context
     * @param closeFlag
     */
    public static final void closeActivity(Context context, int closeFlag) {

        LoginInfo logiInfo = SharePrefs.newSharePrefs().getLoginInfo();
        if((!logiInfo.isAutomaticLogin()) || closeFlag == RESTART_APPTION) {
            SharePrefs.set(context, SharePrefs.LOGIN_INFO, "{}");
        }

        Intent intent = new Intent(context, LogoActivity.class);
        intent.putExtra("CLOSE_APPTION", closeFlag);
        intent.setFlags(CloseActivity.flags);
        context.startActivity(intent);
    }

    /**
     * 退出应用dialog
     */
    public static final class CloseActivityDialog extends UiAlertViewDialog {

        private Context context;
        private boolean isLogout;

        /**
         *
         * @param context
         * @param isLogout  是否做注销请求 true 做注销请求 false 不做注销请求
         */
        public CloseActivityDialog(Context context, boolean isLogout) {
            super(context, "您确定要退出应用么？", "确定退出请点击确认按钮");
            this.context = context;
            this.isLogout = isLogout;
        }
        @Override
        public void viewActionInit() {
            if(isLogout) {
                LoginMode.newLoginMode().requestManagerLogout(context, null);
            } else {
                closeActivity(context, CLOSE_APPTION);
            }

        }
    }






    /**
     * 检查是否需要换图片
     * @param view
     */
    private void check(LinearLayout view) {
        String path = FileUtils.getAppCache(this, "welcomeback");
        List<File> files = FileUtils.listPathFiles(path);
        if (!files.isEmpty()) {
            File f = files.get(0);
            long time[] = getTime(f.getName());
            long today = StringUtils.getToday();
            if (today >= time[0] && today <= time[1]) {
                view.setBackgroundDrawable(Drawable.createFromPath(f.getAbsolutePath()));
            }
        }
    }

    /**
     * 分析显示的时间
     * @param time
     * @return
     */
    private long[] getTime(String time) {
        long res[] = new long[2];
        try {
            time = time.substring(0, time.indexOf("."));
            String t[] = time.split("-");
            res[0] = Long.parseLong(t[0]);
            if (t.length >= 2) {
                res[1] = Long.parseLong(t[1]);
            } else {
                res[1] = Long.parseLong(t[0]);
            }
        } catch (Exception e) {
        }
        return res;
    }
}
