package com.example.lab_08_dialer;

import java.util.ArrayList;
import java.util.Arrays;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	Button[] buttons = new Button[12];
	Button btn_del, btn_save, btn_call;
	EditText txt_phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create a integer ids of all the resources values
		Integer[] buttonIds = { R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
				R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8,
				R.id.btn_9, R.id.btn_star, R.id.btn_hash };

		int i = 0;
		for (i = 0; i < buttonIds.length; i++) {
			buttons[i] = (Button) findViewById(buttonIds[i]);
			buttons[i].setOnClickListener(this);
		}

		txt_phone = (EditText) findViewById(R.id.txt_phone);
		
		btn_del = (Button) findViewById(R.id.btn_del);
		btn_del.setOnClickListener(this);
		
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_save.setOnClickListener(this);
		
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_call.setOnClickListener(this);

	}

	public void onClick(View v) {
		System.out.println("Got here");
		// if delete button is pressed
		if (v.equals(btn_del)) {
			String phone_number = txt_phone.getText().toString();
			if (phone_number.length() != 0) {
				phone_number = phone_number.substring(0,
						phone_number.length() - 1);
			}
			txt_phone.setText(phone_number);

		} else if (v.equals(btn_save)) {
			String phoneNumber = txt_phone.getText().toString();

			Intent intent = new Intent(Intent.ACTION_INSERT,
					ContactsContract.Contacts.CONTENT_URI);
			intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber);
			startActivity(intent);

		} else if (v.equals(btn_call)) {
			String phoneNumber = txt_phone.getText().toString();

			Intent it = new Intent(Intent.ACTION_CALL);
			it.setData(Uri.parse("tel:" + phoneNumber));
			startActivity(it);

		}

		else {
			int key = Arrays.asList(buttons).indexOf(v);

			if (key == 10) {
				txt_phone.append("*");

			} else if (key == 11) {
				txt_phone.append("#");

			} else {
				txt_phone.append(Integer.toString(key));
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
