package com.ud.headlines;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


class getDesc extends AsyncTask<String, Void, String> {

    public INewsDetail delegate;


    Context context;
    TextView strRes;
    ListView drawerlst;
    String line;
    TextView lst_tv;
    String title;
    ProgressDialog pDialog;
    Builder ab;
    Uri uri;
    Document doc;
    Elements link;
    Elements desc;
    String description;
    protected String newsRSS1 = "http://m.dunyanews.tv/caller.php?q=hd&n=";
    String url;
    ImageView newshead;
    String imgSrc;
    Bitmap bitmap;
    Elements result;
    String[] links;
    String[] titles;
    List<String> listImg;
    List<String> listDesc;
    List<String> listTitle;

    public getDesc(Context context) {
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Plese wait...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();


    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            //String rawurl = "http://m.dunyanews.tv/caller.php?q=hd&n="+arg0[0];
            String url = arg0[0].replaceAll(" ", "%20");
            doc = Jsoup.parse(new URL(newsRSS1 + url), 4000);
            Log.e("URL", url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arg0[0];

    }

    @Override
    protected void onPostExecute(String string) {
        Elements imgLink = doc.select("div.news-panel > p > img[src]");
        imgSrc = imgLink.attr("src");
        desc = doc.select("div.news-panel > p");
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

