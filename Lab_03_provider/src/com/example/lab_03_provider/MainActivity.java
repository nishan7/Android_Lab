package com.example.lab_03_provider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener{
	EditText txt_note, txt_date;
	Button btn_save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txt_note = (EditText) findViewById(R.id.txt_note);
		txt_date = (EditText) findViewById(R.id.txt_date);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_save.setOnClickListener(this);
	
	}
	
	public void onClick(View v){
		String date = txt_date.getText().toString();
		String note  = txt_note.getText().toString();
		ContentValues cvs = new ContentValues();
		cvs.put("date", date);
		cvs.put("note", note);
		
		getContentResolver().insert(Uri.parse(NotesCP.CONTENT_URI), cvs);
		Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
