package com.shunix.portalsnav.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.utils.DrivingHandler;

public class NavigationFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		int mode = getArguments().getInt("mode");
		switch (mode) {
		case PortalsList.DRIVING:
			DrivingHandler drivingHandler = new DrivingHandler(getActivity(), lat1, lng1, lat2, lng2);
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
