package com.ud.clientserverv1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class newsDetailFragment extends Fragment
{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			   Bundle savedInstanceState) {
			  View myFragmentView = inflater.inflate(R.layout.newsdetailfragment, container, false);

			  return myFragmentView;
			 }
	
	}