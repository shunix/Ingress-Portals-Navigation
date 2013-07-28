package com.shunix.portalsnav.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class KMLHandler extends DefaultHandler {

	private boolean state = false;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {	
		super.characters(ch, start, length);
		String str = String.valueOf(ch, start, length);
		if(state == true) {
			System.out.println(str);
			state = false;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if(uri == "http://www.opengis.net/kml/2.2" && qName == "coordinates") {
			state = false;
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
		if(uri == "http://www.opengis.net/kml/2.2" && qName == "coordinates") {
			state = true;
		}
	}

}
