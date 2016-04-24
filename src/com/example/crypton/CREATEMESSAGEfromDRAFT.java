package com.example.crypton;
 
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.PendingIntent;

public class CREATEMESSAGEfromDRAFT extends Activity {
	   DatabaseHandlerForSentMsges db = new DatabaseHandlerForSentMsges(this);

    private static final String TAG=CREATEMESSAGE.class.getSimpleName();
    private final static int REQ_CODE=1;
    private Uri uricontact;
    private String contactID;
	
	Intent i;
	EditText textphoneno;
	 EditText textsms;
	 String msg;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createmessage);
		textphoneno=(EditText)findViewById(R.id.editTextPhoneNo);
		 textsms=(EditText)findViewById(R.id.editTextSMS);
		Button encrypt=(Button)findViewById(R.id.bencrypt);
		Button send=(Button)findViewById(R.id.buttonSend);
		
		 Bundle bundle=getIntent().getExtras();
			msg=bundle.getString("sms");
			textsms.setText(msg);

		
		
	    encrypt.setOnClickListener(new View.OnClickListener()
        {
	    	 public void onClick(View v)
	    	 {                
	    		 try
	    		 {
	    			 Intent i = new Intent(CREATEMESSAGEfromDRAFT.this,Encrypt.class); 
	    			 String phoneno=textphoneno.getText().toString();
	    			 String sms=textsms.getText().toString();  
	    			 Bundle bundle=new Bundle();
	    			 bundle.putString("sms",sms);
	    			 bundle.putString("phoneno",phoneno);
	    			 i.putExtras(bundle);
	    			 CREATEMESSAGEfromDRAFT.this.startActivity(i);
	    		 }catch(Exception e)
	    		 {
	    			 e.printStackTrace();
	    		 }
	    	 }
       });
	    
	    
	    
	   send.setOnClickListener(new View.OnClickListener()
       {
		   public void onClick(View v)
		   {                
			  String phoneno=textphoneno.getText().toString();
			  
			   String sms=textsms.getText().toString();   

			   if (phoneno.length()>0 && sms.length()>0)                
			   {
				   SmsManager s = SmsManager.getDefault();
				   s.sendTextMessage(phoneno, null, sms, null, null);
					db.addSMS(new SentSMS(sms,phoneno));

				   Toast.makeText(getApplicationContext(),"SMS SENT!!!",Toast.LENGTH_LONG).show();

			   }
			   else
				   Toast.makeText(getBaseContext(),
						   "Please enter both phone number and message.",
						   Toast.LENGTH_LONG).show();
		   }
       }); 
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		 super.onCreateOptionsMenu(menu);
		 MenuInflater blowup=getMenuInflater();
		 blowup.inflate(R.menu.contacts,menu);
		 return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.contacts:
			
			 startActivityForResult(new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI),REQ_CODE);
	        
			break;
			
			//save menu;
		case R.id.save:
			String sms=textsms.getText().toString();
			String phno="Unnamed";

			DatabaseHandlerForDraft db = new DatabaseHandlerForDraft(this);
			db.addSMS(new DraftSMS(sms,phno)); 
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==REQ_CODE && resultCode==RESULT_OK)
		{
			Log.d(TAG,"Responses:"+data.toString());
			uricontact=data.getData();
			retrievContactNumber();
		}
	}

	private void retrievContactNumber() {
		// TODO Auto-generated method stub
		String contactNumber = null;
		 
        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uricontact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);
 
        if (cursorID.moveToFirst()) {
 
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }
 
        cursorID.close();
 
        Log.d(TAG, "Contact ID: " + contactID);
 
        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
 
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
 
                new String[]{contactID},
                null);
 
        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        textphoneno.setText(contactNumber);
        Toast.makeText(this,"the number is " + contactNumber, Toast.LENGTH_LONG).show();
        cursorPhone.close();
         
        Log.d(TAG, "Contact Phone Number: " + contactNumber);
        
		
	}
	
	

	
}
