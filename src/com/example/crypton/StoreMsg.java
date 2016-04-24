package com.example.crypton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StoreMsg extends Activity {

	String finalmessage;
	String phonenumber;
public void onCreate(Bundle savedInstanceState){
	super. onCreate (savedInstanceState);

	Intent intent = getIntent();
	 finalmessage = intent.getStringExtra("sms");
	 phonenumber=intent.getStringExtra("phonenumber");
	DatabaseHandler db = new DatabaseHandler(this);
	db.addSMS(new IncomingSMS(finalmessage,phonenumber)); 
	//Intent i = new Intent(StoreMsg.this,INBOX.class); 
	//StoreMsg.this.startActivity(i);
}

}
