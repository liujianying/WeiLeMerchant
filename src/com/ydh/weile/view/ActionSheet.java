package com.ydh.weile.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydh.weile.merchant.R;


public abstract class ActionSheet {


	public Dialog showSheet(Context context, String [] arrayContent) {
		
		final Dialog dlg = new Dialog(context, R.style.ActionSheet);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.actionsheet, null);
		final int cFullFillWidth = 10000;
		layout.setMinimumWidth(cFullFillWidth);
		 
		TextView frist_cotent = (TextView) layout.findViewById(R.id.frist_cotent);
		TextView last_content = (TextView) layout.findViewById(R.id.last_content);
		TextView cancel = (TextView) layout.findViewById(R.id.cancel);
		frist_cotent.setText(arrayContent[0]);
		last_content.setText(arrayContent[1]);
		cancel.setText(arrayContent[2]);
		
		frist_cotent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fristMethod();
				dlg.dismiss();
			}
		});

		last_content.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lastMethod();
				dlg.dismiss();
			}
		});

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancelMethod();
				dlg.dismiss();
			}
		});

		Window w = dlg.getWindow();
		WindowManager.LayoutParams lp = w.getAttributes();
		lp.x = 0;
		final int cMakeBottom = -1000;
		lp.y = cMakeBottom;
		lp.gravity = Gravity.BOTTOM;
		dlg.onWindowAttributesChanged(lp);
		dlg.setCanceledOnTouchOutside(false);
		dlg.setContentView(layout);
		dlg.show();

		return dlg;
	}
	
	public abstract void cancelMethod();
	public abstract void lastMethod();
	public abstract void fristMethod();

}
