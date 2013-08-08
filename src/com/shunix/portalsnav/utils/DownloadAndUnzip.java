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
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DownloadAndUnzip implements AsyncInterface {

	private Activity context;
	private ProgressDialog dialog;
	private SharedPreferences sharedPreferences;
	private boolean beijing;
	private boolean chengdu;
	private boolean chongqing;
	private boolean guangzhou;
	private boolean shanghai;
	private boolean xian;
	private boolean wuhan;

	public DownloadAndUnzip(Activity context) {
		this.context = context;
		sharedPreferences = context.getSharedPreferences("PortalsNav", 0);
		beijing = sharedPreferences.getBoolean("Beijing", false);
		chengdu = sharedPreferences.getBoolean("Chengdu", false);
		chongqing = sharedPreferences.getBoolean("Chongqing", false);
		guangzhou = sharedPreferences.getBoolean("Guangzhou", false);
		shanghai = sharedPreferences.getBoolean("Shanghai", false);
		xian = sharedPreferences.getBoolean("Xian", false);
		wuhan = sharedPreferences.getBoolean("Wuhan", false);
	}

	@Override
	public void endWork() {
		try {
			SQLHelper helper = new SQLHelper(context, "Database");
			SQLiteDatabase database = helper.getWritableDatabase();
			// Clear database on every update
			try {
				database.delete("PortalsInfo", null, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			database.close();
			helper.close();
			if(beijing) {
				UnZipHelper.unzipFile(
						UnZipHelper.getZipStorageDir(context, "data"),
						"beijing.zip");
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
				stream.close();
			}
			if(chengdu) {
				UnZipHelper.unzipFile(
						UnZipHelper.getZipStorageDir(context, "data"),
						"chengdu.zip");
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
				stream.close();
			}
			if(chongqing) {
				UnZipHelper.unzipFile(
						UnZipHelper.getZipStorageDir(context, "data"),
						"chongqing.zip");
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
				stream.close();
			}
			if(guangzhou) {
				UnZipHelper.unzipFile(
						UnZipHelper.getZipStorageDir(context, "data"),
						"guangzhou.zip");
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
				stream.close();
			}
			if (shanghai) {
				UnZipHelper.unzipFile(
						UnZipHelper.getZipStorageDir(context, "data"),
						"shanghai.zip");
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
				stream.close();
			}
			if(wuhan) {
				UnZipHelper.unzipFile(
						UnZipHelper.getZipStorageDir(context, "data"),
						"wuhan.zip");
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
				stream.close();
			}
			if(xian) {
				UnZipHelper.unzipFile(
						UnZipHelper.getZipStorageDir(context, "data"),
						"xian.zip");
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
				stream.close();
			}
			context.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					dialog.dismiss();
					Toast.makeText(context,
							"Database has been successfully updated",
							Toast.LENGTH_LONG).show();
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
			if (beijing) {
				URL url = new URL("http://s.binux.me/ingress/beijing.kmz");
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
						+ File.separator + "beijing.zip";
				File file = new File(tmpfile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						fileOutputStream);
				bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
				bufferedOutputStream.close();
				fileOutputStream.close();
				byteArrayOutputStream.close();
				inputStream.close();
			}
			if (chengdu) {
				URL url = new URL("http://s.binux.me/ingress/cengdu.kmz");
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
						+ File.separator + "chengdu.zip";
				File file = new File(tmpfile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						fileOutputStream);
				bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
				bufferedOutputStream.close();
				fileOutputStream.close();
				byteArrayOutputStream.close();
				inputStream.close();
			}
			if (chongqing) {
				URL url = new URL("http://s.binux.me/ingress/congqing.kmz");
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
						+ File.separator + "chongqing.zip";
				File file = new File(tmpfile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						fileOutputStream);
				bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
				bufferedOutputStream.close();
				fileOutputStream.close();
				byteArrayOutputStream.close();
				inputStream.close();
			}
			if (guangzhou) {
				URL url = new URL("http://s.binux.me/ingress/guangzhou.kmz");
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
						+ File.separator + "guangzhou.zip";
				File file = new File(tmpfile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						fileOutputStream);
				bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
				bufferedOutputStream.close();
				fileOutputStream.close();
				byteArrayOutputStream.close();
				inputStream.close();
			}
			if (shanghai) {
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
			}
			if (xian) {
				URL url = new URL("http://s.binux.me/ingress/xian.kmz");
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
						+ File.separator + "xian.zip";
				File file = new File(tmpfile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						fileOutputStream);
				bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
				bufferedOutputStream.close();
				fileOutputStream.close();
				byteArrayOutputStream.close();
				inputStream.close();
			}
			if (wuhan) {
				URL url = new URL("http://s.binux.me/ingress/wuhan.kmz");
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
						+ File.separator + "wuhan.zip";
				File file = new File(tmpfile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						fileOutputStream);
				bufferedOutputStream.write(byteArrayOutputStream.toByteArray());
				bufferedOutputStream.close();
				fileOutputStream.close();
				byteArrayOutputStream.close();
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
