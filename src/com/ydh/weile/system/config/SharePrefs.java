package com.ydh.weile.system.config;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.ydh.weile.android.WeiLeMerchantApp;
import com.ydh.weile.entity.LoginInfo;
import com.ydh.weile.entity.UserInfo;


/**
 * SharedPreferences 统一处理类
 *
 * @author yx
 * 
 */
public class SharePrefs {


    public static SharePrefs sharePrefs = null;

    public static SharePrefs newSharePrefs() {

        if(sharePrefs == null) {
            synchronized(SharePrefs.class){
                if(sharePrefs == null)sharePrefs =new SharePrefs();
            }
        }
        return sharePrefs;
    }

    private SharePrefs() {

    }

    public static final String            Weile_SP = "WeileMerchant_SP";       // SharedPreferences 名称
    public static final String                LOGIN_INFO = "Login_Info";       // 保存用户登录信息
    public static final String                  USER_INFO = "User_Info";       // 保存用户登录信息
	public static final String    LastTimeUserName = "LastTimeUserName";       // 上次登录的用户名    SharedPreferences_key
	public static final String      LastTimeUserPSW = "LastTimeUserPSW";       // 上次登录的用户密码    SharedPreferences_key
	public static final String                      isSes = "sessionID";
    public static final String                 Phoneuuid  = "Phoneuuid";       //用户手机的唯一识别码


	public static void set(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(
				Weile_SP, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void set(Context context, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(
				Weile_SP, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	

	public static String get(Context context, String key, String defValue) {
		SharedPreferences sp = context.getSharedPreferences(
				Weile_SP, Activity.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}
	

	public static boolean get(Context context, String key, boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(
				Weile_SP, Activity.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	
	
	public static void set(Context context, String key, long value) {
		SharedPreferences sp = context.getSharedPreferences(
				Weile_SP, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
	public static long get(Context context, String key, long defValue) {
		SharedPreferences sp = context.getSharedPreferences(
				Weile_SP, Activity.MODE_PRIVATE);
		return sp.getLong(key, defValue);
	}


    public static void delete(Context context) {
        SharedPreferences sp = context.getSharedPreferences(
                Weile_SP, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
    }


    public LoginInfo getLoginInfo() {
        return JSON.parseObject(SharePrefs.get(WeiLeMerchantApp.wlmApp, SharePrefs.LOGIN_INFO, "{}"), LoginInfo.class);
    }


    public UserInfo getUserInfo() {
        return JSON.parseObject(SharePrefs.get(WeiLeMerchantApp.wlmApp, SharePrefs.USER_INFO, "{}"), UserInfo.class);
    }

}
