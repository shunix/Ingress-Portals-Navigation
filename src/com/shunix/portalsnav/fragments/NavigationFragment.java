package com.shunix.portalsnav.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.ui.ShunixHandler;
import com.shunix.portalsnav.utils.BicyclingHandler;
import com.shunix.portalsnav.utils.DrivingHandler;
import com.shunix.portalsnav.utils.TransitHandler;
import com.shunix.portalsnav.utils.WalkingHandler;


public class NavigationFragment extends ListFragment {
	
	private ShunixHandler handler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		int mode = getArguments().getInt("mode");
		double curLat = getArguments().getDouble("lat");
		double curLng = getArguments().getDouble("lng");
		double targetLat = getArguments().getDouble("tarlat");
		double targetLng = getArguments().getDouble("tarlng");
		handler = new ShunixHandler(this);
		switch (mode) {
		case PortalsList.DRIVING:
			DrivingHandler drivingHandler = new DrivingHandler(getActivity(), curLat, curLng, targetLat, targetLng, handler);
			drivingHandler.processData();
			break;
		case PortalsList.WALKING:
			WalkingHandler walkingHandler = new WalkingHandler(getActivity(), curLat, curLng, targetLat, targetLng, handler);
			walkingHandler.processData();
			break;
		case PortalsList.BICYCLING:
			BicyclingHandler bicyclingHandler = new BicyclingHandler(getActivity(), curLat, curLng, targetLat, targetLng, handler);
			bicyclingHandler.processData();
			break;
		case PortalsList.TRANSIT:
			TransitHandler transitHandler = new TransitHandler(getActivity(), curLat, curLng, targetLat, targetLng, handler);
			transitHandler.processData();
			break;

		default:
			break;
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.navigation_list, container, false);
	}

}
