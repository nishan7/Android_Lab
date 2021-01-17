package com.example.lab_03_client;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	static final String CONTENT_URI = "content://com.example.notes";
	TextView txt_res;
	EditText txt_date;
	Button btn_search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txt_res = (TextView) findViewById(R.id.txt_res);
		txt_date = (EditText) findViewById(R.id.txt_date);
		btn_search = (Button) findViewById(R.id.btn_search);
		btn_search.setOnClickListener(this);

	}

	public void onClick(View v) {
		String date = txt_date.getText().toString();

//		String where = "date=?";
		// Cursor
		// cursor=getContentResolver().query(Uri.parse("content://com.example.notes")
		// ,new String[]{"date","note"},where, new String[]{date},null);

		Cursor cursor = getContentResolver().query(
				Uri.parse(CONTENT_URI), null, null,
				new String[] { date }, null);

//		Cursor cursor = getContentResolver().query(Uri.parse(CONTENT_URI),
//				new String[] { date }, null, null, null);

		if (cursor == null) {
			return;
		}
		System.out.println(cursor.getCount());

		String output = "";
		cursor.moveToFirst();
		while (cursor.moveToNext()) {
			// output = + cursor.getString(1) + "\n";
			String date1 = cursor.getString(0);
			String note = cursor.getString(1);
			output += date1 + note + "\n";
			System.out.println(output);
		}
		cursor.close();
		txt_res.setText(output);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
