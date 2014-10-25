package com.ydh.weile.uitl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @防止Toast多次点击 一直显示问题
 * Created by liujianying on 14-9-12.
 */
public class ToastUitl {

    private static int r_string;
    private static String ToastContent = "";
    private static Toast mToast = null;
    public static void showToast(Context context, String text) {
        try {
            if(!TextUtils.isEmpty(text)) {
                if (mToast == null) {
                    ToastUitl.ToastContent = text;
                    mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                } else {

                    mToast.setText(text);
                    if ((ToastContent != null) && (!ToastContent.equals(text))) {
                        ToastUitl.ToastContent = text;
                        mToast.setDuration(Toast.LENGTH_SHORT);
                    }
                }
                mToast.show();
            }
        } catch (Exception e) {

        }
    }

    public static void showToast(Context context, int text) {
        try {
                if (mToast == null) {
                    ToastUitl.r_string = text;
                    mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                } else {

                    mToast.setText(text);
                    if (text != r_string) {
                        ToastUitl.r_string = text;
                        mToast.setDuration(Toast.LENGTH_SHORT);
                }
                mToast.show();
            }
        } catch (Exception e) {

        }
    }
}
