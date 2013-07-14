package com.ud.clientserverv1;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class LiveViewActivity extends Activity 
{
	VideoView mVideoView;
	MediaController mediaController;
	Uri video;
	ProgressDialog videoLoading;
	AlertDialog.Builder popup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activiry_live_view);
		getActionBar().setTitle("Live Stream");
	/*
		videoLoading = new ProgressDialog(this);

		mVideoView = (VideoView) findViewById(R.id.videoView1);
        mediaController = new MediaController(this);

        mediaController.setAnchorView(mVideoView);
        video = Uri.parse("rtmp://78.129.192.5/dunvodstrm/14JUL13-HL-16-00-PM");
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(video);
        mVideoView.start();
		
		videoLoading.setMessage("Loading..");
		videoLoading.setCancelable(false);
		videoLoading.setIndeterminate(true);
		videoLoading.show();
		
        mVideoView.setOnPreparedListener(new OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                videoLoading.dismiss();
            }
        });
    */
		 
		
		popup = new AlertDialog.Builder(this);
		popup.setMessage("Coming Soon");
		popup.setCancelable(false);
		popup.setPositiveButton("Back", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);				
			}
			});
		
		popup.show();
		
	}
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		finish();
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {   //menu items
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);// actionbar menu item drawer open close

		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	
		switch (item.getItemId())
		{
		case R.id.liveView:
			finish();
    		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

		
		}
		return false;

	}
		
	
}