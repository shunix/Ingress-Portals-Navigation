package com.shunix.portalsnav.fragments;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.shunix.portalsnav.R;

public class StartFragment extends Fragment {

	private Button updateButton;
	private Button startButton;
	private Button aboutButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public boolean isNetworkAvailable() {
		Context context = getActivity().getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isGPSAvailable() {
		LocationManager alm = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.start_layout, container, false);
		updateButton = (Button) layout.findViewById(R.id.button1);
		startButton = (Button) layout.findViewById(R.id.button2);
		aboutButton = (Button) layout.findViewById(R.id.button3);
		updateButton.setOnClickListener(updateListener);
		startButton.setOnClickListener(startListener);
		aboutButton.setOnClickListener(aboutListener);
		return layout;
	}

	OnClickListener updateListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (!isNetworkAvailable()) {
				Toast.makeText(
						getActivity(),
						"Network is not avaliable,  please enable network first",
						Toast.LENGTH_LONG).show();
				return;
			}
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new ChooseArea())
					.addToBackStack(null).commit();
		}
	};

	OnClickListener startListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (!isNetworkAvailable()) {
				Toast.makeText(
						getActivity(),
						"Network is not available, please enable network first",
						Toast.LENGTH_LONG).show();
				return;
			}
			if (!isGPSAvailable()) {
				Toast.makeText(getActivity(),
						"GPS is not available, please enable GPS first",
						Toast.LENGTH_LONG).show();
			}
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new PortalsFilterFragment())
					.addToBackStack(null).commit();
		}
	};

	OnClickListener aboutListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new AboutMe())
					.addToBackStack(null).commit();
		}
	};

}
