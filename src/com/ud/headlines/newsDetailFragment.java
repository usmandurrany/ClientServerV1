package com.ud.headlines;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.pagesuite.flowtext.FlowTextView;

public class newsDetailFragment extends Fragment implements View.OnTouchListener {
    final static int STEP = 200;
    FlowTextView mtxtRatio1;
    //float mRatio = 1.0f;
    int mRatio = 1;
    int mBaseDist;
    int mBaseRatio;
    //float fontsize = 13;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newsDetialFragment = inflater.inflate(R.layout.newsdetailfragment, container, false);

        mtxtRatio1 = (FlowTextView) newsDetialFragment.findViewById(R.id.newsDesc);
        mtxtRatio1.setOnTouchListener(this);
        mtxtRatio1.setTextSize(mRatio + 13);
        return newsDetialFragment;
    }


    public boolean onTouch(View v, MotionEvent event) {
        if (event.getPointerCount() == 2) {
            int action = event.getAction();
            int pureaction = action & MotionEvent.ACTION_MASK;
            if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
                mBaseDist = getDistance(event);
                mBaseRatio = mRatio;
            } else {
                int delta = (getDistance(event) - mBaseDist) / STEP;
                int multi = (int) Math.pow(2, delta);
                //mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
                mRatio = Math.min(1024, Math.max(1, mBaseRatio * multi));
                mtxtRatio1.setTextSize(mRatio + 13);
            }
        }
        return true;
    }

    int getDistance(MotionEvent event) {
        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));
        return (int) (Math.sqrt(dx * dx + dy * dy));
    }


}