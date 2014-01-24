package com.example.login_json;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Fail extends Activity{
	Button bBack;

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fail);
		Toast.makeText(Fail.this, "Login fail please try again !", Toast.LENGTH_SHORT).show();
		Toast.makeText(Fail.this, "Press on Back !", Toast.LENGTH_LONG).show();
		
		bBack=(Button)findViewById(R.id.btnBack);
		
		bBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(Fail.this, MainActivity.class);
            	startActivity(i);
			}
		});
	}
}
