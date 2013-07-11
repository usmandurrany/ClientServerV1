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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {


	
	//EditText txt;
	//Button btn;
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
    
	// Create a JSON object from the request response
	JSONObject jsonObject = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//btn = (Button) findViewById(R.id.button1);
		//txt = (EditText) findViewById(R.id.editText1);
		lst_tv = (TextView) findViewById(R.id.rowTextView);
		strRes = (TextView) findViewById(R.id.textView1);
		drawerlst = (ListView) findViewById(R.id.left_drawer);
		drawer =  (DrawerLayout) findViewById(R.id.drawer_layout);
		addListenerOnButton();
		getdata gd = new getdata();
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


	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.drawer:
	            if(drawer.isDrawerOpen(drawerlst) == false)
	        	drawer.openDrawer(drawerlst);
	            else 
	            	drawer.closeDrawer(drawerlst);
	            return true;

	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	class getdata extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... arg0) {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("FirstNameToSearch", ""));
			//Create the HTTP request
			HttpParams httpParameters = new BasicHttpParams();

			//Setup timeouts
			HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
			HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost httppost = new HttpPost("http://192.168.10.3/oauth/test.php");
	
				try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					response = httpclient.execute(httppost);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			HttpEntity entity = response.getEntity();

			try {
				content = entity.getContent();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        try {
				while ((line = reader.readLine()) != null) {
				  result.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//	try {
				//	result = EntityUtils.toString(entity);
				//} catch (ParseException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	} catch (IOException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}

				return result.toString();

			
	


		}
		
		protected void onPostExecute(String result)
		{
			try {
				JSONObject json=new JSONObject(result);
				JSONArray jsonArray = json.getJSONArray("rss");
			      jsonArray.getJSONObject(1);
			      int length = jsonArray.length();
			      List<String> listContents = new ArrayList<String>(length);
			      for (int i = 0; i < length; i++)
			      {
			        listContents.add(jsonArray.getJSONObject(i).getString("title"));
			        Log.d("JSON ",jsonArray.getString(i));
		    	    strRes.setText(jsonArray.getJSONObject(i).getString("description"));

			      }

			      drawerlst.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.custom_list_view, listContents));
	              this.cancel(true);
			//	jsonObject = new JSONObject(result);
		//	} catch (JSONException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
			}

			//try {
				//strRes.setText(jsonObject.getString("FirstName"));
				
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}
	class getdetails extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... arg0) {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("FirstNameToSearch", ""));
			//Create the HTTP request
			HttpParams httpParameters = new BasicHttpParams();

			//Setup timeouts
			HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
			HttpConnectionParams.setSoTimeout(httpParameters, 15000);			

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost httppost = new HttpPost("http://192.168.10.3/oauth/test.json");

				try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					response = httpclient.execute(httppost);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			HttpEntity entity = response.getEntity();

			try {
				content = entity.getContent();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        try {
				while ((line = reader.readLine()) != null) {
				  result.append(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//	try {
				//	result = EntityUtils.toString(entity);
				//} catch (ParseException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	} catch (IOException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}

				return result.toString();

			



		}
		
		protected void onPostExecute(String result)
		{
			try {
				JSONObject json=new JSONObject(result);
				JSONArray jsonArray = json.getJSONArray("rss");
			      jsonArray.length();

			    	  //if(jsonArray.getString(0) == title)
			    	  //{
			    	   strRes.setText(jsonArray.getJSONObject(itemindex).getString("description"));
			    	   // break;
			    	  //}
			      //}

      
			//	jsonObject = new JSONObject(result);
		//	} catch (JSONException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
			}

			//try {
				//strRes.setText(jsonObject.getString("FirstName"));
				
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
//	    inflater.inflate(R.menu.game_menu, menu);

		return true;
	}
	
	private void addListenerOnButton() {
		// TODO Auto-generated method stub
	
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
