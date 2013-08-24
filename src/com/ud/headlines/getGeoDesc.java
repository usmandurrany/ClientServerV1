package com.ud.headlines;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


class getGeoDesc extends getDesc {

    Elements imgLink;


    public getGeoDesc(Context context) {
        super(context);

        super.newsRSS1 = "http://www.geo.tv/Print.aspx?ID=";
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void onPostExecute(String string) {
        imgLink = doc.select("img#imgMain");
        imgSrc = imgLink.attr("src");
        desc = doc.select("span#lblDetail");
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... arg0) {
                try {
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(imgSrc).getContent());
                    //Log.e("desc",desc.text());
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String string) {
                delegate.newsDet(bitmap, desc.text());

            }

        }.execute(imgSrc);

        //Toast.makeText(context, link.get(itemindex).nextSibling().toString(),Toast.LENGTH_LONG).show();
        // gdesc.execute();
        pDialog.dismiss();
    }


}


