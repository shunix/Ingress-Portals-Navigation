package com.shunix.portalsnav.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.ui.ShunixAdapter;
import com.shunix.portalsnav.utils.BasicPortal;
import com.shunix.portalsnav.utils.ShunixParcelable;

public class PortalsList extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.protals_layout, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ShunixParcelable parcelable = (ShunixParcelable)getArguments().getParcelable("list");
		List<BasicPortal> arrayList = parcelable.getPortals();
		ShunixAdapter adapter = new ShunixAdapter(getActivity(), R.layout.row_layout, arrayList);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

}
