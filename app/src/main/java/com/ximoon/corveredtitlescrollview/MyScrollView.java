package com.ximoon.corveredtitlescrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by XImoon on 15/10/28.
 */
public class MyScrollView extends ScrollView {

    private OnScrolledListener listener = new OnScrolledListener() {
        @Override
        public void scroll(int y) {

        }
    };

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        listener.scroll(getScrollY());
        super.onScrollChanged(l, t, oldl, oldt);
    }

    //
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    public void setListener(OnScrolledListener listener){
        this.listener = listener;
    }
}
