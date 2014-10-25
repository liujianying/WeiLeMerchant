package com.ydh.weile.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ydh.weile.merchant.R;
import com.ydh.weile.net.HttpUitl;


/**
 * LOGO界面
 * @author Administrator
 * 
 */
public class LogoActivity extends Activity {


    public static final  int CLOSE_APPTION = 0;
    public static final  int RESTART_APPTION = 1;

    private Thread  CacheThread;
	private ImageView logo;
	private final int inquireBootImagesSuccess = 4;
	private final int LoadConfigSucess = 701;
	private final int LoadConfigFail = 702;
	private final int LoadAnimaStart = 703;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不显示程序的标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//不显示系统的标题栏
		setContentView(R.layout.logo);

        startActivity(new Intent(this, LoginActivity.class));

	}



	

	/**
	 * 向桌面添加快捷方式
	 */
	private void addShortcut() {
		Intent shortcutIntent = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
				getString(R.string.app_name));
		shortcutIntent.putExtra("duplicate", false);
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setComponent(new ComponentName(this.getPackageName(), this
				.getPackageName() + ".activity.LogoActivity"));
		shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(this, R.drawable.logo));
		sendBroadcast(shortcutIntent);
		//设置创建了快捷方式为true
//		SharePrefs.set(this, SharePrefs.IsCreateShortcut, true);
	}



    @Override
    protected void onNewIntent(Intent intent) {

        int StartLogo = intent.getIntExtra("CLOSE_APPTION", -1);
        Intent intent1 = new Intent(LogoActivity.this, CloseActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if(CLOSE_APPTION == StartLogo){
            //关闭当前页面并且APP退出
            intent1.setType("close");

        }else if(RESTART_APPTION == StartLogo){
//			//选择不退出
            intent1.setType("restart");
        }

        startActivity(intent1);

        finish();
    }
}