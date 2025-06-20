package com.jlkj.newsofmonday.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.newsofmonday.R;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
	private String url = "http://192.168.1.104/news/client/article";
	private String result;
	private List<Map<String,Object>> list = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE); // Use a NoActionBar theme instead
		setContentView(R.layout.activity_main);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String json_result = connctWork(url);
				Log.d("----------------",json_result);
				JSONArrayParse(json_result);
				
			}
		}).start();
		
	}
	
	//联网
	public String connctWork(String url){
		HttpGet get = new HttpGet(url);
        //新建HttpClient对象 
        HttpClient httpClient = new DefaultHttpClient();
        try {
			HttpResponse response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode() == 200){
				byte[] bResult = EntityUtils.toByteArray(response.getEntity()); 
				if(bResult!=null){
					result = new String(bResult, "utf-8"); 
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//JSON解析
	public void JSONArrayParse(String json){
		try {
			list = new ArrayList<Map<String,Object>>();
			JSONObject jsonObject = new JSONObject(json);
			String jsonArray = jsonObject.getString("data");
			System.out.println(jsonArray);
			JSONArray arr = new JSONArray(jsonArray);
			for (int i = 0; i < arr.length(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				JSONObject temp = (JSONObject)arr.get(i);
				String id = temp.getString("id");
				String pic = temp.getString("pic");
				Bitmap bitmap = getImage(pic);
				String tip = temp.getString("tip");
				map.put("id", id);
				map.put("bitmap", bitmap);
				map.put("tip", tip);
				list.add(map);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//下载图片
	public Bitmap getImage(String imgUrl){
		URL fileURL = null;
		Bitmap bitmap = null;
		try {
			fileURL = new URL(imgUrl);
		} catch (MalformedURLException err) {
			err.printStackTrace();
		}
		try{
			HttpURLConnection conn = (HttpURLConnection) fileURL.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			int length = (int) conn.getContentLength();
			if (length != -1) {
				byte[] imgData = new byte[length];
				byte[] buffer = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(buffer)) > 0) {
					System.arraycopy(buffer, 0, imgData, destPos, readLen);
					destPos += readLen;
				}
				bitmap = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
				return bitmap;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
