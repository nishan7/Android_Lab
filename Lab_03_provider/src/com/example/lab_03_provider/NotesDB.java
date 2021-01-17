package com.example.lab_03_provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDB  extends SQLiteOpenHelper{
	public NotesDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DBNAME, factory, 1);
	}


	static String DBNAME = "notes.db";
	static String TABLE_NAME = "notes";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "+TABLE_NAME + " (date TEXT, note TEXT)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}


}

