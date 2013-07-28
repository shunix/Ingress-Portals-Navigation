package com.shunix.portalsnav.fragments;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.orm.androrm.QuerySet;
import com.shunix.portalsnav.R;
import com.shunix.portalsnav.models.PortalsInfo;
import com.shunix.portalsnav.utils.AsyncHelper;
import com.shunix.portalsnav.utils.DownloadAndUnzip;
import com.shunix.portalsnav.utils.KMLHandler;
import com.shunix.portalsnav.utils.UnZipHelper;

public class DownloadFragment extends Fragment {

	private Button button;
	private Button button2;
	private Button button3;
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
		button = (Button) view.findViewById(R.id.button1);
		button2 = (Button) view.findViewById(R.id.button2);
		button3 = (Button) view.findViewById(R.id.button3);
		button3.setOnClickListener(listener3);
		button2.setOnClickListener(listener2);
		button.setOnClickListener(listener);
		return view;
	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			dialog = ProgressDialog.show(getActivity(), "Downloading",
					"Please wait for a moment", true);
			DownloadAndUnzip downloadAndUnzip = new DownloadAndUnzip(
					getActivity(), dialog);
			AsyncHelper asyncHelper = new AsyncHelper(downloadAndUnzip);
			asyncHelper.AsyncWorkBegin();
			asyncHelper.AsyncWorkEnd();
		}
	};
	OnClickListener listener2 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser;
				saxParser = factory.newSAXParser();
				XMLReader reader = saxParser.getXMLReader();
				KMLHandler handler = new KMLHandler(getActivity(), "Database");
				reader.setContentHandler(handler);
				InputStream stream = new FileInputStream(new File(
						UnZipHelper.getZipStorageDir(getActivity(), "data")
								+ File.separator + "doc.kml"));
				InputSource inputSource = new InputSource(stream);
				reader.parse(inputSource);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	};
	OnClickListener listener3 = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			QuerySet<PortalsInfo> querySet = PortalsInfo.objects(getActivity());
			System.out.println(querySet.all().count());
		}
	};

}
