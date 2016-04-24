package com.example.crypton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Encryptedmessage extends Activity    {
	   DatabaseHandlerForSentMsges db = new DatabaseHandlerForSentMsges(this);

	String finalmessage,phoneno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.encryptedmessage);
		Button send=(Button)findViewById(R.id.bencryptsend);
		EditText message=(EditText)findViewById(R.id.bencrypt);
	    Bundle bundle=getIntent().getExtras();
		finalmessage=bundle.getString("sms");
		phoneno=bundle.getString("phoneno");
		message.setText(finalmessage);
		
		send.setOnClickListener(new View.OnClickListener()
        {
			public void onClick(View v)
			{                
				try{
					SmsManager smsmanager=SmsManager.getDefault();
					smsmanager.sendTextMessage(phoneno, null,finalmessage, null, null);
					db.addSMS(new SentSMS(finalmessage,phoneno));

					Toast.makeText(getApplicationContext(),"SMS SENT!!!",Toast.LENGTH_LONG).show();
				}catch(Exception e)
				{
					Toast.makeText(getApplicationContext(),"Message Sending Failed",Toast.LENGTH_LONG).show();
				}
			}
        }); 
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		 super.onCreateOptionsMenu(menu);
		 MenuInflater blowup=getMenuInflater();
		 blowup.inflate(R.menu.encypted,menu);
		 return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.menuinbox:
			Intent newintent = new Intent(Encryptedmessage.this,INBOX.class); 
			Encryptedmessage.this.startActivity(newintent);
			return true;
		case R.id.menudrafts:
			Intent newintent1 = new Intent(Encryptedmessage.this,DRAFT.class); 
			Encryptedmessage.this.startActivity(newintent1);
			return true;
		case R.id.menusent:
			Intent newintent2 = new Intent(Encryptedmessage.this,SENT.class); 
			Encryptedmessage.this.startActivity(newintent2);
			return true;
		case R.id.menubluetooth:
			Intent newintent3 = new Intent(Encryptedmessage.this,BluetoothChat.class); 
			Encryptedmessage.this.startActivity(newintent3);
			return true;
		}
		return false;
	}

}
