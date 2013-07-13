package com.ud.clientserverv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
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
	//Document doc;

	
	// Create a JSON object from the request response
	JSONObject jsonObject = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lst_tv = (TextView) findViewById(R.id.rowTextView);
		strRes = (TextView) findViewById(R.id.textView1);
		drawerlst = (ListView) findViewById(R.id.left_drawer);
		drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
//		Intent GCM = new Intent(this, GCM.class);
	//	startActivity(GCM);
		
		GCM gcmClass = new GCM(this);
		
		addListenerOnButton();
		getdata gd = new getdata();
        mediaController = new MediaController(this);

		gd.execute();
		
		
		
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
	
		
	/*class urlparse extends AsyncTask<String,Void,String>
	{
		protected String doInBackground(String... arg0)
		{
			try {
				doc = Jsoup.parse(new URL("http://www.google.com"), 2000);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "none";
			
		}
		protected void onPostExecute(String document)
		{

            Elements resultLinks = doc.select("a");
            Toast.makeText(MainActivity.this, "number of links: " + resultLinks.size(),Toast.LENGTH_LONG).show();
            for (Element link : resultLinks) {
                System.out.println();
                String href = link.attr("href");
           //     System.out.println("Title: " + link.text());
	            //Toast.makeText(this, "number of links: " + resultLinks.size(),Toast.LENGTH_LONG);
            }
		}
	} */


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
	        //	setContentView(R.layout.activiry_live_view);
	 
	    		Intent liveView = new Intent(MainActivity.this, LiveViewActivity.class);
	    		startActivity(liveView);
	    		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
	        	
	        	//urlparse uparse = new urlparse();
	        	//uparse.execute();

	    		return true;

	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	class getdata extends AsyncTask<String, Void, String>
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
			ArrayList<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair("network", ""));
			postParams.add(new BasicNameValuePair("country", ""));
			
			//Create the HTTP request
			HttpParams httpParameters = new BasicHttpParams();

			//Setup timeouts
			HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
			HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost httppost = new HttpPost("http://www.xpperts.com/test.php");
	
			try {
					httppost.setEntity(new UrlEncodedFormEntity(postParams));
				} catch (UnsupportedEncodingException e) {
				
					e.printStackTrace();
				}
			
				try {
					response = httpclient.execute(httppost);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
			HttpEntity entity = response.getEntity();

			try {
				content = entity.getContent();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        
	        try {
				while ((line = reader.readLine()) != null) {
				  result.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
				return result.toString(); // return the json string to onPostExecute

		}
		
		protected void onPostExecute(String result)
		{
			try {
				JSONObject json=new JSONObject(result);
				JSONArray jsonArray = json.getJSONArray("rss"); //get the items under the name RSS
			      jsonArray.getJSONObject(1);
			      int length = jsonArray.length();
			      List<String> listContents = new ArrayList<String>(length);
			      for (int i = 0; i < length; i++)
			      {
			        listContents.add(jsonArray.getJSONObject(i).getString("title"));
			       // Log.d("JSON ",jsonArray.getString(i));

			      }
	                getActionBar().setTitle(jsonArray.getJSONObject(0).getString("title"));

		    	    strRes.setText(jsonArray.getJSONObject(0).getString("description"));

			      // Put the custom_list_view.xml in the drawerView and populate it with the json titles
			      drawerlst.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.custom_list_view, listContents));
			      // Cancel the task if it still keeps running 
	              this.cancel(true);
	              pDialog.dismiss();
			}
				
			catch (JSONException e) {
				e.printStackTrace();
			}

		}
		
		
	}
	
	class getdetails extends getdata
	{

		protected void onPostExecute(String result)
		{
			try {
				JSONObject json=new JSONObject(result);
				JSONArray jsonArray = json.getJSONArray("rss");
			    strRes.setText(jsonArray.getJSONObject(itemindex).getString("description"));
			    this.cancel(true);
			    pDialog.dismiss();
			}

				
			catch (JSONException e) {
				e.printStackTrace();
			}

		}
		
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {   //menu items
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);// actionbar menu item drawer open close

		return true;
	}
	
	private void addListenerOnButton() {
	
/*btn.setOnTouchListener(new OnTouchListener() {
	

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
				//txt.setText("Usman Durrani");
			getdata gd = new getdata();
			gd.execute();
		return false;
	}
		});*/
		drawerlst.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
			      title = (drawerlst.getItemAtPosition(position).toString());
			      itemindex = position;
					//Toast.makeText(getBaseContext(), , Toast.LENGTH_LONG).show();
					getdetails gdet = new getdetails();

					gdet.execute();
					drawer.closeDrawer(drawerlst);
			    }});
		
		
	}
	




}
