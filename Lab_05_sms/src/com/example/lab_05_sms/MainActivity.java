package com.example.lab_05_sms;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView txt_number, txt_message;
		txt_number = (TextView) findViewById(R.id.txt_number);
		txt_message = (TextView) findViewById(R.id.txt_message);



		// As notification is stored as bundle as name "notification"
		Bundle notification = getIntent().getBundleExtra("notification");
		System.out.println(notification);

		if (notification != null) {
//			System.out.println(notification.getString("number"));
//			System.out.println(notification.getString("message"));	
			txt_number.setText(notification.getString("number"));
			txt_message.setText(notification.getString("message"));

		}

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
