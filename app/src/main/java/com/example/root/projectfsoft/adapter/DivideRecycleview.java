package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.root.projectfsoft.R;

/**
 * Created by root on 22/12/2016.
 */

public class DivideRecycleview extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Drawable mDrawable;
    public DivideRecycleview(Context context){
        mContext=context;
        mDrawable=mContext.getResources().getDrawable(R.drawable.divider_recycleview);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        super.onDraw(c, parent);
        int left=10;
        int right=parent.getWidth()-10;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + 2;
            int bottom = top + 2;

            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }
}
