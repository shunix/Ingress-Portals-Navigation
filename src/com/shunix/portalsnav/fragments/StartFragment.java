package com.shunix.portalsnav.fragments;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.utils.AsyncHelper;
import com.shunix.portalsnav.utils.DownloadAndUnzip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartFragment extends Fragment {

	private Button updateButton;
	private Button startButton;
	private Button aboutButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
			DownloadAndUnzip downloadAndUnzip = new DownloadAndUnzip(
					getActivity());
			AsyncHelper asyncHelper = new AsyncHelper(downloadAndUnzip);
			asyncHelper.AsyncWorkBegin();
			asyncHelper.AsyncWorkEnd();
		}
	};

	OnClickListener startListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new NavigationFragment())
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
