package com.example.crypton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Decryptedmessage extends Activity    {
	
	String finalmessage,phoneno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.decryptedmessage);
		TextView message=(TextView)findViewById(R.id.dec);
	    Bundle bundle=getIntent().getExtras();
		finalmessage=bundle.getString("sms");
		phoneno=bundle.getString("phoneno");
		message.setText(finalmessage);
		
		
		
	}
}
