package com.ydh.weile.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ydh.weile.merchant.R;
import com.ydh.weile.uitl.ScreenUtils;

public class CustomDialog extends AlertDialog implements DialogInterface.OnCancelListener   {

	private String message;

	private View.OnClickListener btn_okclick;

	private View.OnClickListener btn_cancelclick;

	private OnCancelListener btn_oncancel;

	private String title;

	private Button btn_Ok = null;
	private Button btn_cancel = null;
	private TextView tv_context = null;
	private TextView tv_title = null;
	private ScrollView sv_content;
	LinearLayout ll_update_action;
//	private EditText editText ;
	private Context context;

	public CustomDialog(Context context, View.OnClickListener btn_okclick, View.OnClickListener btn_cancelclick) {
		super(context);
		this.context = context;
		this.btn_okclick = btn_okclick;
		this.btn_cancelclick = btn_cancelclick;

	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_ok_cancel);
		sv_content=(ScrollView) findViewById(R.id.sv_content);
		ll_update_action=(LinearLayout) findViewById(R.id.ll_update_action);

		tv_context = (TextView) findViewById(R.id.dialog_button_context);
		tv_context.setText(message);
		tv_title = (TextView) findViewById(R.id.dialog_button_title);
		tv_title.setText(title);

		int maxheight= ScreenUtils.getScreenHeight(context)/3*2;
		int titleHeight=ScreenUtils.getHeight(tv_title);
		int btnHeight=ScreenUtils.getHeight(ll_update_action);
		int contexHeight=ScreenUtils.getHeight(tv_context);
		btn_Ok = (Button) findViewById(R.id.confirmation);
		btn_Ok.setOnClickListener(btn_okclick);
		btn_cancel = (Button) findViewById(R.id.cancel);
		btn_cancel.setOnClickListener(btn_cancelclick);

		int currentHeight=titleHeight+btnHeight+contexHeight;
		if(currentHeight >maxheight ){
//			sv_content.setMaxHeight(maxheight-titleHeight-btnHeight);
			LayoutParams params=(LayoutParams) sv_content.getLayoutParams();
			params.height = maxheight-titleHeight-btnHeight;
			sv_content.setLayoutParams(params);
		}
	}


	public void setMessage(String msg) {
		message = msg;
	}
	
	public static enum AlertType {
		DIALOG_WAITING, DIALOG_ALERT
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 *
	 * ps
	 */
	public void setMessageGone(){
		tv_context.setVisibility(View.GONE);
	}
	
	/**
	 * 
	 */
	public void setProperty() {
		Window window = this.getWindow(); //
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0; // 
		wl.y = 51;
		// wl.alpha=0.6f;//
		wl.gravity = Gravity.BOTTOM;
		window.setAttributes(wl);
	}

	@Override
	public void onCancel(DialogInterface dialog) {

	}

	@Override
	public void dismiss() {
		super.dismiss();
	}
	
	
	
	
	
}
