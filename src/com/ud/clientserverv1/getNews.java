package com.ud.clientserverv1;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class getNews
{

public getNews(Context context)
{
	this.context = context;
	
}

Context context;
TextView strRes;
ListView drawerlst;
String line;
TextView lst_tv;
String title;
//int itemindex = 0;
DrawerLayout drawer;
ProgressDialog pDialog;
Builder ab;
Uri uri;
Document doc;
Elements link;
Elements desc;
String description;
String newsRSS1= "http://m.dunyanews.tv/caller.php?q=hd&n=0";
String url;
ImageView newshead;
String imgSrc;
Bitmap bitmap;
Elements result;
getLink glink = new getLink();
getDesc gdesc = new getDesc();
String[] links;
String[] titles;
List<Bitmap> listImg;
List<String> listDesc;
List<String> listTitle;
class getTitle extends AsyncTask<String, Void, String>
	{
	public IAsyncResult delegate;
	

		@Override
		protected void onPreExecute()
		{
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Plese wait...");
			pDialog.setIndeterminate(true);
			pDialog.setCancelable(false);			
		    pDialog.show();
		   
		    
		    
		}

		@Override
		protected String doInBackground(String... arg0) {
			try {
				doc = Jsoup.parse(new URL(newsRSS1), 5000);
			} catch (MalformedURLException e) {
				Log.e("Malformed URL",newsRSS1);
			} catch (IOException e) {
				Log.e("IO Exception",e.toString());
				pDialog.dismiss();
				this.cancel(true);
			}
			return null;
			
		}
		
		protected void onPostExecute(String result)
		{
			
				Elements getTitle = doc.select("div.news-panel > a > h2");
				Elements getDesc  = doc.select("div.news-panel > p");
				Elements getImgLink = doc.select("div.news-panel > p > img[src]");

				listTitle = new ArrayList<String>(getTitle.size());
			    listDesc = new ArrayList<String>(getDesc.size());
			    listImg = new ArrayList<Bitmap>(getImgLink.size());
			      
			      
			   //for (int i = 2; i < getTitle.size(); i++)
			      for (Element e : getTitle)
			          listTitle.add(e.text());
			      
			      for (Element e : getDesc)
				      listDesc.add(e.text());
			      
			  	links = new String[getImgLink.size()];
				for(int i=0; i< getTitle.size();i++){
					links[i] = getImgLink.get(i).attr("src");
					//Toast.makeText(context, getImgLink.get(i).attr("src").toString(), Toast.LENGTH_LONG).show();
				}


			      new AsyncTask<String,Void,String>()
			      {

					@Override
					protected String doInBackground(String... params) {
					for(String s : params)
					{
						try {
							bitmap = BitmapFactory.decodeStream((InputStream)new URL(s).getContent());
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}

						listImg.add(bitmap);
						
					}
						return null;
					}
					@Override
					public void onPostExecute(String args)
					{
					//	Toast.makeText(context, listImg.size(), Toast.LENGTH_LONG).show();
						delegate.resultTitle(listTitle, listImg);
					     pDialog.dismiss();
					}
			    	  
			      }.execute(links);
			      
			     // glink.execute(0);
			     
			      
			      
		}
	}
	
class getLink extends AsyncTask<String,Void,String>
	{
		public INewsDetail delegate;


		@Override
		protected String doInBackground(String... arg0)
		{
			try {
				String rawurl = "http://m.dunyanews.tv/caller.php?q=hd&n="+arg0[0];
			    String url = rawurl.replaceAll(" ", "%20");
				doc = Jsoup.parse(new URL(url), 4000);
				Log.e("URL",url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return arg0[0];
			
		}
		@Override
		protected void onPostExecute(String string)
		{
			Elements imgLink = doc.select("div.news-panel > p > img[src]");
			imgSrc = imgLink.attr("src");
			desc = doc.select("div.news-panel > p");
			 new AsyncTask<String,Void,String>()
			 {

				@Override
				protected String doInBackground(String... arg0) {
					try {
						bitmap = BitmapFactory.decodeStream((InputStream)new URL(imgSrc).getContent());
						Log.e("URL",imgSrc);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
				@Override
				protected void onPostExecute(String string)
				{
				    delegate.newsDet(bitmap, desc.text());

				}
				 
			 }.execute(imgSrc);
			
		    //Toast.makeText(context, link.get(itemindex).nextSibling().toString(),Toast.LENGTH_LONG).show();
		   // gdesc.execute();
//		    pDialog.dismiss();
		}
		
		
	}

class getDesc extends AsyncTask<String, Void, String>
	{
		public IAsyncResult delegate;
		
		@Override
		protected String doInBackground(String... params) {
			try {
				doc = Jsoup.parse(new URL(url),5000);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return params[0];
		}
		@Override
		protected void onPostExecute(String string)
		{
			result = doc.select("div#main-heading_detail > div.txt2_um");
			Elements image = doc.select("div#main-heading_detail > img[src]");
			imgSrc = image.attr("src");
		//	Toast.makeText(context, imgSrc, Toast.LENGTH_LONG).show();//Image link that is retrieved from the sent url

				new AsyncTask<String,Void,String>()
				{

					@Override
					protected String doInBackground(String... params) {
						  try {
							bitmap = BitmapFactory.decodeStream((InputStream)new URL(imgSrc).getContent());
						} catch (MalformedURLException e) {
							pDialog.dismiss();	
							e.printStackTrace();
						} catch (IOException e) {
							pDialog.dismiss();	
							e.printStackTrace();
						}
						return null;
					}
					
				protected void onPostExecute(String string)
				{
				delegate.resultDesc(result.text(), bitmap);

				}
					
				}.execute();
				//pDialog.dismiss();	

			//Toast.makeText(context, string, Toast.LENGTH_LONG).show(); //Url being sent to this task

		}
	}
}
