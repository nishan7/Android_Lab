package com.example.lab_01;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyDatabase extends SQLiteOpenHelper {
	public static String DATABASE_NAME="Employee.db";
	public static String EMPLOYEE_TABLE="employee";
	public MyDatabase(Context context, String name, CursorFactory factory, int version){
		super(context, name, factory, version);
		
	}
	public void onCreate(SQLiteDatabase db){
		db.execSQL("create table employee (id TEXT, name TEXT, age TEXT, address TEXT)");
		
		
	}
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
		//
		
	}

}
