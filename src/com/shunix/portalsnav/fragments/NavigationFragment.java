package com.shunix.portalsnav.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.utils.BicyclingHandler;
import com.shunix.portalsnav.utils.DrivingHandler;
import com.shunix.portalsnav.utils.TransitHandler;
import com.shunix.portalsnav.utils.WalkingHandler;

public class NavigationFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		int mode = getArguments().getInt("mode");
		double curLat = getArguments().getDouble("lat");
		double curLng = getArguments().getDouble("lng");
		double targetLat = getArguments().getDouble("tarlat");
		double targetLng = getArguments().getDouble("tarlng");
		switch (mode) {
		case PortalsList.DRIVING:
			DrivingHandler drivingHandler = new DrivingHandler(getActivity(), curLat, curLng, targetLat, targetLng);
			break;
		case PortalsList.WALKING:
			WalkingHandler walkingHandler = new WalkingHandler(getActivity(), curLat, curLng, targetLat, targetLng);
			break;
		case PortalsList.BICYCLING:
			BicyclingHandler bicyclingHandler = new BicyclingHandler(getActivity(), curLat, curLng, targetLat, targetLng);
			break;
		case PortalsList.TRANSIT:
			TransitHandler transitHandler = new TransitHandler(getActivity(), curLat, curLng, targetLat, targetLng);
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
