package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by root on 21/12/2016.
 */

public class RecycleViewClicker implements RecyclerView.OnItemTouchListener {
    private GestureDetector mGestureDetector;
    click mclick;

    public interface  click{
        void onClick(View view,int position);
        void onLongClick(View view,int position);
    }


    public RecycleViewClicker(Context context,final RecyclerView rv,click listenner){

        mclick=listenner;
        mGestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=rv.findChildViewUnder(e.getX(),e.getY());
                if(child!=null&&mclick!=null){
                    mclick.onLongClick(child,rv.getChildAdapterPosition(child));
                }
                super.onLongPress(e);
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if(child!=null&&mclick!=null&&mGestureDetector.onTouchEvent(e)){
            mclick.onClick(child,rv.getChildAdapterPosition(child));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
