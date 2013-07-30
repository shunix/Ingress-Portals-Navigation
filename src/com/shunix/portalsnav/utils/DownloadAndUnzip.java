package com.shunix.portalsnav.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;

public class DownloadAndUnzip implements AsyncInterface {

	private Activity context;
	private ProgressDialog dialog;
	public DownloadAndUnzip(Activity context) {
		this.context = context;
	}
	@Override
	public void endWork() {
		UnZipHelper.unzipFile(UnZipHelper.getZipStorageDir(context, "data"), "shanghai.zip");
		context.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				dialog.dismiss();
			}
		});
	}

	@Override
	public void beginWork() {
		try {
			context.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					dialog = ProgressDialog.show(context, "Downloading",
							"Please wait for a moment", true);
				}
			});
			URL url = new URL("http://s.binux.me/ingress/shanghai.kmz");
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			InputStream inputStream = httpURLConnection.getInputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int x;
			while((x = inputStream.read(buffer, 0, 1024)) >= 0) {
				byteArrayOutputStream.write(buffer, 0, x);
			}
			String tmpfile =UnZipHelper.getZipStorageDir(context, "data") + File.separator + "shanghai.zip";
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
