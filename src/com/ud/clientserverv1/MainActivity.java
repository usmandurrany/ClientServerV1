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

import com.pagesuite.flowtext.FlowTextView;


public class MainActivity extends FragmentActivity implements IAsyncResult{

	ListView drawerlst;
    String title;
    DrawerLayout drawer;
    
    ActionBarDrawerToggle mDrawerToggle;
    
    dunyaNewsFragment dunya = new dunyaNewsFragment();
    //geoNewsFragment geo = new geoNewsFragment(); //Unimplemented
    
    newsDetailFragment newsDetail = new newsDetailFragment();

    getNews getnews = new getNews(this);

	
	newsPagerAdapter mNewsPagerAdapter;
	ViewPager mViewPager;
	

    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//AutoUpdateApk aua = new AutoUpdateApk(getApplicationContext());
		//aua.checkUpdates(true);
		
		setContentView(R.layout.activity_main);
		drawerlst = (ListView) findViewById(R.id.left_drawer);
		drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
		
 		mNewsPagerAdapter = new newsPagerAdapter(getSupportFragmentManager());
 		mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mNewsPagerAdapter);
 		
	    final ActionBar actionBar = getActionBar();
	    ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				//mViewPager.setAdapter(null);
			//	mViewPager.setAdapter(mNewsPagerAdapter);
			}

			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
				mViewPager.setCurrentItem(arg0.getPosition());
				//arg0.setText("Refresh News");
			}

			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

				//arg0.setText(mNewsPagerAdapter.getPageTitle(arg0.getPosition()));
			}
 	     };

		
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
    		   if(position == 1){
    			   if(mNewsPagerAdapter.getCount() == 2)
    			   mNewsPagerAdapter.removePage(1);
    			   
    	       //   actionBar.setSelectedNavigationItem(0);

    			   
    		   }
    		  // else    		   
             // actionBar.setSelectedNavigationItem(position);
           }   
    	   
    	   
    	   
       };
       
       mViewPager.setOnPageChangeListener(pageChangeListener);


		
		List<String> listCategories = new ArrayList<String>(7);
		listCategories.add("Headlines");
		listCategories.add("Pakistan");
		listCategories.add("World");
		listCategories.add("Sports");
		listCategories.add("Entertainment");
		listCategories.add("Business");
		drawerlst.setAdapter(new ArrayAdapter<String>(this, R.layout.custom_list_view,listCategories));


		
		@SuppressWarnings("unused")
		GCM gcmClass = new GCM(this);
		
		addListenerOnButton();

		
		
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
			   //mViewPager.setCurrentItem(3);
			      //   getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, 0).replace(R.id.pager,newsDetail).commit();
			 
			 	//mViewPager.setCurrentItem(2);
			   drawer.closeDrawer(drawerlst);
			    }});
		
		
	}








	@Override
	public void resultLink(String url, String desc) {

        ((TextView) dunya.getView().findViewById(R.id.textView1)).setText(desc);  


	}




	@Override
	public void resultDesc(String str, Bitmap image) {

	      //  ((ImageView) dunya.getView().findViewById(R.id.imageView1)).setImageBitmap(image);
			}




	public void detFragmentValue(Bitmap image, String desc) {
		mNewsPagerAdapter.addPage(newsDetail,1);
	 	mViewPager.setCurrentItem(2);
	     ((ImageView) newsDetail.getView().findViewById(R.id.newsImgBig)).setImageBitmap(image);
	     ((FlowTextView) newsDetail.getView().findViewById(R.id.newsDesc)).setText(desc);
	     ((FlowTextView) newsDetail.getView().findViewById(R.id.newsDesc)).invalidate();



	     
		
	}




	@Override
	public void resultHeadlines(List<String> listTitle) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void resultHeadImg(List<Bitmap> listImg) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void resultTitle(String[] listTitle, String[] listImg) {
		// TODO Auto-generated method stub
		
	}

	
@Override
public void onBackPressed(){
	if(mViewPager.getCurrentItem()== 1)
		mViewPager.setCurrentItem(0);
	else if(mViewPager.getCurrentItem()==0)
		finish();
	
}

	

	
	}
