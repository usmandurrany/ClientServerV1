package com.ud.clientserverv1;

import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;




public class allNewsFragment extends Fragment implements IAsyncResult {

String url;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  View myFragmentView = inflater.inflate(R.layout.allnewsfragment, container, false);
	 getNews getnews = new getNews(getActivity());
		getNews.getTitle gettitle = getnews.new getTitle();
		getNews.getLink getlink = getnews.new getLink();
		getNews.getDesc getdesc = getnews.new getDesc();

		gettitle.delegate=this;

		getlink.delegate=this;
		getdesc.delegate=this;
		gettitle.execute();

		getlink.execute(0);
		getdesc.execute(url);
  return myFragmentView;
 }

@Override
public void resultTitle(List<String> listContents) {
	// TODO Auto-generated method stub
	
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
