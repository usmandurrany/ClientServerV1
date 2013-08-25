package com.ud.headlines;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Usman on 8/25/13.
 *
 */
public class newsUpadateCheck extends Service implements IAsyncResult {
    public static final long NOTIFY_INTERVAL = 60 * 1000; // 10 seconds
    private NotificationManager mNotificationManager;
    public static final int NOTIFICATION_ID = 1;
    getLatestNews checkNews;
    Uri alarmSound;
    public Context ctx;

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // cancel if already existed
        ctx = getApplicationContext();
        checkNews = new getLatestNews(ctx);
        checkNews.delegate=this;

        if(mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }

    @Override
    public void resultTitle(String[] listTitle, String[] listImg) {
        sendNotification(listTitle[0]);

    }

    @Override
    public void resultHeadlines(List<String> listTitle) {

    }

    @Override
    public void resultHeadImg(List<Bitmap> listImg) {

    }

    @Override
    public void resultLink(String url, String desc) {

    }

    @Override
    public void resultDesc(String str, Bitmap image) {

    }


    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // display toast

                 checkNews.execute();

                   // Toast.makeText(getApplicationContext(), getDateTime(),
                     //       Toast.LENGTH_SHORT).show();


                }

            });
        }

        private String getDateTime() {
            // get date time in custom format
            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return sdf.format(new Date());
        }

    }
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(ctx, newsDetailActivity.class);
        notificationIntent.putExtra("news", "Durrani");
        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
                notificationIntent, 0);
        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("GCM Notification")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg)
                        .setSound(alarmSound)
                        .setAutoCancel(true);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
