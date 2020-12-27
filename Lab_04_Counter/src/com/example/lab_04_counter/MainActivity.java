package com.example.lab_04_counter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Runnable;

public class MainActivity extends Activity implements OnClickListener, Runnable{
	Button btn_start, btn_stop;
	TextView txt_counter;
	
	int count = 0;
	boolean running = false;
	Thread counterThread;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_stop = (Button) findViewById(R.id.btn_stop);
		txt_counter = (TextView) findViewById(R.id.txt_counter);

		btn_start.setOnClickListener(this);
		btn_stop.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.equals(btn_start)) {
			running = true;
			counterThread = new Thread(this);
			counterThread.start();
		} else {
			running = false;

		}

	}
	
	Handler handle = new Handler(){
		public void handleMessage(Message m){
			txt_counter.setText(String.valueOf(m.what));
		}
	};

	@Override
	public void run(){
		while(running & count < 100){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handle.sendEmptyMessage(count);
			count++;	
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
