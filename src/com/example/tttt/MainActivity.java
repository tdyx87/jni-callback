package com.example.tttt;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView      mTextView;
	 
    MainHandler   mMainHandler;
 
    static MainHandler mHandler;
    
    static
    {
        System.loadLibrary("tttt");// 声明所要调用的库名称
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextView=(TextView)findViewById(R.id.MyTextView);
        mMainHandler=new MainHandler();
        mHandler=mMainHandler;

 

        WorkThread tThread = new WorkThread ();
        new Thread(tThread).start();
	}

	 public static void myCallbackFunc(String nMsg)
	    {
	          Message tMsg=new Message();
	          Bundle tBundle=new Bundle();
	          tBundle.putString("CMD", nMsg);
	          tMsg.setData(tBundle);
	      
	          mHandler.sendMessage(tMsg);
	    }
	    //------------------------------------------------------
	    public static native String myJni(String nParam);// 对要调用的方法做本地声明 
	 
	  
	 
	    //------------------------------------------------------
	 
	    public class WorkThread implements Runnable 
	    {   
	         @Override
	         public void run()
	         {  
	              myJni("Eagle  is great"); 
	 
	         }
	    }
	    
	    class MainHandler extends Handler
	    {
	         public MainHandler(){}
	 
	         public MainHandler(Looper L)
	         {
	             super(L);
	         }
	   
	         public void handleMessage(Message nMsg)
	         {
	             super.handleMessage(nMsg);
	    
	             Bundle tBundle=nMsg.getData();
	             String tCmd=tBundle.getString("CMD");
	    
	             MainActivity.this.mTextView.setText(tCmd);
	        }
	     }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
