package com.ydh.weile.net.mode.uitl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.activity.CloseActivity;
import com.ydh.weile.entity.LoginInfo;
import com.ydh.weile.entity.UpdateVersionEntity;
import com.ydh.weile.entity.YDHData;
import com.ydh.weile.interfaces.NetCode;
import com.ydh.weile.net.DataFetcherRunnable;
import com.ydh.weile.net.FetchDataHelper;
import com.ydh.weile.net.HttpRequestBody;
import com.ydh.weile.net.HttpRequestUrl;
import com.ydh.weile.system.config.SharePrefs;
import com.ydh.weile.uitl.LogUitl;
import com.ydh.weile.uitl.ToastUitl;

import org.json.JSONException;

/**
 * Created by liujianying on 14/10/20.
 * 登录退出网络请求模块 版本更新mode
 */
public class LoginMode {


    public static LoginMode loginMode = null;

    public static LoginMode newLoginMode() {

        if(loginMode == null) {
            synchronized(LoginMode.class){
                 if(loginMode == null)loginMode =new LoginMode();
                }
         }
        return loginMode;
    }

    private LoginMode() {

    }

    /**
     * @category 用户登录
     * @param
     * @param
     * @param password
     */
    public void startLogin(final Context mContext,final String userName, final String password,final boolean isAutomaticLogin, final Handler handler){

        final Message msg = handler.obtainMessage();
        try {
            FetchDataHelper.post(HttpRequestUrl.newUrl().getManagerLogin(userName, password), HttpRequestBody.newBody().requestManagerLoginBody(userName, password), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() == 0) {
                        msg.what = NetCode.RequestSuccess;
                        LoginInfo logiInfo = JSON.parseObject(ydhData.getData(), LoginInfo.class);
                        logiInfo.setAutomaticLogin(isAutomaticLogin);
                        logiInfo.setPassword(password);
                        logiInfo.setUserName(userName);
                        SharePrefs.set(mContext, SharePrefs.LOGIN_INFO, JSON.toJSONString(logiInfo));
                        msg.obj = logiInfo;
                    }else {
                        msg.what = NetCode.System_Error;
                        ToastUitl.showToast(mContext, ydhData.getMsg());
                    }
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


//    requestManagerLogout

    /**
     * 退出app接口
     * @param mContext
     * @param handler
     */
    public void  requestManagerLogout(final Context mContext, final Handler handler) {

        final Message msg = new Message();

        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getRequestManagerLogout(), HttpRequestBody.newBody().requestManagerLogout(), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    LogUitl.SystemOut(JSON.toJSONString(ydhData));
                    if(ydhData.getResultCode() ==  0) {
                        msg.what = NetCode.RequestSuccess;

                        if(handler != null) {

                            handler.sendMessage(msg);
                        } else {

                            CloseActivity.closeActivity(mContext, CloseActivity.CLOSE_APPTION);
                        }
                    }else {

                        msg.what = NetCode.System_Error;

                        if(handler != null) {

                            handler.sendMessage(msg);
                        }else {
                            ToastUitl.showToast(mContext, ydhData.getMsg());
                        }
                    }
                }

                @Override
                public void errorMessage(int resultCode, Object object) {
                    msg.obj = resultCode;
                    msg.what = NetCode.RequestFailed;
                    if(handler != null)
                    {
                        handler.sendMessage(msg);

                    } else {

//                        NetExceptionUitl.newNetExceptionUitl().showExceptionToast(mContext,(Integer)msg.obj);
                        //退出登录时候 直接退出应用
                        CloseActivity.closeActivity(mContext, CloseActivity.CLOSE_APPTION);
                    }
                }
            });
        } catch (JSONException e) {
            msg.what = NetCode.RequestFailed;
            msg.obj = NetCode.RequestFailed;
            if(handler != null) {
                handler.sendMessage(msg);
            }
            e.printStackTrace();
        }
    }


    /**
     * 获取更新
     * @param mContext
     * @param handler
     */
    public void appVersionUpdate(final Context mContext, final Handler handler, final int interfaceFlag) {
        final Message msg = handler.obtainMessage();
        msg.arg1 = interfaceFlag;
        try {

            FetchDataHelper.post(HttpRequestUrl.newUrl().getAppVersionUpdate(), HttpRequestBody.newBody().requestAppVersionUpdate(), new DataFetcherRunnable.IFetchCallback() {
                @Override
                public void fetchSuccess(Object object) {
                    YDHData ydhData = (YDHData) object;
                    if(ydhData.getResultCode() ==  0) {
                        msg.what = NetCode.RequestSuccess;
                        msg.obj = JSON.parseObject(ydhData.getData(), UpdateVersionEntity.class);
                    }else {
                        msg.what = NetCode.System_Error;
                        ToastUitl.showToast(mContext, ydhData.getMsg());
                    }
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
            msg.obj = NetCode.RequestFailed;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }


}
