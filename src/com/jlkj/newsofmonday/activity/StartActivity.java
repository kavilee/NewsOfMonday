package com.jlkj.newsofmonday.activity;

import com.example.newsofmonday.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;

/**
 * 启动界面
 * @author kavilee11
 *
 */

public class StartActivity extends Activity{
	
	private RelativeLayout myRelativeLayout;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		
		myRelativeLayout = (RelativeLayout)findViewById(R.id.myR);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
					
					System.out.println(myRelativeLayout.getHeight());
					System.out.println(myRelativeLayout.getWidth());
					
					startActivity(new Intent(StartActivity.this,MainActivity.class));
					StartActivity.this.finish();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
				
	}

}