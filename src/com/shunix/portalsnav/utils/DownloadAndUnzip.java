package com.shunix.portalsnav.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DownloadAndUnzip implements AsyncInterface {

	private Activity context;
	private ProgressDialog dialog;

	public DownloadAndUnzip(Activity context) {
		this.context = context;
	}

	@Override
	public void endWork() {
		try {
			UnZipHelper.unzipFile(
					UnZipHelper.getZipStorageDir(context, "data"),
					"shanghai.zip");
			SQLHelper helper = new SQLHelper(context, "Database");
			SQLiteDatabase database = helper.getWritableDatabase();
			database.delete("PortalsInfo", null, null);
			database.close();
			helper.close();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser;
			saxParser = factory.newSAXParser();
			XMLReader reader = saxParser.getXMLReader();
			KMLHandler handler = new KMLHandler(context, "Database");
			reader.setContentHandler(handler);
			InputStream stream = new FileInputStream(new File(
					UnZipHelper.getZipStorageDir(context, "data")
							+ File.separator + "doc.kml"));
			InputSource inputSource = new InputSource(stream);
			reader.parse(inputSource);
			context.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					dialog.dismiss();
					Toast.makeText(context, "Database has been successfully updated", Toast.LENGTH_LONG).show();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			InputStream inputStream = httpURLConnection.getInputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int x;
			while ((x = inputStream.read(buffer, 0, 1024)) >= 0) {
				byteArrayOutputStream.write(buffer, 0, x);
			}
			String tmpfile = UnZipHelper.getZipStorageDir(context, "data")
					+ File.separator + "shanghai.zip";
			File file = new File(tmpfile);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					fileOutputStream);
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
