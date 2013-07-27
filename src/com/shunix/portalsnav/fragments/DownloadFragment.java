package com.shunix.portalsnav.fragments;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
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
			UnZipHelper.unzipFile(UnZipHelper.getZipStorageDir(getActivity(), "data"), "shanghai.zip");
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
				HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
				InputStream inputStream = httpURLConnection.getInputStream();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int x;
				while((x = inputStream.read(buffer, 0, 1024)) >= 0) {
					byteArrayOutputStream.write(buffer, 0, x);
				}
				String tmpfile =UnZipHelper.getZipStorageDir(getActivity(), "data") + File.separator + "shanghai.zip";
				File file = new File(tmpfile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
				bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
				bufferedOutputStream.close();
				fileOutputStream.close();
				byteArrayOutputStream.close();
				inputStream.close();
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
