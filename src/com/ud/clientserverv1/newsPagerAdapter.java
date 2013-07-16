package com.ud.clientserverv1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class newsPagerAdapter extends FragmentPagerAdapter {

        public newsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    // The first section of the app is the most interesting -- it offers
                    // a launchpad into the other demonstrations in this example application.
                    return new dunyaNewsFragment();
                  
                case 1:
                	return new geoNewsFragment();

                default:
                    // The other sections of the app are dummy placeholders.

                    return new dunyaNewsFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	switch(position){
        	case 0:
        		return "Dunya News";
        	case 1:
        		return "Geo News";
			default:
        	return null;
        	}
        }
    }