package com.ud.headlines;

import android.content.Context;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


class getGeoNews extends getNews {

    Context context;

    List<String> listId;

    public getGeoNews(Context context) {
        super(context);

        this.context = context;
        // TODO Auto-generated constructor stub

        super.newsRSS1 = "http://mcs.geo.tv/rss/headline.xml";


    }


    @Override
    protected void onPostExecute(String result) {

        Elements getTitle = doc.select("item>title");
        Elements getId = doc.select("item>link");
        //////Elements getImgLink = doc.select("div.news-panel > p > img[src]");

        listTitle = new ArrayList<String>(getTitle.size());
        listId = new ArrayList<String>(getId.size());
        //////listImg = new ArrayList<String>(getImgLink.size());


        for (Element e : getTitle)
            listTitle.add(e.text());

        for (Element e : getId) {


            String[] splitID = e.nextSibling().toString().split("=");

            //Toast.makeText(context, "Your ID is"+ splitID[1] , Toast.LENGTH_LONG).show();

            listId.add(splitID[1]);


        }

        links = new String[getId.size()];
        for (int i = 0; i < getId.size(); i++) {
            links[i] = listId.get(i); //getId.get(i).attr("src");
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