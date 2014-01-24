package com.example.login_json;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Success extends Activity{
	Button bBack;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.success);
		Toast.makeText(Success.this, "Login Successfully !", Toast.LENGTH_SHORT).show();
		Toast.makeText(Success.this, "Press on Back !", Toast.LENGTH_LONG).show();
		
		bBack=(Button)findViewById(R.id.btnBack);
		
		bBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(Success.this, MainActivity.class);
            	startActivity(i);
			}
		});
	}
}
