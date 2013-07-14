package com.ud.clientserverv1;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends Activity {

	HttpResponse response;
	StringBuilder result = new StringBuilder();
	TextView strRes;
	ListView drawerlst;
    InputStream content;
    String line;
    TextView lst_tv;
    String title;
    int itemindex;
    DrawerLayout drawer;
    ActionBarDrawerToggle mDrawerToggle;
    ProgressDialog pDialog;
    VideoView mVideoView;
    Uri uri;
    MediaController mediaController;
	Document doc;
	Document cleanDoc;
	Elements link;
	Elements desc;
	String description;
    String newsRSS1= "http://dunyanews.tv/news.xml";
    String url;
    ImageView newshead;
    String imgSrc;
    Bitmap bitmap;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lst_tv = (TextView) findViewById(R.id.rowTextView);
		strRes = (TextView) findViewById(R.id.textView1);
		drawerlst = (ListView) findViewById(R.id.left_drawer);
		drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
		newshead =  (ImageView) findViewById(R.id.imageView1);

		@SuppressWarnings("unused")
		GCM gcmClass = new GCM(this);
		
		addListenerOnButton();
		getTitle gtitle = new getTitle();
        mediaController = new MediaController(this);

		gtitle.execute();
		
		
		
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("News");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawer.setDrawerListener(mDrawerToggle);
    }
	
		
	

	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.drawer: 				// id of drawer open-close icon
	            if(drawer.isDrawerOpen(drawerlst) == false)
	        	drawer.openDrawer(drawerlst);
	            else 
	            	drawer.closeDrawer(drawerlst);
	            return true;
	        case R.id.liveView:
	 
	    		Intent liveView = new Intent(MainActivity.this, LiveViewActivity.class);
	    		startActivity(liveView);
	    		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

	    		return true;

	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	class getTitle extends AsyncTask<String, Void, String>
	{
		
		@Override
		protected void onPreExecute()
		{
		//spinner.VISIBLE = true;
			pDialog = new ProgressDialog(MainActivity.this);
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
			return "none";
			
		}
		
		protected void onPostExecute(String result)
		{
			
			   Elements getTitle = doc.select("title");
			      List<String> listContents = new ArrayList<String>(getTitle.size());

			   for (int i = 2; i < getTitle.size(); i++)
			      {
			        listContents.add(getTitle.get(i).text());

			      }
			      drawerlst.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.custom_list_view, listContents));

	              pDialog.dismiss();

		}
	}
	
	class getLink extends getTitle
	{
		@Override
		protected String doInBackground(String... arg0)
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
			return "none";
			
		}
		@Override
		protected void onPostExecute(String document)
		{
			link = doc.select("item > link");
			desc = doc.select("item > description");
			url = link.get(itemindex).nextSibling().toString();
			description = desc.get(itemindex).text();
		    Toast.makeText(MainActivity.this, link.get(itemindex).nextSibling().toString(),Toast.LENGTH_LONG).show();
		    getDesc gdesc = new getDesc();
		    gdesc.execute();
		    this.cancel(true);
            
		}
		
		
	}

	class getDesc extends AsyncTask<String, Void, String>
	{

		
		@Override
		protected String doInBackground(String... params) {
			try {
				doc = Jsoup.parse(new URL(url),5000);
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
			Elements result = doc.select("div#main-heading_detail > div.txt2_um");
			Elements image = doc.select("div#main-heading_detail > img");
			imgSrc = image.attr("src");
			
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
					newshead.setImageBitmap(bitmap);
				    Animation fadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in);		
				    newshead.startAnimation(fadeInAnimation);
				}
					
				}.execute();
			
				
			Toast.makeText(MainActivity.this, image.attr("src"), Toast.LENGTH_LONG).show();
		  if (result.text().length() < 10)
			strRes.setText(description);

		  else 
			strRes.setText(result.text());
		    
            pDialog.dismiss();	
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {   //menu items
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);// actionbar menu item drawer open close

		return true;
	}
	
	private void addListenerOnButton() {
	
		drawerlst.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
			      title = (drawerlst.getItemAtPosition(position).toString());
			      itemindex = position;
					//Toast.makeText(getBaseContext(), , Toast.LENGTH_LONG).show();
					getLink gdesc = new getLink();

					gdesc.execute();
					drawer.closeDrawer(drawerlst);
			    }});
		
		
	}
	


	

	
	}
