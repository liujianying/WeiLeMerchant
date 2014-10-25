package com.ydh.weile.uitl;

import android.util.SparseArray;
import android.view.View;

public class ViewHolderUtils {
    private ViewHolderUtils() {
    }
    /**
     * 
      * @功能 从缓存中 获取 View 控件 对象，如果不存在缓存就初始化后放入缓存，以备下次使用；
      *  知识点： Android应用性能优化之使用SparseArray替代HashMap；
      * @param view 当前Item 布局 View
      * @param id 当前Item中 某个View 的id值
      * @return  当前Item中对应id的View对象
     */
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHodler = (SparseArray<View>) view.getTag();
        if (viewHodler == null) {
            viewHodler = new SparseArray<View>();
            view.setTag(viewHodler);
        }
        View childView = viewHodler.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHodler.put(id, childView);
        }
        return (T) childView;
    }
}
