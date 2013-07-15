package com.ud.clientserverv1;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
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
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements IAsyncResult {

	ListView drawerlst;
    String title;
    int itemindex = 0;
    DrawerLayout drawer;
    String url;
    ActionBarDrawerToggle mDrawerToggle;
    dunyaNewsFragment dunya = new dunyaNewsFragment();
    geoNewsFragment geo = new geoNewsFragment();

    getNews getnews = new getNews(this);
	getNews.getTitle gettitle = getnews.new getTitle();
	getNews.getLink getlink = getnews.new getLink();
	getNews.getDesc getdesc = getnews.new getDesc();

    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		drawerlst = (ListView) findViewById(R.id.left_drawer);
		drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
		
		gettitle.delegate=this;
		getlink.delegate=this;
		getdesc.delegate=this;
		
//		gettitle.execute();
		List<String> listCategories = new ArrayList<String>(7);
		listCategories.add("Headlines");
		listCategories.add("Pakistan");
		listCategories.add("World");
		listCategories.add("Sports");
		listCategories.add("Entertainment");
		listCategories.add("Business");
		drawerlst.setAdapter(new ArrayAdapter<String>(this, R.layout.custom_list_view,listCategories));


		
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
								getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left,0).replace(R.id.content_frame,dunya).commit();
								
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
				getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,0).replace(R.id.content_frame,geo).commit();
				


				
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		}));

	    
	   getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, dunya).commit();

		
		//AutoUpdateApk aua = new AutoUpdateApk(getApplicationContext());
		//aua.checkUpdates(true);
		@SuppressWarnings("unused")
		GCM gcmClass = new GCM(this);
		
		addListenerOnButton();

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
			/*		//Toast.makeText(getBaseContext(), , Toast.LENGTH_LONG).show();
			  	getlink = getnews.new getLink();
				getdesc = getnews.new getDesc();
				getlink.delegate=MainActivity.this;
				getdesc.delegate=MainActivity.this;
			      getlink.execute(itemindex);
					getdesc.execute(url);*/
					drawer.closeDrawer(drawerlst);
			    }});
		
		
	}




	@Override
	public void resultTitle(List<String> listTitle, List<Bitmap> listImg) {
		
		
	}




	@Override
	public void resultLink(String url, String desc) {
		this.url = url;
		  // Toast.makeText(this, getSupportFragmentManager().findFragmentById(R.id.content_frame).toString(), Toast.LENGTH_LONG).show();
if(getSupportFragmentManager().findFragmentById(R.id.content_frame)== dunya){
		//Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        ((TextView) dunya.getView().findViewById(R.id.textView1)).setText(desc);  
		//strRes.setText(desc);
}

	}




	@Override
	public void resultDesc(String str, Bitmap image) {
		if(getSupportFragmentManager().findFragmentById(R.id.content_frame)== dunya){

	        ((ImageView) dunya.getView().findViewById(R.id.imageView1)).setImageBitmap(image);
			}
		
	}
	


	

	
	}
