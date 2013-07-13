package com.ud.clientserverv1;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
	 * Handling of GCM messages.
	 */
	public class MyBroadcastReceiver extends BroadcastReceiver {
	    static final String TAG = "GCMDemo";
	    public static final int NOTIFICATION_ID = 1;
	    private NotificationManager mNotificationManager;
	    NotificationCompat.Builder builder;
	    Context ctx;
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
	        ctx = context;
	        String messageType = gcm.getMessageType(intent);
	        if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
	            sendNotification("Send error: " + intent.getExtras().toString());
	        } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
	            sendNotification("Deleted messages on server: " +
	                    intent.getExtras().toString());
	        } else {
	            sendNotification("Received: " + intent.getExtras().getString("message").toString());
	        }
	        setResultCode(Activity.RESULT_OK);
	    }

	    // Put the GCM message into a notification and post it.
	    private void sendNotification(String msg) {
	        mNotificationManager = (NotificationManager)
	                ctx.getSystemService(Context.NOTIFICATION_SERVICE);

	        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
	                new Intent(ctx, GCM.class), 0);

	        NotificationCompat.Builder mBuilder =
	                new NotificationCompat.Builder(ctx)
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentTitle("GCM Notification")
	        .setStyle(new NotificationCompat.BigTextStyle()
	        .bigText(msg))
	        .setContentText(msg);

	        mBuilder.setContentIntent(contentIntent);
	        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	    }
	    private static void generateNotification(Context context, String message) {
	        int icon = R.drawable.ic_launcher;
	        long when = System.currentTimeMillis();
	        NotificationManager notificationManager = (NotificationManager)
	                context.getSystemService(Context.NOTIFICATION_SERVICE);
	        Notification notification = new Notification(icon, message, when);
	         
	        String title = context.getString(R.string.app_name);
	         
	        Intent notificationIntent = new Intent(context, MainActivity.class);
	        // set intent so it does not start a new activity
	        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                Intent.FLAG_ACTIVITY_SINGLE_TOP);
	        PendingIntent intent =
	                PendingIntent.getActivity(context, 0, notificationIntent, 0);
	        notification.setLatestEventInfo(context, title, message, intent);
	        notification.flags |= Notification.FLAG_AUTO_CANCEL;
	         
	        // Play default notification sound
	        notification.defaults |= Notification.DEFAULT_SOUND;
	         
	        // Vibrate if vibrate is enabled
	        notification.defaults |= Notification.DEFAULT_VIBRATE;
	        notificationManager.notify(0, notification);     
	 
	    }
	 
	
	}	