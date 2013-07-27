package com.shunix.portalsnav.fragments;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shunix.portalsnav.R;
import com.shunix.portalsnavn.utils.UnZipHelper;

public class DownloadFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Thread thread = new GetFile();
			thread.start();
			thread.join();
			Toast.makeText(getActivity(), "Download complete",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class GetFile extends Thread {

		@Override
		public void run() {
			super.run();
			try {
				URL url = new URL("http://s.binux.me/ingress/shanghai.kmz");
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				urlConnection.connect();
				InputStream inputStream = urlConnection.getInputStream();
				byte[] buffer = new byte[2048000];
				@SuppressWarnings("unused")
				int numread;
				while ((numread = inputStream.read(buffer)) != -1);
				inputStream.close();
				String tmpfile =UnZipHelper.getZipStorageDir(getActivity(), "data") + File.separator + "shanghai.kmz";
				System.out.println(tmpfile);
				File file = new File(tmpfile);
				file.createNewFile();
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(buffer);
				System.out.println(buffer);
				fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.download_layout, container, false);
		return view;
	}

}
