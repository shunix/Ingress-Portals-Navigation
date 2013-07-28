package com.shunix.portalsnav.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;

public class KMLHandler extends DefaultHandler {

	private boolean corState;
	private boolean nameState;
	private String[] strings;
	private DatabaseManager dbManager;

	public KMLHandler(Context context, String dbName) {
		corState = false;
		nameState = false;
		strings = new String[2];
		dbManager = new DatabaseManager(context, dbName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String str = String.valueOf(ch, start, length);
		if (nameState == true) {
			// The first name element is "shanghai" while it's not a portal
			if (str == "shanghai") {
				return;
			}
			if (str == null) {
				strings[0] = "";
			}
			strings[0] = str;
			nameState = false;
		}
		if (corState == true) {
			strings[1] = str;
			//System.out.println(str);
			int firstIndex = str.indexOf(',');
			int lastIndex = str.lastIndexOf(',');
			if(firstIndex == -1) {
				corState = false;
				return;
			}
			if(lastIndex == firstIndex) {
				corState = false;
				return;
			}
			//System.out.println(strings[0] + " " + firstIndex + " " + lastIndex);
			double lat = Double.parseDouble(str.substring(0, firstIndex));
			double lng = Double.parseDouble(str.substring(firstIndex + 1,
					lastIndex));
			System.out.println(strings[0] + " " + lat + " " + lng);
			dbManager.savePortalsInfo(strings[0], lat, lng);
			corState = false;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		dbManager.endTransction();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (uri == "http://www.opengis.net/kml/2.2" && qName == "coordinates") {
			corState = false;
		}
		if (uri == "http://www.opengis.net/kml/2.2" && qName == "name") {
			nameState = false;
		}
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (uri == "http://www.opengis.net/kml/2.2" && qName == "coordinates") {
			corState = true;
		}
		if (uri == "http://www.opengis.net/kml/2.2" && qName == "name") {
			nameState = true;
		}
	}

}
