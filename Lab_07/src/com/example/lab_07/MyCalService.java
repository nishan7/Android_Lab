package com.example.lab_07;

import com.example.cal.Calculator;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyCalService extends Service {
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return stub; //It must return null for local and intent service
	}

	Calculator.Stub stub = new Calculator.Stub() {
		@Override
		public int sub(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			return a - b;
		}

		@Override
		public int mul(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			return a * b;
		}

		@Override
		public int add(int a, int b) throws RemoteException {
			// TODO Auto-generated method stub
			return a + b;
		}
	};
	
//	public void onCreate(){
//		// If we need to initalize anything before creating this service
//		super.onCreate();
//	}
	
//	public void onStartCommand(){
//		
//	}
	
//	public void onDestroy(){
//		
//	}
}