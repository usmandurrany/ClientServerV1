package com.ud.headlines;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.ud.headlines.PullToRefreshListView.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class dunyaNewsFragment extends Fragment implements IAsyncResult, INewsDetail {

    String url;
    PullToRefreshListView listView;
    List<newsItem> newsItems;
    Bitmap icon;
    newsListAdapter adapter;
    newsItem item;
    getNews getnews;
    List<String> listTitle;
    List<String> listImg;
    ImageLoaderConfiguration config;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    FadeInBitmapDisplayer fadeIn;
    getDesc getdesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View dunyaNewsFragment = inflater.inflate(R.layout.dunyanewsfragment, container, false);
        listView = (PullToRefreshListView) dunyaNewsFragment.findViewById(R.id.news);

        getnews = new getNews(getActivity());
        getdesc = new getDesc(getActivity());
        newsItems = new ArrayList<newsItem>();

        getnews.delegate = this;
        getdesc.delegate = this;

        fadeIn = new FadeInBitmapDisplayer(500);

        if (imageLoader == null) {
            options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.nonews_s)
                    .delayBeforeLoading(200)
                    .cacheInMemory(true) // default
                    .displayer(fadeIn)

                    .build();
            config = new ImageLoaderConfiguration.Builder(getActivity()).defaultDisplayImageOptions(options).threadPoolSize(5).build();
            this.imageLoader = ImageLoader.getInstance();
            this.imageLoader.init(config);
        }
        PauseOnScrollListener POSlistener = new PauseOnScrollListener(imageLoader, true, false);
        listView.setOnScrollListener(POSlistener);
        listView.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                getnews = new getNews(getActivity());
                getnews.delegate = dunyaNewsFragment.this;
                getnews.execute();
            }


        });
        if (isNetworkConnected()) {
            getnews.execute();

        } else {
            //Toast.makeText(getActivity(), "Network error", Toast.LENGTH_LONG).show();
            Builder ab = new AlertDialog.Builder(getActivity());
            ab.setTitle("Network error");
            ab.setMessage("Cant connect to server");
            ab.setPositiveButton("Close", new OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    getActivity().finish();

                }

            });
            ab.show();
        }


        addListenerOnButton();
        return dunyaNewsFragment;
    }

    @Override
    public void resultTitle(String[] listTitle, String[] listImg) {
        adapter = new newsListAdapter(getActivity(), listTitle, listImg, imageLoader);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.onRefreshComplete();


    }

    @Override
    public void resultLink(String url, String desc) {
        this.url = url;

        ((TextView) this.getView().findViewById(R.id.textView1)).setText(desc);

    }

    @Override
    public void resultDesc(String str, Bitmap image) {


        ((ImageView) this.getView().findViewById(R.id.imageView1)).setImageBitmap(image);
    }

    private void addListenerOnButton() {

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), adapter.getItem(position), Toast.LENGTH_LONG).show();
                getdesc = new getDesc(getActivity());
                getdesc.delegate = dunyaNewsFragment.this;
                getdesc.execute(adapter.getItem(position));


            }
        });


    }

    @Override
    public void newsDet(Bitmap image, String desc) {

        //Toast.makeText(getActivity(),desc, Toast.LENGTH_LONG).show();

        ((MainActivity) getActivity()).detFragmentValue(image, desc);
    }

    public boolean isNetworkConnected() {
        final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
    }

    @Override
    public void resultHeadlines(List<String> listTitle) {
        for (int i = 0; i < listTitle.size(); i++) {
            newsItem item = new newsItem(listTitle.get(i));
            newsItems.add(item);
        }
        listView.setAdapter(adapter);
    }

    @Override
    public void resultHeadImg(List<Bitmap> listImg) {
        for (int i = 0; i < listImg.size(); i++) {
            item = new newsItem(listImg.get(i));
            newsItems.add(item);

        }
    }
}
