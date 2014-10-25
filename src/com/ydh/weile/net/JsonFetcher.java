package com.ydh.weile.net;

import android.text.TextUtils;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.ydh.weile.android.WeiLeMerchantApp;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.system.config.SystemVal;
import com.ydh.weile.system.config.WeiLeFakeUUID;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class JsonFetcher {

    public static final MediaType MediaTypeJson
            = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();

    public static String post1(String url, String body)  throws Exception  {
        BasicNameValuePair nvp = new BasicNameValuePair("request",body);
        List<BasicNameValuePair> lis = new ArrayList<BasicNameValuePair>();
        lis.add(nvp);
        RequestBody body1 = RequestBody.create(MediaTypeJson, OkHttpUtil.formatParams(lis));

        String phoneuuid = SharePrefs.get(WeiLeMerchantApp.wlmApp, SharePrefs.Phoneuuid,null);
        if(TextUtils.isEmpty(phoneuuid)) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(SystemVal.imei)) {
                sb.append(SystemVal.imei);
            }
            sb.append("_");
            if (!TextUtils.isEmpty(SystemVal.mac)) {
                sb.append( SystemVal.imei);
            }
            if (TextUtils.isEmpty(SystemVal.imei) && TextUtils.isEmpty(SystemVal.mac)) {

                phoneuuid = WeiLeFakeUUID.makeRandUUID();
                SharePrefs.set(WeiLeMerchantApp.wlmApp,SharePrefs.Phoneuuid,WeiLeFakeUUID.makeRandUUID());
            } else {

                phoneuuid = sb.toString();
                SharePrefs.set(WeiLeMerchantApp.wlmApp,SharePrefs.Phoneuuid,sb.toString());
            }
        }

        Request request = new Request.Builder()
                .header("clientos","101")
                .header("osversion",SystemVal.sdk + "")
                .header("clientphone",SystemVal.model + "")
                .header("weiLeversion", SystemVal.versionCode + "")
                .header("phoneuuid", phoneuuid)
                .url(url)
                .post(body1)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
