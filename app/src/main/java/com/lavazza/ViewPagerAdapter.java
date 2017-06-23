package com.lavazza;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Krackerz4 on 20-Feb-17.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mResources;

    public ViewPagerAdapter(Context mContext, int[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);
        final int m=position;
        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        imageView.setImageResource(mResources[position]);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch(m){
//
//                }
//            }
//        });
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}