package com.ydh.weile.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ydh.weile.merchant.R;

public class LoadDataView extends  FrameLayout {
	private Context context;
	private View view;
	
	private RelativeLayout rl_loadLayout;
	private ProgressBar loadingImageView;
	private TextView loading_text;
	
	private LinearLayout ll_responseLayout;
	private ImageView iv_response;
	private TextView tv_response;
	private OnResponseImageClickListener listener;
	
	private LinearLayout ll_loginLayout;
	private Button btn_login;
	private onLoginButtonClickListener loginListener;
	
	private View showView;
	private View customView;
	private boolean isShow = false;
	
	public enum LoadResponse{
		Sucess,
		Fail,
		NoNetWork,
		NoData,
		Custom,
		Login
	}


    public LoadDataView(Context context) {
		super(context);
		this.context = context;
		initLayout();
	}
	
	 public LoadDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initLayout();
	 }

    public LoadDataView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initLayout();
    }
	
    private void initLayout(){
    	view = LayoutInflater.from(this.context).inflate(R.layout.weile_loading_layout, null);
    	LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, Gravity.CENTER);
    	Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.content_loading_01);
    	int width = bm.getWidth();
    	int height = bm.getHeight();
    	bm.recycle();
    	bm = null;
    	addView(view,lp);
    	
    	rl_loadLayout = (RelativeLayout)view.findViewById(R.id.rl_loadLayout);
    	loadingImageView = (ProgressBar)view.findViewById(R.id.pb_loading);
    	RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(width, height);
    	lp2.addRule(RelativeLayout.CENTER_HORIZONTAL);
    	loadingImageView.setLayoutParams(lp2);
    	loading_text = (TextView)view.findViewById(R.id.loading_text);
    	
    	ll_responseLayout = (LinearLayout)view.findViewById(R.id.ll_responseLayout);
    	iv_response = (ImageView)view.findViewById(R.id.iv_response);
    	tv_response = (TextView)view.findViewById(R.id.tv_response);
    	
    	ll_loginLayout = (LinearLayout)view.findViewById(R.id.ll_loginLayout);
    	btn_login = (Button)view.findViewById(R.id.btn_login);
    }
    
    public void setText(String text){
    	if(loading_text != null){
    		loading_text.setText(text);
    	}
    }
    
    public void setTextSize(int size){
    	if(loading_text != null){
    		loading_text.setTextSize(size);
    	}
    }
    
    public void setText(String text,int colorID){
    	if(loading_text != null){
    		loading_text.setText(text);
    		if(colorID != 0){
    			loading_text.setTextColor(getResources().getColor(colorID));
    		}
    	}
    }
  
    public void setLoadSucessView(View view){
    	this.showView = view;
    	isShow = true;
    }
    
    /**
     * 设置加载失败时候显示的动画
     * @param drawableId
     */
    public void setLoadFailViewImage(int drawableId){
    	iv_response.setImageResource(drawableId);
    }
    
    public void setCustomView(View view ){
    	customView = view;
    }
  
    
    /**
     * 显示加载动画
     */
    public void show(){
    	rl_loadLayout.setVisibility(View.VISIBLE);
    	
    	if(showView != null)
    		showView.setVisibility(View.GONE);
    	if(customView != null)
    		customView.setVisibility(View.GONE);
		ll_responseLayout.setVisibility(View.GONE);
		ll_loginLayout.setVisibility(View.GONE);
    	setVisibility(View.VISIBLE);
    	isShow = true;
    }



	
	public void closed(LoadResponse response){
		if(response == LoadResponse.Sucess){
			setVisibility(View.GONE);
			if(showView != null)
				showView.setVisibility(View.VISIBLE);
		}else if(response == LoadResponse.Fail){
			tv_response.setText("加载失败,请重试!");
			rl_loadLayout.setVisibility(View.GONE);
			ll_responseLayout.setVisibility(View.VISIBLE);
			if(showView != null)
				showView.setVisibility(View.GONE);
		}else if(response == LoadResponse.NoNetWork){
			tv_response.setText(getResources().getString(R.string.NoNetworkConnection));
			rl_loadLayout.setVisibility(View.GONE);
			ll_responseLayout.setVisibility(View.VISIBLE);
			if(showView != null)
				showView.setVisibility(View.GONE);
		}else if(response == LoadResponse.NoData){
			tv_response.setText("亲,没有您想要的数据!");
			rl_loadLayout.setVisibility(View.GONE);
			ll_responseLayout.setVisibility(View.VISIBLE);
			if(showView != null)
				showView.setVisibility(View.GONE);
		}else if(response == LoadResponse.Custom){
			setVisibility(View.GONE);
			if(customView != null){
				customView.setVisibility(View.VISIBLE);
			}
			if(showView != null)
				showView.setVisibility(View.GONE);
		}else if(response == LoadResponse.Login){
			rl_loadLayout.setVisibility(View.GONE);
			ll_responseLayout.setVisibility(View.GONE);
			if(showView != null)
				showView.setVisibility(View.GONE);
			if(customView != null)
				customView.setVisibility(View.GONE);
			ll_loginLayout.setVisibility(View.VISIBLE);
		}
		isShow = false;
	}
	
	
	public boolean isShow(){
		return isShow;
	}
	
	public void setOnResponseImageClickListener(OnResponseImageClickListener l){
		this.listener = l;
		if(iv_response != null){
			iv_response.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listener.OnClickListener();
				}
			});
		}
	}
	
	public void setOnLoginButtonClickListener(onLoginButtonClickListener l){
		this.loginListener = l;
		if(btn_login != null){
			btn_login.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					loginListener.OnClickListener();
				}
			});
		}
	}
	
	
	
	public interface OnResponseImageClickListener{
		public void OnClickListener();
	}
	
	public interface onLoginButtonClickListener{
		public void OnClickListener();
	}
	
	
	
}  
