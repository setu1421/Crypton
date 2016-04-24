package com.example.crypton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ViewSentMsg extends Activity    {
	DatabaseHandlerForSentMsges db = new DatabaseHandlerForSentMsges(this);

	String finalmessage,phoneno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewsentmsg);
		TextView message=(TextView)findViewById(R.id.tv);
		
		Button Delete=(Button)findViewById(R.id.bdelete);

	    Bundle bundle=getIntent().getExtras();
		finalmessage=bundle.getString("sms");
		phoneno=bundle.getString("phoneno");

		message.setText(finalmessage);
		
	
		
		Delete.setOnClickListener(new View.OnClickListener()
        {
	    	public void onClick(View v)
	    	{       
	    		try{
	    			db.deleteSMS(new SentSMS(finalmessage,phoneno)); 
	    			db.close();

	    			Intent i = new Intent(ViewSentMsg.this,SENT.class); 
	    			ViewSentMsg.this.startActivity(i);
	    		}catch(Exception e)
	    		{
	    			e.printStackTrace();
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
			Intent newintent = new Intent(ViewSentMsg.this,INBOX.class); 
			ViewSentMsg.this.startActivity(newintent);
			return true;
		case R.id.menudrafts:
			Intent newintent1 = new Intent(ViewSentMsg.this,DRAFT.class); 
			ViewSentMsg.this.startActivity(newintent1);
			return true;
		case R.id.menusent:
			Intent newintent2 = new Intent(ViewSentMsg.this,SENT.class); 
			ViewSentMsg.this.startActivity(newintent2);
			return true;
		case R.id.menubluetooth:
			Intent newintent3 = new Intent(ViewSentMsg.this,BluetoothChat.class); 
			ViewSentMsg.this.startActivity(newintent3);
			return true;
		}
		return false;
	}
}
