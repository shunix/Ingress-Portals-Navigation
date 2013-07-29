package com.shunix.portalsnav.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

	public SQLHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public SQLHelper(Context context, String name, int version)
	{
		this(context, name, null, version);
	}
	
	public SQLHelper(Context context, String name)
	{
		this(context, name, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		return;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		return;
	}

}
