package com.shunix.portalsnav.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.ui.ShunixAdapter;
import com.shunix.portalsnav.utils.BasicPortal;

public class PortalsList extends ListFragment {

	public static final int DRIVING = Menu.FIRST;
	public static final int WALKING = Menu.FIRST + 1;
	// public static final int BICYCLING = Menu.FIRST + 2;
	public static final int TRANSIT = Menu.FIRST + 2;
	private double lat;
	private double lng;
	private ShunixAdapter adapter;
	private final String URL_STUB = "http://ditu.google.cn/maps?f=d&source=s_d&saddr=";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.protals_layout, container, false);
		ListView listView = (ListView) view.findViewById(android.R.id.list);
		registerForContextMenu(listView);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		// Can NOT directly cast to BasicPortal Array.
		Object[] portals = bundle.getParcelableArray("array");
		List<BasicPortal> arrayList = new ArrayList<BasicPortal>();
		for (int i = 0; i < portals.length; ++i) {
			arrayList.add((BasicPortal) portals[i]);
		}
		lat = bundle.getDouble("lat");
		lng = bundle.getDouble("lng");
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		adapter = new ShunixAdapter(getActivity(), R.layout.row_layout,
				arrayList, layoutInflater);
		setListAdapter(adapter);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		String targetLat = adapter.getItem(info.position).getPortalLat();
		String targetLng = adapter.getItem(info.position).getPortalLng();
		String target = "";
		switch (item.getItemId()) {
		case DRIVING:
			target = URL_STUB + String.valueOf(lat) + ","
					+ String.valueOf(lng) + "&daddr=" + targetLat + ","
					+ targetLng + "&dirflg=d";
			break;
		case WALKING:
			target = URL_STUB + String.valueOf(lat) + ","
					+ String.valueOf(lng) + "&daddr=" + targetLat + ","
					+ targetLng + "&dirflg=w";
			break;
		case TRANSIT:
			target = URL_STUB + String.valueOf(lat) + ","
					+ String.valueOf(lng) + "&daddr=" + targetLat + ","
					+ targetLng + "&dirflg=r";
			break;

		default:
			break;
		}
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(target));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				& Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
		intent.setClassName("com.google.android.apps.maps", 
            "com.google.android.maps.MapsActivity");
		getActivity().startActivity(intent);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Select transport mode");
		menu.add(0, DRIVING, 0, "Driving");
		menu.add(0, WALKING, 0, "Walking");
		menu.add(0, TRANSIT, 0, "Transit");
	}

}
