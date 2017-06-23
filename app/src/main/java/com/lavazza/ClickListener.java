package com.lavazza;

import android.view.View;

public interface ClickListener {
//    public void itemClicked(View view , int position);

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
