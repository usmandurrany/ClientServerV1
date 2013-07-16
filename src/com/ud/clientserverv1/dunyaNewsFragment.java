package com.ud.clientserverv1;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class dunyaNewsFragment extends Fragment implements IAsyncResult, INewsDetail{

String url;
ListView listView;
List<newsItem> newsItems;
Bitmap icon ;

getNews getnews ;
getNews.getDesc getdesc;
getNews.getTitle gettitle ;
getNews.getLink getlink;

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  View myFragmentView = inflater.inflate(R.layout.dunyanewsfragment, container, false);
  listView = (ListView) myFragmentView.findViewById(R.id.news);

   getnews = new getNews(getActivity());
  getdesc = getnews.new getDesc();
  gettitle = getnews.new getTitle();
  getlink = getnews.new getLink();

		newsItems = new ArrayList<newsItem>();
		getlink.delegate=this;
		gettitle.delegate=this;

		getdesc.delegate=this;
		if(isNetworkConnected())
		gettitle.execute();
		else{
			//Toast.makeText(getActivity(), "Network error", Toast.LENGTH_LONG).show();
			Builder ab = new AlertDialog.Builder(getActivity());
			ab.setTitle("Network error");
			ab.setMessage("Cant connect to server");
			ab.setPositiveButton("Close", new OnClickListener(){

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					getActivity().finish();
					
				}
				
			});
			ab.show();
		}
		
		
		//getlink.execute(0);
		//getdesc.execute(url);
		addListenerOnButton();
  return myFragmentView;
 }

@Override
public void resultTitle(List<String> listTitle, List<Bitmap> listImg) {
	icon = BitmapFactory.decodeResource(getActivity().getResources(),
	        R.drawable.nonews_s);
	if(listTitle.size()==0 && listImg.size() ==0)
		Toast.makeText(getActivity(), "Network communication problem !", Toast.LENGTH_LONG).show();

    for (int i = 0; i < listTitle.size(); i++) {
        newsItem item = new newsItem(listImg.get(i), listTitle.get(i).toString());
        newsItems.add(item);
    }
	//((ListView) this.getView().findViewById(R.id.news)).setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.news_list_view,listTitle));
   // listView = (ListView) findViewById(R.id.news);
    NewsListAdapter adapter = new NewsListAdapter(getActivity(),R.layout.news_list_view, newsItems);
    listView.setAdapter(adapter);
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

private void addListenerOnButton() {

	listView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
		    {
		    //  title = (drawerlst.getItemAtPosition(position).toString());
		    int itemindex = position;
		    newsItem holder = (newsItem) (listView.getItemAtPosition(position));
		    Toast.makeText(getActivity(), holder.title, Toast.LENGTH_LONG).show();

		    getlink.execute(holder.title);
		    
		   //mViewPager.setCurrentItem(3);
		      //   getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0).replace(R.id.pager,newsDetail).commit();
		    
		 	
		    }});
	
	
}

@Override
public void newsDet(Bitmap image, String desc) {
((MainActivity) getActivity()).detFragmentValue(image, desc);
}
public boolean isNetworkConnected() {
    final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
}
}
