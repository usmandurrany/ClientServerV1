package com.ud.clientserverv1;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class geoNewsListAdapter extends newsListAdapter
{

	public geoNewsListAdapter(Activity a, String[] titles, String[] imgUrls,
			ImageLoader imageLoader) {
		super(a, titles, imgUrls, imageLoader);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.news_list_view, null);

        TextView text=(TextView)vi.findViewById(R.id.textView1);;
        ImageView image=(ImageView)vi.findViewById(R.id.imageView1);
        text.setText(titles[position]);
        
        //imageLoader.displayImage(imgUrls[position], image);
        return vi;
    }
	
	@Override
	public String getItem(int position) {
        return imgUrls[position];
    }
	
	
}