package com.ydh.weile.net;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.ydh.weile.android.WeiLeMerchantApp;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.system.config.SystemVal;
import com.ydh.weile.system.config.WeiLeFakeUUID;
import com.ydh.weile.uitl.LogUitl;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by liujianying on 14-10-10.
 */
public class OkHttpUtil {

    private static final MediaType MediaTypeJson
            = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    static{
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);    // socket timeout
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);    // socket timeout
        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
    }
    /**
     * 该不会开启异步线程。
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException{
        return mOkHttpClient.newCall(request).execute();
    }
    /**
     * 开启异步线程访问网络
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback){
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }
    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     * @param request
     */
    public static void enqueue(Request request){
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response arg0) throws IOException {

            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }
        });
    }
    public static String getStringFromServer(String url) throws IOException{
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    private static final String CHARSET_NAME = "UTF-8";
    /**
     * 这里使用了HttpClinet的API。只是为了方便
     * @param params
     * @return
     */
    public static String formatParams(List<BasicNameValuePair> params){
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }
    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params){
        return url + "?" + formatParams(params);
    }
    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value){
        return url + "?" + name + "=" + value;
    }


    public static String post(String url, String dataBody)  throws Exception  {
        BasicNameValuePair nvp = new BasicNameValuePair("request", dataBody);
        LogUitl.SystemOut("dataBody = " + JSON.toJSONString(nvp));
        List<BasicNameValuePair> lis = new ArrayList<BasicNameValuePair>();
        lis.add(nvp);
        RequestBody body = RequestBody.create(MediaTypeJson, formatParams(lis));


        LogUitl.SystemOut("body = " + JSON.toJSONString(body));
        String phoneuuid = SharePrefs.get(WeiLeMerchantApp.wlmApp, SharePrefs.Phoneuuid, null);
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
                .post(body)
                .build();
        Response response = mOkHttpClient.newCall(request).execute();

        if (!response.isSuccessful())
            throw
                    new IOException("Unexpected code " + response);
        return response.body().string();
    }
}
