package com.example.lab_01;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btn_save, btn_search;
	EditText txt_id, txt_name, txt_age, txt_address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_save = (Button) findViewById(R.id.btn_save);
		btn_search = (Button) findViewById(R.id.btn_search);
		btn_save.setOnClickListener(this);
		btn_search.setOnClickListener(this);

		txt_id = (EditText) findViewById(R.id.txt_id);
		txt_name = (EditText) findViewById(R.id.txt_name);
		txt_age = (EditText) findViewById(R.id.txt_age);
		txt_address = (EditText) findViewById(R.id.txt_address);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		

		MyDatabase db = new MyDatabase(this, MyDatabase.DATABASE_NAME, null, 1);
		
		String id = txt_id.getText().toString();
		String name = txt_name.getText().toString();
		String age = txt_age.getText().toString();
		String address = txt_address.getText().toString();

		if (view.equals(btn_save)) {
			SQLiteDatabase dbwriter = db.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("id", id);
			cv.put("name", name);
			cv.put("age", age);
			cv.put("address", address);
			dbwriter.insert("Employee", null, cv);
			dbwriter.close();
			
			Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
			
			
		} else {
//			System.out.println(id + name + age + address);
			Intent it = new Intent(this, SearchActivity.class);
			startActivity(it);

		}
	}
}
