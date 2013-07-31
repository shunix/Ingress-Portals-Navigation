package com.shunix.portalsnav.fragments;

import com.shunix.portalsnav.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PortalsFilterFragment extends ListFragment {

	private LocationListener locationListener;
	private LocationManager locationManager;
	private ProgressDialog dialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}
			
			@Override
			public void onProviderEnabled(String provider) {
			}
			
			@Override
			public void onProviderDisabled(String provider) {
			}
			
			@Override
			public void onLocationChanged(Location location) {
			}
		};
		dialog = ProgressDialog.show(getActivity(), "Locating", "Please wait for a moment");
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 0, locationListener);
		//Clear GPS cache on every startup
		Bundle b = new Bundle();
		locationManager.sendExtraCommand("gps", "force_xtra_injection", b);
		locationManager.sendExtraCommand("gps", "force_time_injection", b);
		//Get GPS data
		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.portals_search, container, false);
		return view;
	}

}
