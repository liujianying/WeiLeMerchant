package com.ydh.weile.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ydh.weile.interfaces.InitView;
import com.ydh.weile.merchant.R;
import com.ydh.weile.uitl.LogUitl;

public abstract class BaseActivity extends FragmentActivity implements InitView, View.OnClickListener {

	protected static final String TAG = BaseActivity.class.getSimpleName();
    /**
     * 屏幕的宽度、高度、密度
     */
    protected int mScreenWidth;
    protected int mScreenHeight;
    protected float mDensity;
	/** 是否允许全屏 */
	protected boolean mAllowFullScreen = true;
    protected TextView    title_id;
    protected ImageButton ib_back_button;
    protected Context mContext;
	
	
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }

        setContentView(R.layout.header);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
        mDensity = metric.density;
        mContext = this;
    }

    /**
     *
     * @param isShowblack  是否显示回退键
     * @param titleName    标题内容
     */
    protected void setHeading(boolean isShowblack, String titleName) {
        title_id = (TextView) findViewById(R.id.title_id);
        ib_back_button = (ImageButton) findViewById(R.id.ib_back_button);
        ib_back_button.setOnClickListener(this);
        if(isShowblack)
        ib_back_button.setVisibility(View.VISIBLE);
        title_id.setText(titleName);
    }
    


	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		if(intent.resolveActivity(getPackageManager()) != null){
	     	startActivity(intent);
		}else{
            LogUitl.LogE(TAG, "there is no activity can handle this intent: "+intent.getAction().toString());
		}
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		Intent intent = new Intent();
		intent.setAction(action);
		if(intent.resolveActivity(getPackageManager()) != null){
	     	startActivity(intent);
		}else{
			LogUitl.LogE(TAG, "there is no activity can handle this intent: "+intent.getAction().toString());
		}
	}

	/**含有Date通过Action跳转界面**/
	protected void startActivity(String action,Uri data) {
		Intent intent = new Intent();
		intent.setAction(action);
		intent.setData(data);
		if(intent.resolveActivity(getPackageManager()) != null){
	     	startActivity(intent);
		}else{
			LogUitl.LogE(TAG, "there is no activity can handle this intent: "+intent.getAction().toString());
		}
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		if(intent.resolveActivity(getPackageManager()) != null){
	     	startActivity(intent);
		}else{
			LogUitl.LogE(TAG, "there is no activity can handle this intent: "+intent.getAction().toString());
		}
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	
	/** 带有右进右出动画的退出 **/
	public void finish() {
		super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	/** 默认退出 **/
	protected void defaultFinish() {
		super.finish();
	}
    /** 默认退出 **/
    protected void defaultPushFinish() {
        super.finish();
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }




    @Override
    public void onClick(View v) {

        if(v.getId() ==  R.id.ib_back_button) {
            finish();
        }
    }


    /**
     * @隐藏键盘
     * @param event
     */
    protected void HidKeyBoard(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


}
