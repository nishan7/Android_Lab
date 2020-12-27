package com.example.lab_01;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity implements OnClickListener {
	EditText txt_id;
	Button btn_main_search;
	TextView output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		btn_main_search = (Button) findViewById(R.id.btn_main_search);
		btn_main_search.setOnClickListener(this);

		txt_id = (EditText) findViewById(R.id.txt_id);
		output = (TextView) findViewById(R.id.output);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		 
		String id = txt_id.getText().toString();
		MyDatabase db = new MyDatabase(this, MyDatabase.DATABASE_NAME, null, 1);
		SQLiteDatabase db_reader = db.getReadableDatabase();
		String[] columns = { "id", "name", "age", "address" };
		String where = "id=?";
		String[] value = new String[] { id.trim() };
		Cursor cur = db_reader.query(MyDatabase.EMPLOYEE_TABLE, columns, where,
				value, null, null, null);

		output.setText("");
		if (cur.moveToNext()) {
			System.out.println("klsdfj");
			String empid = cur.getString(0);
			String name = cur.getString(1);
			String age = cur.getString(2);
			String address = cur.getString(3);

			output.append(empid + " " + name + " " + age + " " + address + "\n");
			System.out.println(name);
		} else {
			Toast.makeText(this, "No id exists", Toast.LENGTH_LONG);
		}
	}

}
