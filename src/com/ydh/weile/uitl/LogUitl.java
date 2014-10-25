package com.ydh.weile.uitl;


import android.util.Log;

import com.ydh.weile.merchant.BuildConfig;

public class LogUitl {

	public static boolean isLog = BuildConfig.DEBUG;

	public static void LogE(String Tag, String errorString) {

		if (isLog)
			Log.e(Tag, errorString);
	}

	public static void LogI(String Tag, String errorString) {

		if (isLog)
			Log.i(Tag, errorString);
	}
	
	public static void LogD(String Tag, String errorString) {

		if (isLog)
			Log.d(Tag, errorString);
	}
	public static void LogW(String Tag, String errorString) {
		
		if (isLog)
			Log.w(Tag, errorString);
	}
	public static void LogV(String Tag, String errorString) {

		if (isLog)
			Log.v(Tag, errorString);
	}
	
	public static void SystemOut(String string){
		if (isLog)
			System.out.println(string);
	}
	public static void SystemOut(Object object){
		if (isLog)
			System.out.println(object);
	}
	
	

}
