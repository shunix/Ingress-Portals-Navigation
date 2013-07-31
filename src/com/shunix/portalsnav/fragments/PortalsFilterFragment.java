package com.shunix.portalsnav.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.utils.BasicPortal;
import com.shunix.portalsnav.utils.DatabaseManager;

public class PortalsFilterFragment extends Fragment {

	private LocationListener locationListener;
	private LocationManager locationManager;
	private ProgressDialog dialog;
	private Location location;
	private TextView curlocTextView;
	private EditText rangeEditText;
	private Button listButton;
	private Button cancelButton;
	private double lat = 0;
	private double lng = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
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
		dialog = ProgressDialog.show(getActivity(), "Locating",
				"Please wait for a moment");
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 0, locationListener);
		Thread gpsThread = new GPSThread();
		gpsThread.start();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.portals_search, container, false);
		curlocTextView = (TextView) view.findViewById(R.id.textView1);
		rangeEditText = (EditText) view.findViewById(R.id.editText1);
		listButton = (Button) view.findViewById(R.id.button1);
		cancelButton = (Button) view.findViewById(R.id.button2);
		listButton.setOnClickListener(listListener);
		cancelButton.setOnClickListener(canceListener);
		return view;
	}

	OnClickListener listListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int range = 1;
			try {
				try {
					range = Integer
							.parseInt(rangeEditText.getText().toString());
				} catch (Exception e) {
					Toast.makeText(getActivity(),
							"Your input is not a valid number.",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				DatabaseManager dbManager = new DatabaseManager(getActivity(),
						"Database");
				dbManager.endTransction();
				List<BasicPortal> arrayList = new ArrayList<BasicPortal>();
				arrayList = dbManager.getPortalsWithin(lat, lng, range);
				BasicPortal[] portals = new BasicPortal[arrayList.size()];
				for(int i = 0; i < arrayList.size(); ++i) {
					portals[i] = arrayList.get(i);
				}
				PortalsList portalsList = new PortalsList();
				Bundle bundle = new Bundle();
				//Can NOT directly pass an arraylist as parcelable object in bundle.
				bundle.putParcelableArray("array", portals);
				bundle.putDouble("lat", lat);
				bundle.putDouble("lng", lng);
				portalsList.setArguments(bundle);
				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(R.id.container, portalsList)
						.addToBackStack(null).commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	OnClickListener canceListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			getActivity().getSupportFragmentManager().popBackStack();
		}
	};

	class GPSThread extends Thread {

		@Override
		public void run() {
			super.run();
			// Clear GPS cache on every startup
			Bundle b = new Bundle();
			locationManager.sendExtraCommand("gps", "force_xtra_injection", b);
			locationManager.sendExtraCommand("gps", "force_time_injection", b);
			// Get GPS data
			location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			while (location == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lat = location.getLatitude();
			lng = location.getLongitude();
			getActivity().runOnUiThread(new Runnable() {

				@Override
				public void run() {
					String lat = String.valueOf(location.getLatitude());
					String lng = String.valueOf(location.getLongitude());
					curlocTextView.setText(lat + "," + lng);
					dialog.dismiss();
				}
			});
		}
	}
}
