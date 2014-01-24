package com.example.login_json;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.mysqltest.Login;
//import com.example.mysqltest.R;
//import com.example.mysqltest.ReadComments;
//import com.example.mysqltest.Register;
//import com.example.mysqltest.Login.AttemptLogin;
 
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener 
{

	private Button b_login,b_register;
	private EditText e_userName,e_password;
	private TextView t_checkConnection;
	

   // Progress Dialog
   private ProgressDialog pDialog;

   // JSON parser class
   JSONParser jsonParser = new JSONParser();
   
   private static final String LOGIN_URL ="http://www.Yourwebsitenamehere.com/apps/login_demo/login.php";
 
   private static final String TAG_SUCCESS = "login detail is wrong";
   private static final String TAG_MESSAGE = "message";
   
	@Override
	//http://www.afreesms.com/report/1390023946/a97f749db25218d74dfc1a5959adc8e3
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e_userName=(EditText)findViewById(R.id.et_userName);
		e_password=(EditText)findViewById(R.id.et_password);
		
		t_checkConnection=(TextView)findViewById(R.id.txt_checkConnection);
		
		b_login=(Button)findViewById(R.id.btn_login);
		b_register=(Button)findViewById(R.id.btn_register);
		
		// check if you are connected or not
				if(isConnected())
				{
					t_checkConnection.setBackgroundColor(0xFF00CC00);
					t_checkConnection.setText("Connection sucessfully");
		        }
				else
				{
					t_checkConnection.setBackgroundColor(0x00FF0000);
				
					t_checkConnection.setText("Error in connection Please check your connection");
				}
				
				
				b_login.setOnClickListener(this);
				b_register.setOnClickListener(this);
				
		
	}
	
	public boolean isConnected()
	{
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		 NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		 if (networkInfo != null && networkInfo.isConnected()) 
 	    	return true;
		 else
			 return false;
	}
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
				switch (v.getId()) 
				{
				 case R.id.btn_login:
						new AttemptLogin().execute();
						Toast.makeText(this,"Login", Toast.LENGTH_SHORT).show();
					break;
				 case R.id.btn_register:
						Intent i = new Intent(this, Register.class);
						startActivity(i);
				    	Toast.makeText(this,"Registration", Toast.LENGTH_SHORT).show();
					break;

				 default:
					break;
				}
	}
	
	

	
	class AttemptLogin extends AsyncTask<String, String, String> 
	{

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;
		
        @Override
        protected void onPreExecute() 
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) 
		{
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String user_email = e_userName.getText().toString();
            String user_password = e_password.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("user_email", user_email));
                params.add(new BasicNameValuePair("user_password", user_password));
 
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);
 
                // check your log for json response
                Log.d("Run not completet","before responce");
                
                Log.d("Login attempt", json.toString());
                    
                final String JSON_STRING=json.toString();
                Log.d("your json string is",JSON_STRING);
                
                String message = json.getString("msg");
                String user_invalid ="login detail is wrong";
                String user_valid ="success";
                
                Log.d("your message is ",message);//login detail is wrong--u r invalide user
                
                Log.d("Run completeed","after responce ");
                // json success tag
              
         
                
                
                {//1
                
                if( user_invalid.equals( message ) )
                	Log.d("XXXXXX","you are invalid");
                else
                	Log.d("XXXXX","you are valid user");
                	
                }	//1
                
                {//2
                    
                    if( user_valid.equals( message ) )
                    {
                    	Log.d("Congratulation !","you are valid user");
                    	Log.d("Login Successful!", json.toString());
                    	Intent i = new Intent(MainActivity.this, Success.class);
                    	finish();
        				startActivity(i);
                    	return json.getString(TAG_MESSAGE);
                    }
                    else
                    {
                    	Log.d("Please try again !","you are invalid user");
                    	//Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    	Log.d("Login Fail!", json.toString());
                    	Intent i = new Intent(MainActivity.this, Fail.class);
                    	finish();
        				startActivity(i);
                    	return json.getString(TAG_MESSAGE);
                    }
               }	//2
                	
                
                
                /*
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) 
                {
                	Log.d("Login Successful!", json.toString());
                	Intent i = new Intent(MainActivity.this, Success.class);
                	finish();
    				startActivity(i);
                	return json.getString(TAG_MESSAGE);
                }
                else
                {
                	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
               
                	return json.getString(TAG_MESSAGE);
                	
                }
                */
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}

	}
	
}


/*
    public boolean isConnected(){
    	ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
    	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	    if (networkInfo != null && networkInfo.isConnected()) 
    	    	return true;
    	    else
    	    	return false;	
    }
   
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	 //php login script location:
   
   //localhost :  
   //testing on your device
   //put your local ip instead,  on windows, run CMD > ipconfig
   //or in mac's terminal type ifconfig and look for the ip under en0 or en1
  // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";
   
   //testing on Emulator:
   //private static final String LOGIN_URL = "http://10.0.2.2:1234/webservice/login.php";
   //"http://www.Yourwebsitenamehere.com/apps/login_demo/login.php?user_email=ttttt@gmail.com&&user_password=test"
  // private static final String LOGIN_URL ="http://www.Yourwebsitenamehere.com/apps/login_demo/login.php?user_email=ttttt@gmail.com&&user_password=test";
  
  
  
    //?user_email=ttttt@gmail.com&&user_password=test"
 //testing from a real server:
   //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";
   
   //JSON element ids from repsonse of php script:
*/
