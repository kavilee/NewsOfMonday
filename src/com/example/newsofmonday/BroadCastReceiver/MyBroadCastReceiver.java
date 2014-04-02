package com.example.newsofmonday.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadCastReceiver extends BroadcastReceiver{
	
	Context mContext;
	public MyBroadCastReceiver(Context mContext){
		this.mContext = mContext;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		
	}
	
	public void registerAction(String action){      

	IntentFilter filter = new IntentFilter();      
	filter.addAction(action);          
	mContext.registerReceiver(this, filter);      
	}    


}
