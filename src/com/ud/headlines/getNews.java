package com.ud.headlines;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


class getNews extends AsyncTask<String, Void, String> {

    public IAsyncResult delegate;


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
    public String newsRSS1 = "http://m.dunyanews.tv/caller.php?q=hd&n=0";
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

    public getNews(Context context) {
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
            doc = Jsoup.parse(new URL(newsRSS1), 5000);
        } catch (MalformedURLException e) {
            Log.e("Malformed URL", newsRSS1);
        } catch (IOException e) {
            Log.e("IO Exception", e.toString());
            pDialog.dismiss();
            this.cancel(true);
        }
        return null;

    }


    protected void onPostExecute(String result) {

        Elements getTitle = doc.select("div.news-panel > a > h2");
        Elements getDesc = doc.select("div.news-panel > p");
        Elements getImgLink = doc.select("div.news-panel > p > img[src]");

        listTitle = new ArrayList<String>(getTitle.size());
        listDesc = new ArrayList<String>(getDesc.size());
        listImg = new ArrayList<String>(getImgLink.size());


        //for (int i = 2; i < getTitle.size(); i++)
        for (Element e : getTitle)
            listTitle.add(e.text());

        for (Element e : getDesc)
            listDesc.add(e.text());

        links = new String[getImgLink.size()];
        for (int i = 0; i < getTitle.size(); i++) {
            links[i] = getImgLink.get(i).attr("src");
            //Toast.makeText(context, getImgLink.get(i).attr("src").toString(), Toast.LENGTH_LONG).show();
        }
        titles = new String[getTitle.size()];
        for (int i = 0; i < getTitle.size(); i++) {

            titles[i] = getTitle.get(i).text();
            //Toast.makeText(context, getImgLink.get(i).attr("src").toString(), Toast.LENGTH_LONG).show();
        }
        delegate.resultTitle(titles, links);
        pDialog.dismiss();


        // glink.execute(0);

    }
}

