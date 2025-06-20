package com.jlkj.newsofmonday.activity;

import com.example.newsofmonday.R;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 启动界面
 * @author kavilee11
 *
 */

public class StartActivity extends AppCompatActivity{
	
	private RelativeLayout myRelativeLayout; 
	private ImageView myImageView; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// It's generally recommended to request window features before super.onCreate()
        // or set a NoActionBar theme in the manifest or styles.xml.
        // For AppCompatActivity, this should ideally be handled by a NoActionBar theme.
		// requestWindowFeature(Window.FEATURE_NO_TITLE); // This might cause issues with AppCompatActivity if not using a NoActionBar theme.
		setContentView(R.layout.activity_start);
		
		myRelativeLayout = (RelativeLayout)findViewById(R.id.myR);
		myImageView = (ImageView)findViewById(R.id.imageView1);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
					
					System.out.println(myRelativeLayout.getHeight());
					System.out.println(myRelativeLayout.getWidth());
					
					startActivity(new Intent(StartActivity.this, LoginActivity.class));
					StartActivity.this.finish();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
				
	}

}