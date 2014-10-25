package com.ydh.weile.uitl;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * ScreenUtils
 *
 */
public class ScreenUtils {

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPxInt(Context context, float dp) {
        return (int)(dpToPx(context, dp) + 0.5f);
    }

    public static float pxToDpCeilInt(Context context, float px) {
        return (int)(pxToDp(context, px) + 0.5f);
    }

    /**
     * 判断是否横屏
     *
     * @param context
     * @return
     */
    public static boolean isOrientationLandscape(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        }
        return false;
    }

    /**
     * 返回屏幕尺寸
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 返回屏幕尺寸
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return metrics.heightPixels;
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        Rect frame = new Rect();
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    public static void switchSceenEffect(Context ctx,int sceenType){
        if( null != ctx && ctx instanceof Activity){
            Window mWindow = ((Activity)ctx).getWindow();
            WindowManager.LayoutParams lp = mWindow.getAttributes();
            if( sceenType ==0)
                lp.alpha = 0.5f;
            else{
                lp.alpha = 1f;
            }
            mWindow.setAttributes(lp);
        }
    }


    /**
     * @param view 控件
     * @param y  高度
     */
    public static void setLayoutY(View view,int y ,int verb){
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(margin.leftMargin,y, margin.rightMargin,45);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        if( verb != -1)
            layoutParams.addRule(verb);
        view.setLayoutParams(layoutParams);
    }


    public static void setLayoutY2(View view,int y ,int verb){
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(margin.leftMargin,y, margin.rightMargin,margin.bottomMargin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        if( verb != -1)
            layoutParams.addRule(verb);
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutX(View view,int x){
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,margin.topMargin, x+margin.width, margin.bottomMargin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    public static void setLayout(View view,int x,int y)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,y, x+margin.width, y+margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    public static int getWidth(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredWidth());
    }
    /*
    * 获取控件高
    */
    public static int getHeight(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredHeight());
    }

}
