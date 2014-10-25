package com.ydh.weile.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydh.weile.merchant.R;


/**
 * @author Administrator
 * @弹出对话框
 */ 
public abstract class UiAlertViewDialog {

    public static boolean isShow;
	public Dialog dialog;
	protected Context context;
	private String titleString = "";
	private String titleContentString = "";
	private TextView tv_title;
	private TextView tv_title_cotent;


	public UiAlertViewDialog(Context context, String titleString, String titleContentString) {
		this.context = context;
		this.titleString = titleString;
		this.titleContentString = titleContentString;
		initLayout(R.layout.boss_unipay_alert_havebtn);
	}
	
	/**
	 * @初始化dialog
	 * @默认xml布局 最外层为LinearLayout
	 * @param id
	 */
	private void initLayout(int id){
		
		// 1. 布局文件转换为View对象
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) inflater.inflate(id, null);
		// 2. 新建对话框对象
		dialog = new AlertDialog.Builder(context).create();
		dialog.setCancelable(false);
		dialog.show();
		dialog.getWindow().setContentView(layout);
        isShow = true;
		Button cancel = (Button) layout.findViewById(R.id.cancel);
		Button confirmation = (Button) layout.findViewById(R.id.confirmation);
		tv_title = (TextView) layout.findViewById(R.id.tv_title);
		tv_title_cotent = (TextView) layout.findViewById(R.id.tv_title_cotent);
		tv_title_cotent.setText(titleContentString);
		tv_title.setText(titleString);
		
		if(TextUtils.isEmpty(titleContentString)){
			tv_title_cotent.setVisibility(View.GONE);
		}
		
		if(TextUtils.isEmpty(titleString)){
			tv_title.setVisibility(View.GONE);
		}
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismissDialog();
			}
		});
		confirmation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				dismissDialog();
				viewActionInit();
//				DialogUitl.showDialog("取消退款申请请求，请稍等...", context);
//				RefundUitl.requestCancelApply(orderItemId, handler);
			}
		});
	}

	/**
	 * @关闭dialog
	 */
	public void dismissDialog(){

		if(dialog != null && dialog.isShowing() ){
			dialog.dismiss();
            isShow = false;
		}
	}
	
	public void setTitleSpace(float add,float mult){
		
		if(null !=  tv_title){
			tv_title.setLineSpacing(add, mult);
		}
	}
	
	/**
	 * @方法实现
	 * @param
	 */
	public abstract void viewActionInit();
	
}
