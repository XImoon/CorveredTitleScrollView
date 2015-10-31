package com.ximoon.corveredtitlescrollview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private MyScrollView myScrollView = null;
    private ViewPager mViewPager = null;
    /** 屏幕宽度*/
    public static int DISPLAYW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 测量屏幕的宽度
        DISPLAYW = getResources().getDisplayMetrics().widthPixels;

        setContentView(R.layout.activity_main);

        myScrollView = (MyScrollView) findViewById(R.id.msv);
        mViewPager = (ViewPager) findViewById(R.id.vp);

        // 设置viewpager的宽高一致，为屏幕的宽度(因为嵌套在scrollview中，所以必须重新设置高度)
        mViewPager.setLayoutParams(new RelativeLayout.LayoutParams(DISPLAYW,DISPLAYW));

        myScrollView.setListener(new OnScrolledListener() {
            @Override
            public void scroll(int y) {
                // 若滑动坐标的绝对值即滑动距离不超过viewpager的高度的时候，设置viewpager回滚位置（相当于viewpager滑动的距离是ScrollView滑动距离的相反值，以保持viewpager固定）
                // if ((y < 0 && -y <= DISPLAYW) || (y > 0 && y <= DISPLAYW)) {
                // }
                // 因为viewpager是可横向滚动的控件，所以需要按屏计算它的目前滚动宽度，滚动纵坐标即ScrollView的滚动纵坐标的相反值
                mViewPager.scrollTo(mViewPager.getCurrentItem() * DISPLAYW, -y);   // 此处存在一个问题  滑屏到中间位置的时候再滑动viewpager，会导致图片位置出错,可重写viewpager纠错
                
            }
        });
        mViewPager.setAdapter(new ImageAdapter());

    }


    class ImageAdapter extends PagerAdapter{

        private int[] imgs = {R.mipmap.ic_launcher,R.mipmap.default_ptr_rotate};

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object instantiateItem(ViewGroup container,final int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setMinimumHeight(DISPLAYW);
            imageView.setMinimumWidth(DISPLAYW);
            imageView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgs[position % 2]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }

}
