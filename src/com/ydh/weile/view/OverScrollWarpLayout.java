package com.ydh.weile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class OverScrollWarpLayout extends LinearLayout {

    /**
     * OvershootInterpolator锟侥碉拷锟斤拷系锟斤拷
     */
    private static final float OVERSHOOT_TENSION = 0.75f;

    /**
     * 平锟斤拷锟斤拷锟斤拷锟斤拷
     */
    private Scroller mScroller;

    public OverScrollWarpLayout(Context context, AttributeSet attr) {
        super(context, attr);
        this.setOrientation(LinearLayout.VERTICAL);
        // 锟斤拷始锟斤拷平锟斤拷锟斤拷锟斤拷锟斤拷
        mScroller = new Scroller(getContext(), new OvershootInterpolator(OVERSHOOT_TENSION));
    }

    public OverScrollWarpLayout(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        // 锟斤拷始锟斤拷平锟斤拷锟斤拷锟斤拷锟斤拷
        mScroller = new Scroller(getContext(), new OvershootInterpolator(OVERSHOOT_TENSION));
    }

    // 锟斤拷锟矫此凤拷锟斤拷锟斤拷锟斤拷锟斤拷目锟斤拷位锟斤拷
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    // 锟斤拷锟矫此凤拷锟斤拷锟斤拷锟矫癸拷锟斤拷锟斤拷锟斤拷锟狡�锟斤拷
    public void smoothScrollBy(int dx, int dy) {

        // 锟斤拷锟斤拷mScroller锟侥癸拷锟斤拷偏锟斤拷锟斤拷
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷invalidate()锟斤拷锟杰憋拷证computeScroll()锟结被锟斤拷锟矫ｏ拷锟斤拷锟斤拷一锟斤拷锟斤拷刷锟铰斤拷锟芥，锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷效锟斤拷
        invalidate();
    }

    @Override
    public void computeScroll() {

        // 锟斤拷锟叫讹拷mScroller锟斤拷锟斤拷锟角凤拷锟斤拷锟�
        if (mScroller.computeScrollOffset()) {

            // 锟斤拷锟斤拷锟斤拷锟�View锟斤拷scrollTo()锟斤拷锟绞碉拷实墓锟斤拷锟�
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            // 锟斤拷锟斤拷锟斤拷酶梅锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷芸锟斤拷锟斤拷锟斤拷锟叫э拷锟�
            postInvalidate();
        }
        super.computeScroll();
    }

    public final void smoothScrollToNormal() {
        smoothScrollTo(0, 0);
    }

    public final int getScrollerCurrY() {
        return mScroller.getCurrY();
    }
}