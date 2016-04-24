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


public class ViewDraftMsg extends Activity    {
	DatabaseHandlerForDraft db = new DatabaseHandlerForDraft(this);

	String finalmessage,phoneno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdraftmsg);
		TextView message=(TextView)findViewById(R.id.tv);
		Button Edit=(Button)findViewById(R.id.bedit);
		
		Button Delete=(Button)findViewById(R.id.bdelete);

	    Bundle bundle=getIntent().getExtras();
		finalmessage=bundle.getString("sms");
		message.setText(finalmessage);
		
		Edit.setOnClickListener(new View.OnClickListener()
        {
	    	public void onClick(View v)
	    	{       
	    		try{
	    			Intent i = new Intent(ViewDraftMsg.this,CREATEMESSAGEfromDRAFT.class); 
	    		Bundle bundle=new Bundle();
    			bundle.putString("sms",finalmessage);
    			bundle.putString("phoneno",phoneno);
    			i.putExtras(bundle);
    			ViewDraftMsg.this.startActivity(i);
	    		}catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}
	    	}
        }); 
		
		Delete.setOnClickListener(new View.OnClickListener()
        {
	    	public void onClick(View v)
	    	{       
	    		try{
	    			db.deleteSMS(new DraftSMS(finalmessage,phoneno)); 
	    			db.close();

	    			Intent i = new Intent(ViewDraftMsg.this,DRAFT.class); 
		    		ViewDraftMsg.this.startActivity(i);
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
			Intent newintent = new Intent(ViewDraftMsg.this,INBOX.class); 
			ViewDraftMsg.this.startActivity(newintent);
			return true;
		case R.id.menudrafts:
			Intent newintent1 = new Intent(ViewDraftMsg.this,DRAFT.class); 
			ViewDraftMsg.this.startActivity(newintent1);
			return true;
		case R.id.menusent:
			Intent newintent2 = new Intent(ViewDraftMsg.this,SENT.class); 
			ViewDraftMsg.this.startActivity(newintent2);
			return true;
		case R.id.menubluetooth:
			Intent newintent3 = new Intent(ViewDraftMsg.this,BluetoothChat.class); 
			ViewDraftMsg.this.startActivity(newintent3);
			return true;
		}
		return false;
	}
}
