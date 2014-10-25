package com.ydh.weile.net.mode.uitl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.entity.NowDayConsumeInfoEntity;
import com.ydh.weile.entity.UserInfo;
import com.ydh.weile.entity.YDHData;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.net.DataFetcherRunnable;
import com.ydh.weile.net.FetchDataHelper;
import com.ydh.weile.net.HttpRequestBody;
import com.ydh.weile.net.HttpRequestUrl;
import com.ydh.weile.uitl.ToastUitl;

import org.json.JSONException;

/**
 * Created by liujianying on 14/10/21.
 * 今日收款金额,今日卡券消费数网络模块
 */
public class HomePageMode {



    public static HomePageMode homePageMode = null;

    public static HomePageMode newHomePageMode() {

        if(homePageMode == null) {
            synchronized(LoginMode.class){
                if(homePageMode == null)homePageMode =new HomePageMode();
            }
        }
        return homePageMode;
    }

    private HomePageMode() {

    }


    /**
     * @获取当前用户信息
     *
     */
    public void appGetUserInfo(final Context context, final Handler handler, final int interfaceFlag) {

        final Message msg = handler.obtainMessage();
        msg.arg1 = interfaceFlag;
        try {
            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppGetUserInfo(), HttpRequestBody.newBody().requestAppGetUserInfo(), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    UserInfo userInfo = JSON.parseObject(ydhData.getData(), UserInfo.class);
                    if(ydhData.getResultCode() == 0) {
                        msg.what = NetCode.RequestSuccess;
                    }else {
                        msg.what = NetCode.System_Error;
                        ToastUitl.showToast(context, ydhData.getMsg());
                    }
                    msg.obj = userInfo;

                    handler.sendMessage(msg);
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    msg.obj = resultCode;
                    msg.what = NetCode.RequestFailed;
                    handler.sendMessage(msg);
                }
            });

        } catch (JSONException e) {
            msg.what = NetCode.RequestFailed;
            msg.obj = NetCode.OtrerException;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }

    /**
     * @今日收款金额今日卡券消费数
     *
     */
    public void appNowDayConsumeInfo(final Context context, final Handler handler, final int interfaceFlag) {

        final Message msg = handler.obtainMessage();
        msg.arg1 = interfaceFlag;
        try {
            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppNowDayConsumeInfo(), HttpRequestBody.newBody().requestappNowDayConsumeInfo(), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    NowDayConsumeInfoEntity nowDayConsumeInfoEntity = JSON.parseObject(ydhData.getData(), NowDayConsumeInfoEntity.class);
                    if(ydhData.getResultCode() == 0) {
                        msg.what = NetCode.RequestSuccess;
                    }else {
                        msg.what = NetCode.System_Error;
                        ToastUitl.showToast(context, ydhData.getMsg());
                    }
                    msg.obj = nowDayConsumeInfoEntity;
                    handler.sendMessage(msg);
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    msg.obj = resultCode;
                    msg.what = NetCode.RequestFailed;
                    handler.sendMessage(msg);
                }
            });

        } catch (JSONException e) {
            msg.what = NetCode.RequestFailed;
            msg.obj = NetCode.OtrerException;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }








}
