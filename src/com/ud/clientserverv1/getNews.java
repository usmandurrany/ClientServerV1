package com.ud.clientserverv1;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
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
Uri uri;
Document doc;
Elements link;
Elements desc;
String description;
String newsRSS1= "http://dunyanews.tv/news.xml";
String url;
ImageView newshead;
String imgSrc;
Bitmap bitmap;
Elements result;
getLink glink = new getLink();
getDesc gdesc = new getDesc();


class getTitle extends AsyncTask<String, Void, String>
	{
	public IAsyncResult delegate;
	

		@Override
		protected void onPreExecute()
		{
		//spinner.VISIBLE = true;
			pDialog = new ProgressDialog(context);
		    pDialog.setMessage("Plese wait...");
		    pDialog.setIndeterminate(true);
		    pDialog.setCancelable(false);
		    pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			try {
				doc = Jsoup.parse(new URL(newsRSS1), 2000);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		protected void onPostExecute(String result)
		{
			
				Elements getTitle = doc.select("title");
			      List<String> listContents = new ArrayList<String>(getTitle.size());

			   for (int i = 2; i < getTitle.size(); i++)
			      {
			        listContents.add(getTitle.get(i).text());

			      }
	              pDialog.dismiss();
			     // glink.execute(0);
			     delegate.resultTitle(listContents);
			      
		}
	}
	
	class getLink extends AsyncTask<Integer,Void,Integer>
	{
		public IAsyncResult delegate;
		
		@Override
		protected Integer doInBackground(Integer... arg0)
		{
			try {
				doc = Jsoup.parse(new URL(newsRSS1), 2000);
								
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return arg0[0];
			
		}
		@Override
		protected void onPostExecute(Integer itemindex)
		{
			link = doc.select("item > link");
			desc = doc.select("item > description");
			url = link.get(itemindex).nextSibling().toString();
			description = desc.get(itemindex).text();
		    delegate.resultLink(url, description);
		    //Toast.makeText(context, link.get(itemindex).nextSibling().toString(),Toast.LENGTH_LONG).show();
		   // gdesc.execute();
		    this.cancel(true);
            
		}
		
		
	}

	class getDesc extends AsyncTask<String, Void, String>
	{
		public IAsyncResult delegate;


		
		@Override
		protected String doInBackground(String... params) {
			try {
				doc = Jsoup.parse(new URL(url),10000);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
			Toast.makeText(context, imgSrc, Toast.LENGTH_LONG).show();

				new AsyncTask<String,Void,String>()
				{

					@Override
					protected String doInBackground(String... params) {
						  try {
							bitmap = BitmapFactory.decodeStream((InputStream)new URL(imgSrc).getContent());
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
					
				protected void onPostExecute(String string)
				{
				delegate.resultDesc(result.text(), bitmap);

				}
					
				}.execute();
			
			Toast.makeText(context, string, Toast.LENGTH_LONG).show();
		  //if (result.text().length() < 10)
		//	strRes.setText(description);

		  //else 
			//strRes.setText(result.text());
		    
          //  pDialog.dismiss();	
		}
	}
}