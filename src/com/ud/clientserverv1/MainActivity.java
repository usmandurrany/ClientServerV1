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
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
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


public class MainActivity extends FragmentActivity implements IAsyncResult{

	ListView drawerlst;
    String title;
    int itemindex = 0;
    DrawerLayout drawer;
    String url;
    ActionBarDrawerToggle mDrawerToggle;
    dunyaNewsFragment dunya = new dunyaNewsFragment();
    geoNewsFragment geo = new geoNewsFragment();
    newsDetailFragment newsDetail = new newsDetailFragment();

    getNews getnews = new getNews(this);
	getNews.getTitle gettitle = getnews.new getTitle();
	getNews.getLink getlink = getnews.new getLink();
	getNews.getDesc getdesc = getnews.new getDesc();
	
	newsPagerAdapter mNewsPagerAdapter;
	ViewPager mViewPager;

    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		drawerlst = (ListView) findViewById(R.id.left_drawer);
		drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
		
	    final ActionBar actionBar = getActionBar();
	    ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
				mViewPager.setCurrentItem(arg0.getPosition());
			}

			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
 	     };
 		mNewsPagerAdapter = new newsPagerAdapter(getSupportFragmentManager());

 	     
 	   //  mNewsPagerAdapter.addPage(dunya);
 	    //mNewsPagerAdapter.addPage(geo);
 	   
		mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mNewsPagerAdapter);
 	   actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 	   for(int i = 0;i<mNewsPagerAdapter.getCount();i++)
 		  actionBar.addTab(
                  actionBar.newTab()
                          .setText(mNewsPagerAdapter.getPageTitle(i))
                          .setTabListener(tabListener));
       
       ViewPager.SimpleOnPageChangeListener pageChangeListener = new SimpleOnPageChangeListener(){
    	   
    	   @Override
           public void onPageSelected(int position) {
               // When swiping between different app sections, select the corresponding tab.
               // We can also use ActionBar.Tab#select() to do this if we have a reference to the
               // Tab.
			//      getSupportFragmentManager().beginTransaction().remove(newsDetail).commit();
    		   if(position == 2){
    			   if(mNewsPagerAdapter.getCount() == 3)

    			   mNewsPagerAdapter.removePage(2);
    			   
    		   }
    		   		   
    			   
    		   
              //actionBar.setSelectedNavigationItem(position);
           }   
    	   
    	   
    	   
       };
       
       mViewPager.setOnPageChangeListener(pageChangeListener);

 	     
		
		gettitle.delegate=this;
		//getlink.delegate=this;
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


		

	    // Specify that tabs should be displayed in the action bar.
	    
	    
	   //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, dunya).commit();

		
		AutoUpdateApk aua = new AutoUpdateApk(getApplicationContext());
		aua.checkUpdates(true);
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
			   //mViewPager.setCurrentItem(3);
			      //   getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0).replace(R.id.pager,newsDetail).commit();
			 
			 	//mViewPager.setCurrentItem(2);
			   drawer.closeDrawer(drawerlst);
			    }});
		
		
	}




	@Override
	public void resultTitle(List<String> listTitle, List<Bitmap> listImg) {
		
		
	}




	@Override
	public void resultLink(String url, String desc) {
		this.url = url;

        ((TextView) dunya.getView().findViewById(R.id.textView1)).setText(desc);  


	}




	@Override
	public void resultDesc(String str, Bitmap image) {

	      //  ((ImageView) dunya.getView().findViewById(R.id.imageView1)).setImageBitmap(image);
			}




	public void detFragmentValue(Bitmap image, String desc) {
		mNewsPagerAdapter.addPage(newsDetail,2);
	 	mViewPager.setCurrentItem(2);
	     ((ImageView) newsDetail.getView().findViewById(R.id.newsImgBig)).setImageBitmap(image);
	     ((TextView) newsDetail.getView().findViewById(R.id.newsDesc)).setText(desc);

	     
		
	}

	


	

	
	}
