package com.example.lab_02_notes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.*;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity implements OnClickListener {
	Button btn_save, btn_open, btn_new_file;
	EditText text;
	TextView txt_filepath;

	String filepath = Environment.getExternalStorageDirectory()
			.getAbsolutePath();
	String fname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_save = (Button) findViewById(R.id.btn_save);
		btn_open = (Button) findViewById(R.id.btn_open);
		btn_new_file = (Button) findViewById(R.id.btn_new_file);
		txt_filepath = (TextView) findViewById(R.id.txt_filepath);
		text = (EditText) findViewById(R.id.txt_content);

		btn_save.setOnClickListener(this);
		btn_open.setOnClickListener(this);
		btn_new_file.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		if (view.equals(btn_save)) {
			Toast.makeText(getBaseContext(), filepath + "    " + fname,
					Toast.LENGTH_LONG).show();
			writeToFile();
		} else if (view.equals(btn_open)) {
			readFromFile();
		} else if (view.equals(btn_new_file)) {
			createDialog();
		}

	}

	public void createDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View dialogView = getLayoutInflater().inflate(R.layout.dailog,
				null);
		builder.setView(dialogView);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dailoginterface, int i) {
				System.out.println("onclick");
				EditText txt_filename = (EditText) dialogView
						.findViewById(R.id.txt_filename);
				fname = txt_filename.getText().toString();
				System.out.println("after onclick");
				try {
					File file = new File(filepath, fname.trim());
					file.createNewFile();
					Toast.makeText(getBaseContext(),
							file.getAbsolutePath().toString(),
							Toast.LENGTH_LONG).show();
					txt_filepath.setText(file.getAbsolutePath());

				} catch (IOException e) {
					Toast.makeText(getBaseContext(), e.getLocalizedMessage(),
							Toast.LENGTH_LONG).show();
				}

			}
		});

		AlertDialog dg = builder.create();
		dg.show();

	}

	public void writeToFile() {
		try {
			File file = new File(filepath, fname);
			FileWriter fw = new FileWriter(file);
			fw.write(text.getText().toString());
			fw.close();
			Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();

		} catch (IOException e) { // FileNotFoundException
			e.printStackTrace();
			System.out.println(e);
			Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),
					Toast.LENGTH_LONG).show();
		}

	}
	
	public void readFromFile(){
		try{
			File file = new File(filepath, fname);
			FileReader fr = new FileReader(file);
			
			char[] buf = new char[1000];
			fr.read(buf);
			text.setText(new String(buf));
			Toast.makeText(getApplicationContext(),new String(buf),
					Toast.LENGTH_LONG).show();
			
			
		}catch(Exception e){
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.getLocalizedMessage(),
					Toast.LENGTH_LONG).show();
		}
		
		
		}
	

}
