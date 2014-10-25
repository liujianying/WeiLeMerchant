package com.ydh.weile.activity;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.squareup.picasso.Picasso;
import com.ydh.weile.merchant.R;
import com.ydh.weile.android.BaseActivity;
import com.ydh.weile.uitl.LogUitl;
import com.ydh.weile.view.ActionSheet;

public class MainActivity extends BaseActivity {

    AQuery aq;

//    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
//		Toast.makeText(this, BuildConfig.DEBUG + "", Toast.LENGTH_SHORT).show();
//        new codeImageAction().showSheet(this, new String[]{"复制我的邀请链接","保存二维码到手机","取消"});
//        String str = "12A3";
//        System.out.print(BuildConfig.DEBUG);
//        LogUitl.SystemOut(SafetyTypeConversionUitl.tryInt(str, 999));

//        aq = new AQuery(this);
//        String imageUrl = "http://img5.duitang.com/uploads/item/201407/13/20140713205545_Z2zkr.thumb.700_0.jpeg";
//        aq.id(R.id.image).progress(R.id.progress).image(imageUrl, false, false);

//        String imageUrl = "http://www.vikispot.com/z/images/vikispot/android-w.png";
//        aq.id(R.id.image).image(imageUrl, true, true, 0, R.drawable.login);
//        aq.id(R.id.image).image(imageUrl, true, true, 0, 0, new BitmapAjaxCallback(){
//
//            @Override
//            public void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status){
//
//                iv.setImageBitmap(bm);
//
//
//            }
//
//        });

//        aq.id(R.id.image).background(R.drawable.login).image(imageUrl, true, true, 399, R.drawable.non_pic_defaults);
//        aq.id(R.id.image).getImageView().setScaleType(ImageView.ScaleType.FIT_XY);

//        asyncJson();

        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryClass();
        LogUitl.SystemOut(activityManager.getMemoryClass() + "===");


//        try {
//            LogUitl.SystemOut( post(imageUrl, "")+ "===");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        OkHttpClient okHttpClient = new OkHttpClient();
////        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(okHttpClient)).build();
//        OkHttpDownloader downloader = new OkHttpDownloader(okHttpClient);
//        Picasso picasso = new Picasso.Builder(this).downloader(downloader).build();
        ImageView view = (ImageView) findViewById(R.id.image);
//        picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(view);
//        Picasso.with(this)
//                .load("http://farm8.staticflickr.com/7151/6760135001_14c59a1490_o_d.jpg")
//                .resize(500, 500)
//                .centerCrop()
//                .into(view);
        Picasso.with(this)
                .setIndicatorsEnabled(true);
        Picasso.with(this)
                .load("http://farm8.staticflickr.com/7151/6760135001_14c59a1490_o_d.jpg")
                .resize(500, 500)
                .placeholder(R.drawable.face_girl_notice)
                .error(R.drawable.login)
                .into(view);

    }



//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(MediaType.parse(Me),json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

//    public void asyncJson() {
//
//        //perform a Google search in just a few lines of code
//
//        String url = "http://www.google.com/uds/GnewsSearch?q=Obama&v=1.0";
//        aq.ajax(url, JSONObject.class, 3000, this, "jsonCallback");
//    }
//
//    public void jsonCallback(String url, JSONObject json, AjaxStatus status) {
//
//        if (json != null) {
//            //successful ajax call
//            LogUitl.LogD(TAG, "-------  " + JSON.toJSONString(json));
//        } else {
//            //ajax error
//            LogUitl.LogD(TAG, "------error--  --" + status.getCode());
//        }
//
//
//        LogUitl.LogD(TAG, "status = " + JSON.toJSONString(status));
//        LogUitl.LogD(TAG, "url = " + url);
//
//    }

    @Override
    public void initViews() {

    }

    @Override
    public void initEvents() {

    }


    class codeImageAction extends ActionSheet {

        @Override
        public void cancelMethod() {
        }

        @Override
        public void lastMethod() {


        }

        @Override
        public void fristMethod() {

        }
    }
}
