package com.example.lab_03_provider;
import android.net.Uri;
import android.os.*;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class NotesCP extends ContentProvider{
	NotesDB dbhelper;
	SQLiteDatabase db;
	
    static final String CONTENT_URI="content://com.example.notes";
	
    @Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Implement this method to delete one or more rows
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// Implemenet this method to return MIME type of data
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues cvs) {
		db = dbhelper.getWritableDatabase();
		db.insert(NotesDB.TABLE_NAME, null, cvs);
		return null;
	}

	@Override
	public boolean onCreate() {
		dbhelper = new NotesDB(getContext(), null, null, 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] columns, String selection, String[] selectionArgs,
			String orderBy) {
		Cursor cursor = null;
		db = dbhelper.getReadableDatabase();
//		cursor = db.query(NotesDB.TABLE_NAME, columns, selection, selectionArgs, null, null, orderBy);
//		cursor = db.rawQuery("select * from notes where date = ? ", new String[]{"22/11/2020"});
		cursor = db.rawQuery("select * from notes where date = ? ", selectionArgs);
//		cursor.
		return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		return 0;
	}

}
