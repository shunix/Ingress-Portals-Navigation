package com.shunix.portalsnav.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.ui.ShunixAdapter;
import com.shunix.portalsnav.utils.BasicPortal;

public class PortalsList extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.protals_layout, container, false);
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		//Can NOT directly cast to BasicPortal Array.
		Object[] portals = bundle.getParcelableArray("array");
		List<BasicPortal> arrayList = new ArrayList<BasicPortal>();
		for(int i = 0; i < portals.length; ++i) {
			arrayList.add((BasicPortal)portals[i]);
		}
		LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ShunixAdapter adapter = new ShunixAdapter(getActivity(), R.layout.row_layout, arrayList, layoutInflater);
		setListAdapter(adapter);
	}



	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

}
