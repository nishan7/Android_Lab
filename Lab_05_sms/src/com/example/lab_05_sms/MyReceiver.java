package com.example.lab_05_sms;

import static android.content.Context.NOTIFICATION_SERVICE;
import android.app.Notification;
//import android.app.Notification.Builder;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.*;
import android.telephony.SmsMessage;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		// Get bundle from the broadcast sms intent
		Bundle bundle = intent.getExtras();

		// A PDU is “protocol data unit”, which is the industry format for an
		// SMS message.
		Object[] pdus = (Object[]) bundle.get("pdus");
		// System.out.println(pdus);

		SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) pdus[0]);

		Bundle newBundle = new Bundle();
		newBundle.putString("number", smsMsg.getOriginatingAddress());
		newBundle.putString("message", smsMsg.getDisplayMessageBody());

		// Creating a new intent and storing it notification(Bundle) as
		// "notification"
		Intent it = new Intent(context, MainActivity.class);
		it.putExtra("notification", newBundle);

		it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);

		NotificationManager manager = (NotificationManager) context
				.getSystemService(NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		builder.setContentTitle(smsMsg.getOriginatingAddress());
		builder.setContentText(smsMsg.getDisplayMessageBody());
		builder.setSmallIcon(R.drawable.ic_launcher);

		// PendingIntent wraps the intent, the intent is released only when
		// the conditions are met. (Click on notification)
		// PendingIntents unlike Intents never die
		builder.setContentIntent(PendingIntent.getActivity(context, 1, it,
				PendingIntent.FLAG_UPDATE_CURRENT));
		// `it` is the intent that will be passed

		Notification nof = builder.build();
		manager.notify(100, nof);

		// context.startActivity(it);#

	}

}
