package com.umeng.soexample.bweismall.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by android_lhf：2019/1/10
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollviewListener !=null){
            mScrollviewListener.OnScrollChange(this,l,t,oldl,oldt);
        }
    }

    /**
     * 接口回调
     */
    public interface ScrollviewListener{
        void OnScrollChange(MyScrollView scrollView,int l, int t, int oldl, int oldt);
    }

    public ScrollviewListener mScrollviewListener;

    public void setScrollviewListener(ScrollviewListener scrollviewListener){
        mScrollviewListener = scrollviewListener;
    }

}
