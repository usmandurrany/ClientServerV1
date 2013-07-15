package com.ud.clientserverv1;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends FragmentActivity implements IAsyncResult {

	HttpResponse response;
	StringBuilder result = new StringBuilder();
	TextView strRes;
	ListView drawerlst;
    InputStream content;
    String line;
    TextView lst_tv;
    String title;
    int itemindex = 0;
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
    allNewsFragment allnews = new allNewsFragment();
    liveViewFragment liveview = new liveViewFragment();

    getNews getnews = new getNews(this);
	getNews.getTitle gettitle = getnews.new getTitle();
	getNews.getLink getlink = getnews.new getLink();
	getNews.getDesc getdesc = getnews.new getDesc();

    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		lst_tv = (TextView) findViewById(R.id.rowTextView);
		strRes = (TextView) findViewById(R.id.textView1);
		drawerlst = (ListView) findViewById(R.id.left_drawer);
		drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
		newshead =  (ImageView) findViewById(R.id.imageView1);
		
		gettitle.delegate=this;
		getlink.delegate=this;
		getdesc.delegate=this;
		
		gettitle.execute();

		
	    final ActionBar actionBar = getActionBar();

	    // Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.addTab(
                actionBar.newTab()
                        .setText("Dunya News")
                        .setTabListener(new ActionBar.TabListener() {
							
							@Override
							public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
								getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,0).replace(R.id.content_frame,allnews).commit();
								
							}
							
							@Override
							public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
								// TODO Auto-generated method stub
								
							}
						}));
	    actionBar.addTab(actionBar.newTab().setText("Geo News").setTabListener(new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
				getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,0).replace(R.id.content_frame,liveview).commit();
				


				
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		}));

	    
	   getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, allnews).commit();

		
		//AutoUpdateApk aua = new AutoUpdateApk(getApplicationContext());
		//aua.checkUpdates(true);
		@SuppressWarnings("unused")
		GCM gcmClass = new GCM(this);
		
		addListenerOnButton();
        mediaController = new MediaController(this);

		//gtitle.execute();
		
		
		
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
	     /*   case R.id.liveView:
	 
	    		Intent liveView = new Intent(MainActivity.this, LiveViewActivity.class);
	    		startActivity(liveView);
	    		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

	    		return true;*/
	        case R.id.exit:
	        	MainActivity.this.finish();
	        

	        default:
	            return super.onOptionsItemSelected(item);
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
			  	getlink = getnews.new getLink();
				getdesc = getnews.new getDesc();
				getlink.delegate=MainActivity.this;
				getdesc.delegate=MainActivity.this;
			      getlink.execute(itemindex);
					getdesc.execute(url);
					drawer.closeDrawer(drawerlst);
			    }});
		
		
	}




	@Override
	public void resultTitle(List<String> listTitle, List<Bitmap> listImg) {
		
		drawerlst.setAdapter(new ArrayAdapter<String>(this, R.layout.custom_list_view,listTitle));
		
	}




	@Override
	public void resultLink(String url, String desc) {
		this.url = url;
		  // Toast.makeText(this, getSupportFragmentManager().findFragmentById(R.id.content_frame).toString(), Toast.LENGTH_LONG).show();
if(getSupportFragmentManager().findFragmentById(R.id.content_frame)== allnews){
		//Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        ((TextView) allnews.getView().findViewById(R.id.textView1)).setText(desc);  
		//strRes.setText(desc);
}

	}




	@Override
	public void resultDesc(String str, Bitmap image) {
		if(getSupportFragmentManager().findFragmentById(R.id.content_frame)== allnews){

	        ((ImageView) allnews.getView().findViewById(R.id.imageView1)).setImageBitmap(image);
			}
		
	}
	


	

	
	}
