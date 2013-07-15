package com.ud.clientserverv1;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;




public class allNewsFragment extends Fragment implements IAsyncResult {

String url;
ListView listView;
List<newsItem> newsItems;
Bitmap icon ;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  View myFragmentView = inflater.inflate(R.layout.allnewsfragment, container, false);
	 getNews getnews = new getNews(getActivity());
		getNews.getTitle gettitle = getnews.new getTitle();
		getNews.getLink getlink = getnews.new getLink();
		getNews.getDesc getdesc = getnews.new getDesc();
		newsItems = new ArrayList<newsItem>();

		gettitle.delegate=this;

		getlink.delegate=this;
		getdesc.delegate=this;
		
		gettitle.execute();
		
		
		
		//getlink.execute(0);
		//getdesc.execute(url);
  return myFragmentView;
 }

@Override
public void resultTitle(List<String> listTitle, List<Bitmap> listImg) {
	icon = BitmapFactory.decodeResource(getActivity().getResources(),
	        R.drawable.nonews_s);
    for (int i = 0; i < listTitle.size(); i++) {
        newsItem item = new newsItem(listImg.get(i), listTitle.get(i).toString());
        newsItems.add(item);
    }
	//((ListView) this.getView().findViewById(R.id.news)).setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.news_list_view,listTitle));
   // listView = (ListView) findViewById(R.id.news);
    NewsListAdapter adapter = new NewsListAdapter(getActivity(),R.layout.news_list_view, newsItems);
    ((ListView) this.getView().findViewById(R.id.news)).setAdapter(adapter);
}

@Override
public void resultLink(String url, String desc) {
	this.url = url;
	  
  ((TextView) this.getView().findViewById(R.id.textView1)).setText(desc);  

}

@Override
public void resultDesc(String str, Bitmap image) {


        ((ImageView) this.getView().findViewById(R.id.imageView1)).setImageBitmap(image);
}
}
