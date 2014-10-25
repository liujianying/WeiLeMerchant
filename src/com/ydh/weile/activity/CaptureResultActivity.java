package com.ydh.weile.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.merchant.R;

public class CaptureResultActivity extends BaseActivity {
	
	private Bitmap capture_result_bitmap;
	private String result_str;
	private ImageView iv_twodimensionode;
	private TextView tv_result;
	private Button btn_openUrl;
	private ImageButton btn_back;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capture_result);
//		System.out.println("跳转到结果页面");
		byte[] bitmap_byte = getIntent().getByteArrayExtra("bitmap");
		//capture_result_bitmap = getIntent().getParcelableExtra("bitmap");
		result_str = getIntent().getStringExtra("result");
//		System.out.println("获取到二维码数据");
		iv_twodimensionode = (ImageView)findViewById(R.id.iv_twodimensionode);
		tv_result = (TextView)findViewById(R.id.tv_result);
		btn_openUrl = (Button)findViewById(R.id.btn_openUrl);
		
		if(bitmap_byte != null && bitmap_byte.length > 0){
			capture_result_bitmap = BitmapFactory.decodeByteArray(bitmap_byte, 0, bitmap_byte.length);
			iv_twodimensionode.setImageBitmap(capture_result_bitmap);
		}
		
		if(!TextUtils.isEmpty(result_str)){
			tv_result.setText(result_str);
			if(result_str.startsWith("http://")){
				btn_openUrl.setVisibility(View.VISIBLE);
				btn_openUrl.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Uri uri = Uri.parse(result_str);  
					    startActivity(new Intent(Intent.ACTION_VIEW,uri));
					}
				});
			}else{
				btn_openUrl.setVisibility(View.GONE);
			}
		}else{
			btn_openUrl.setVisibility(View.GONE);
		}
		
		btn_back = (ImageButton)findViewById(R.id.btn_back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CaptureResultActivity.this.finish();
			}
		});
		
		
	}


    @Override
    public void initViews() {

    }

    @Override
    public void initEvents() {

    }
}