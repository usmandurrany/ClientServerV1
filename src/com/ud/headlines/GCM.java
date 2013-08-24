package com.ud.headlines;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class GCM {
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_ON_SERVER_EXPIRATION_TIME =
            "onServerExpirationTimeMs";
    /**
     * Default lifespan (7 days) of a reservation until it is considered expired.
     */
    public static final long REGISTRATION_EXPIRY_TIME_MS = 1000 * 3600 * 24 * 7;

    /**
     * Substitute you own sender ID here.
     */
    String SENDER_ID = "23726717107";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCMDemo";
    HttpResponse response;


    GoogleCloudMessaging gcm;
    SharedPreferences prefs;
    Context context;
    String regid;
    gcmRegServer gRS = new gcmRegServer();

    public GCM(Context con) {

        context = con;
        regid = getRegistrationId(context);

        if (regid.length() == 0) {
            Toast.makeText(context, regid + "Not registered", Toast.LENGTH_LONG).show();

            registerBackground();
        } else
            //Toast.makeText(this, regid, Toast.LENGTH_LONG).show();
            //Log.w("REG ID",regid);
            //gRS.execute();
            gcm = GoogleCloudMessaging.getInstance(context);

    }

    class gcmRegServer extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... arg0) {
            ArrayList<NameValuePair> gcmParams = new ArrayList<NameValuePair>();
            gcmParams.add(new BasicNameValuePair("name", android.os.Build.MODEL));
            gcmParams.add(new BasicNameValuePair("email", ""));
            gcmParams.add(new BasicNameValuePair("regId", regid));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 15000);
            HttpConnectionParams.setSoTimeout(httpParams, 15000);

            HttpClient httpclient = new DefaultHttpClient(httpParams);
            HttpPost httppost = new HttpPost("http://www.xpperts.com/register.php");

            try {
                httppost.setEntity(new UrlEncodedFormEntity(gcmParams));
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
            return regid;
        }

        @Override
        protected void onPostExecute(String string) {
            Log.e("GRS", "RegId Sent " + regid);
        }
    }


    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.length() == 0) {
            Log.v(TAG, "Registration not found.");
            return "";
        }
        // check if app was updated; if so, it must clear registration id to
        // avoid a race condition if GCM sends a message
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion || isRegistrationExpired()) {
            Log.v(TAG, "App version changed or registration expired.");
            return "";
        }
        return registrationId;
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGCMPreferences(Context context) {
        return context.getSharedPreferences(GCM.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * Checks if the registration has expired.
     * <p/>
     * <p>To avoid the scenario where the device sends the registration to the
     * server but the server loses it, the app developer may choose to re-register
     * after REGISTRATION_EXPIRY_TIME_MS.
     *
     * @return true if the registration has expired.
     */
    private boolean isRegistrationExpired() {
        final SharedPreferences prefs = getGCMPreferences(context);
        // checks if the information is not stale
        long expirationTime =
                prefs.getLong(PROPERTY_ON_SERVER_EXPIRATION_TIME, -1);
        return System.currentTimeMillis() > expirationTime;
    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p/>
     * Stores the registration id, app versionCode, and expiration time in the
     * application's shared preferences.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void registerBackground() {
        new AsyncTask() {


            @Override
            protected String doInBackground(Object... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration id=" + regid;

                    // You should send the registration ID to your server over HTTP,
                    // so it can use GCM/HTTP or CCS to send messages to your app.

                    // For this demo: we don't need to send it because the device
                    // will send upstream messages to a server that echo back the message
                    // using the 'from' address in the message.

                    // Save the regid - no need to register again.
                    gRS.execute();
                    setRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

	        /*protected void onPostExecute(String msg) {
		           // Toast.makeText(this, msg + "\n", Toast.LENGTH_LONG).show();
		        	Log.v("Message:  ",msg);
		        }*/


        }.execute(null, null, null);
    }

    private void setRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = getAppVersion(context);
        Log.v(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        long expirationTime = System.currentTimeMillis() + REGISTRATION_EXPIRY_TIME_MS;

        // Log.v(TAG, "Setting registration expiry time to " +
        //       new Timestamp(expirationTime));
        editor.putLong(PROPERTY_ON_SERVER_EXPIRATION_TIME, expirationTime);
        editor.commit();
    }

}