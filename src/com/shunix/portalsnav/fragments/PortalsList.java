package com.shunix.portalsnav.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
	public static final int BICYCLING = Menu.FIRST + 2;
	public static final int TRANSIT = Menu.FIRST + 3;
	private double lat;
	private double lng;
	private ShunixAdapter adapter;

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
		adapter = new ShunixAdapter(getActivity(),
				R.layout.row_layout, arrayList, layoutInflater);
		setListAdapter(adapter);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Bundle bundle = new Bundle();
		bundle.putDouble("lat", lat);
		bundle.putDouble("lng", lng);
		AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
		double targetLat = Double.parseDouble(adapter.getItem(info.position).getPortalLat());
		double targetLng = Double.parseDouble(adapter.getItem(info.position).getPortalLng());
		bundle.putDouble("tarlat", targetLat);
		bundle.putDouble("tarlng", targetLng);
		NavigationFragment navigationFragment = new NavigationFragment();
		switch (item.getItemId()) {
		case DRIVING:
			// Toast.makeText(getActivity(), "Driving",
			// Toast.LENGTH_LONG).show();
			bundle.putInt("mode", DRIVING);
			navigationFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, navigationFragment)
					.addToBackStack(null).commit();
			break;
		case WALKING:
			// Toast.makeText(getActivity(), "Walking",
			// Toast.LENGTH_LONG).show();
			bundle.putInt("mode", WALKING);
			navigationFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, navigationFragment)
					.addToBackStack(null).commit();
			break;
		case BICYCLING:
			// Toast.makeText(getActivity(), "Bicycling",
			// Toast.LENGTH_LONG).show();
			bundle.putInt("mode", BICYCLING);
			navigationFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, navigationFragment)
					.addToBackStack(null).commit();
			break;
		case TRANSIT:
			// Toast.makeText(getActivity(), "Transit",
			// Toast.LENGTH_LONG).show();
			bundle.putInt("mode", TRANSIT);
			navigationFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, navigationFragment)
					.addToBackStack(null).commit();
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Select transport mode");
		menu.add(0, DRIVING, 0, "Driving");
		menu.add(0, WALKING, 0, "Walking");
		menu.add(0, BICYCLING, 0, "Bicycling");
		menu.add(0, TRANSIT, 0, "Transit");
	}

}
