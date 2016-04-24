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


public class ViewMessage extends Activity    {
	DatabaseHandler db = new DatabaseHandler(this);

	String sms,phonenumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewmessage);
		TextView message=(TextView)findViewById(R.id.tv);
		Button decrypt=(Button)findViewById(R.id.bdecrypt);
		Button reply=(Button)findViewById(R.id.breply);

	    Bundle bundle=getIntent().getExtras();
		sms=bundle.getString("sms");
		phonenumber=bundle.getString("phonenumber");
		message.setText(sms);
		
		decrypt.setOnClickListener(new View.OnClickListener()
        {
	    	public void onClick(View v)
	    	{   
	    		try{
	    			Intent i = new Intent(ViewMessage.this,DecryptionCode.class); 
	    		Bundle bundle=new Bundle();
    			bundle.putString("sms",sms);
    			bundle.putString("phoneno",phonenumber);
    			i.putExtras(bundle);
	    		ViewMessage.this.startActivity(i);
	    		}catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}
	    	}
        }); 
		
		reply.setOnClickListener(new View.OnClickListener()
        {
	    	public void onClick(View v)
	    	{       
	    		try{
	    			Intent i = new Intent(ViewMessage.this,CREATEMESSAGE.class); 
	    		ViewMessage.this.startActivity(i);
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
			Intent newintent = new Intent(ViewMessage.this,INBOX.class); 
			ViewMessage.this.startActivity(newintent);
			return true;
		case R.id.menudrafts:
			Intent newintent1 = new Intent(ViewMessage.this,DRAFT.class); 
			ViewMessage.this.startActivity(newintent1);
			return true;
		case R.id.menusent:
			Intent newintent2 = new Intent(ViewMessage.this,SENT.class); 
			ViewMessage.this.startActivity(newintent2);
			return true;
		case R.id.menubluetooth:
			Intent newintent3 = new Intent(ViewMessage.this,BluetoothChat.class); 
			ViewMessage.this.startActivity(newintent3);
			return true;
		}
		return false;
	}
}
