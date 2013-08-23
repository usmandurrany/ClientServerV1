package com.ud.headlines;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.ud.headlines.PullToRefreshListView.OnRefreshListener;

import java.util.List;


public class geoNewsFragment extends Fragment implements IAsyncResult, INewsDetail {

    String url;
    PullToRefreshListView listView;
    List<newsItem> newsItems;
    Bitmap icon;
    geoNewsListAdapter adapter;
    newsItem item;
    getNews getnews;
    List<String> listTitle;
    List<String> listImg;
    ImageLoaderConfiguration config;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    FadeInBitmapDisplayer fadeIn;
    getGeoDesc getGeodesc;
    getGeoNews ggn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View geoNewsFragment = inflater.inflate(R.layout.geonewsfragment, container, false);
        listView = (PullToRefreshListView) geoNewsFragment.findViewById(R.id.geoNews);

        ggn = new getGeoNews(getActivity());

        ggn.delegate = this;


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
                ggn = new getGeoNews(getActivity());
                ggn.delegate = geoNewsFragment.this;
                ggn.execute();
            }


        });


        ggn.execute();

        addListenerOnButton();

        return geoNewsFragment;
    }


    @Override
    public void newsDet(Bitmap image, String desc) {

        ((MainActivity) getActivity()).detFragmentValue(image, desc);
    }

    @Override
    public void resultTitle(String[] listTitle, String[] listImg) {
        // TODO Auto-generated method stub

        adapter = new geoNewsListAdapter(getActivity(), listTitle, listImg, imageLoader);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.onRefreshComplete();


    }

    @Override
    public void resultHeadlines(List<String> listTitle) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resultHeadImg(List<Bitmap> listImg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resultLink(String url, String desc) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resultDesc(String str, Bitmap image) {
        // TODO Auto-generated method stub

    }


    private void addListenerOnButton() {

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), adapter.getItem(position), Toast.LENGTH_LONG).show();
                getGeodesc = new getGeoDesc(getActivity());
                getGeodesc.delegate = geoNewsFragment.this;
                getGeodesc.execute(adapter.getItem(position));


            }
        });


    }


}
