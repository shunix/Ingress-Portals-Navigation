package com.shunix.portalsnav.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.shunix.portalsnav.R;
import com.shunix.portalsnav.utils.AsyncHelper;
import com.shunix.portalsnav.utils.DownloadAndUnzip;

public class DownloadFragment extends Fragment {

	private Button button;
	private ProgressDialog dialog = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.download_layout, container, false);
		button = (Button)view.findViewById(R.id.button1);
		button.setOnClickListener(listener);
		return view;
	}
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dialog = ProgressDialog.show(getActivity(), "Downloading", "Please wait for a moment", true);
			DownloadAndUnzip downloadAndUnzip = new DownloadAndUnzip(getActivity(), dialog);
			AsyncHelper asyncHelper = new AsyncHelper(downloadAndUnzip);
			asyncHelper.AsyncWorkBegin();
			asyncHelper.AsyncWorkEnd();
		}
	};

}
