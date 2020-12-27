package com.example.lab_07;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cal.Calculator;

public class MainActivity extends Activity implements OnClickListener,
		ServiceConnection {
	EditText num1, num2;
	Button btn_add, btn_sub, btn_mul;
	TextView output;
	Calculator cal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		num1 = (EditText) findViewById(R.id.num1);
		num2 = (EditText) findViewById(R.id.num2);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_sub = (Button) findViewById(R.id.btn_sub);
		btn_mul = (Button) findViewById(R.id.btn_mul);
		output = (TextView) findViewById(R.id.txt_output);

		btn_add.setOnClickListener(this);
		btn_sub.setOnClickListener(this);
		btn_mul.setOnClickListener(this);

		bindService(new Intent("com.example.cal"), this, BIND_AUTO_CREATE);

	}

	//onResume()
	//onStop()
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	public void onClick(View view) {
		Integer a = Integer.parseInt(num1.getText().toString());
		Integer b = Integer.parseInt(num2.getText().toString());

		try {
			if (view.equals(btn_add)) {
				System.out.println("ADD");
				int res = cal.add(a, b);
				output.setText(String.valueOf(res));

			} else if (view.equals(btn_sub)) {
				System.out.println("Sub");
				int res = cal.sub(a, b);
				// System.out.println(res);
				output.setText(String.valueOf(res));
			} else if (view.equals(btn_mul)) {
				System.out.println("mul");
				int res = cal.mul(a, b);
				output.setText(String.valueOf(res));
			}
		} catch (RemoteException e) {

		}

	}

	@Override
	public void onServiceConnected(ComponentName arg0, IBinder arg1) {
		// TODO Auto-generated method stub
		Toast.makeText(getBaseContext(), "Service Connected", Toast.LENGTH_LONG).show();
		cal = (Calculator) Calculator.Stub.asInterface(arg1);

	}

	@Override
	public void onServiceDisconnected(ComponentName arg0) {
		// TODO Auto-generated method stub

	}

}
