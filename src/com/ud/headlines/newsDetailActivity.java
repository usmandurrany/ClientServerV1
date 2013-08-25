package com.ud.headlines;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.pagesuite.flowtext.FlowTextView;

public class newsDetailActivity extends ActionBarActivity implements View.OnTouchListener, INewsDetail {
    final static int STEP = 200;
    FlowTextView mtxtRatio1;
    //float mRatio = 1.0f;
    int mRatio = 1;
    int mBaseDist;
    int mBaseRatio;
    //float fontsize = 13;
    ImageView imageView;
    FlowTextView Desc;
    getDesc getdesc;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdetailfragment);
        imageView = (ImageView)findViewById(R.id.newsImgBig);
        Desc = (FlowTextView) findViewById(R.id.newsDesc);
        String newsVal = getIntent().getStringExtra("newsVal");
        String q = getIntent().getStringExtra("news");
        if (q == null) {
            //Log.e(TAG, "null!");


        getdesc = new getDesc(this);
        getdesc.delegate = this;
        getdesc.execute(newsVal);

        }
        else {
            // mViewPager.setCurrentItem(2);

            Toast.makeText(this,q,Toast.LENGTH_LONG).show();
        }

        mtxtRatio1 = Desc;
        mtxtRatio1.setOnTouchListener(this);
        mtxtRatio1.setTextSize(mRatio + 13);

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

    @Override
    public void newsDet(Bitmap image, String desc) {

        imageView.setImageBitmap(image);
        Desc.setText(desc);
        Desc.invalidate();
    }
    @Override
    public void onBackPressed() {
           finish();
    }
}