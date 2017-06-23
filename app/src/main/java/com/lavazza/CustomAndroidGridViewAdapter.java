package com.lavazza;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class CustomAndroidGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] string;
    private final int[] Imageid;

    public CustomAndroidGridViewAdapter(Context c,String[] string,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.string = string;
    }

    @Override
    public int getCount() {
        return string.length;
    }

    @Override
    public Object getItem(int p) {
        return null;
    }

    @Override
    public long getItemId(int p) {
        return 0;
    }

    @Override
    public View getView(int p, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.custom_grid, null);
            TextView textView = (TextView) grid.findViewById(R.id.gridview_text);
//            ImageView imageView = (ImageView)grid.findViewById(R.id.gridview_image);
            textView.setText(string[p]);
//            imageView.setImageResource(Imageid[p]);
//            Picasso.with(mContext).load(Imageid[p]).resize(150, 150).into(imageView);
//            imageView.setVisibility(View.GONE);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}