package com.ud.headlines;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class newsPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> mListViews = new ArrayList<Fragment>();

    public newsPagerAdapter(FragmentManager fm) {
        super(fm);
        mListViews.add(new dunyaNewsFragment());
        mListViews.add(new geoNewsFragment());
    }

    @Override
    public Fragment getItem(int i) {

        return mListViews.get(i);
    }


    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Dunya News";
            case 1:
                return "Geo News";
            default:
                return null;
        }
    }

    public void addPage(Fragment frag, int pos) {
        mListViews.add(pos, frag);
        notifyDataSetChanged();

    }

    public void removePage(int position) {
        mListViews.remove(position);

        notifyDataSetChanged();
    }
}
    