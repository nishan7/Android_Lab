package com.example.lab_06;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.Button;
import android.support.v4.app.NotificationCompat;


public class MainActivity extends Activity  implements View.OnClickListener{
	Button btn_start, btn_stop;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        
        
    }
    
    public void onClick(View v){
    	Intent it = new Intent(this, MyService.class);
    	if (v.equals(btn_start)){
    		System.out.println("Btn_start");
    		startService(it);
    		
    	}else if (v.equals(btn_stop)){
    		System.out.println("Btn_stop");
    		stopService(it);
    	}
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
