package com.ud.headlines;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class newsListAdapter extends BaseAdapter {

    private Activity activity;
    protected String[] titles;
    protected String[] imgUrls;
    protected static LayoutInflater inflater = null;
    ImageLoaderConfiguration config;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    FadeInBitmapDisplayer fadeIn;

    public newsListAdapter(Activity a, String[] titles, String[] imgUrls, ImageLoader imageLoader) {
        activity = a;
        this.titles = titles;
        this.imgUrls = imgUrls;
        this.imageLoader = imageLoader;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public int getCount() {
        return titles.length;
    }

    public String getItem(int position) {
        return titles[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.news_list_view, null);

        TextView text = (TextView) vi.findViewById(R.id.textView1);
        ImageView image = (ImageView) vi.findViewById(R.id.imageView1);
        text.setText(titles[position]);

        imageLoader.displayImage(imgUrls[position], image);
        return vi;
    }
}