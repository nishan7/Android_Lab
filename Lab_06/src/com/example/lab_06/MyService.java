package com.example.lab_06;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;

public class MyService extends Service {
	boolean running = false; // Determines when to stop the counter
	MyThread ctrThread;

	public void onCreate() {
		// The system will invoke this method when the service is created
		// initially using onStartCommand() or onBind()
		// methods to do one-time setup procedures. If the service is running it
		// won't be called

		super.onCreate();
		Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();

		// Start notification counter
		running = true;
		ctrThread = new MyThread();
		ctrThread.start();

	}

	public int onStartCommand(Intent intent, int flag, int startid) {

		// If in case this service is terminated it won't be automatically
		// restarted until it is called

		System.out.println("onStartCommand top");
		super.onStartCommand(intent, flag, startid);

		Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

		return Service.START_NOT_STICKY;

	}

	public void onDestroy() {
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
		running = false;
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// The system will invoke this method when another component wants to
		// bind with the service by calling bindService()
		return null;
	}

	Handler handler = new Handler() {
		@SuppressLint("NewApi") public void handleMessage(Message m) {
			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
			// Notification.Builder is not supported on API lower than 11
			
			builder.setContentTitle("From Service");
			builder.setContentText(""+m.what);
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setContentIntent(PendingIntent.getActivity(getBaseContext(), 1, new Intent(getBaseContext(),MainActivity.class),1));
			Notification nof = builder.build();
			manager.notify(100, nof);
			
		}
	};

	class MyThread extends Thread {
		public void run() {
			int ctr = 0;
			while (running) {
				ctr++;
				handler.sendEmptyMessage(ctr);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

		}

	}
}
