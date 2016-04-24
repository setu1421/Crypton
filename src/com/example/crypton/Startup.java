package com.example.crypton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Startup extends Activity implements OnClickListener{
   Button createmessage,inbox,outbox,drafts,chat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.startup);
		 createmessage=(Button)findViewById(R.id.bcreatemessage);
		 inbox=(Button)findViewById(R.id.binbox);
		 outbox=(Button)findViewById(R.id.boutbox);
		 drafts=(Button)findViewById(R.id.bdrafts);
		 chat=(Button)findViewById(R.id.bchat);
		 
		 createmessage.setOnClickListener(this);
         inbox.setOnClickListener(this);
         outbox.setOnClickListener(this);
         drafts.setOnClickListener(this);
         chat.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==createmessage)
		{
			Intent newintent = new Intent(Startup.this,CREATEMESSAGE.class); 
			Startup.this.startActivity(newintent);
		}
		if(v==inbox)
		{
			Intent newintent = new Intent(Startup.this,INBOX.class); 
			Startup.this.startActivity(newintent);
		}
		if(v==outbox)
		{
			Intent newintent = new Intent(Startup.this,SENT.class); 
			Startup.this.startActivity(newintent);
			
		}
		if(v==drafts)
		{
			Intent newintent = new Intent(Startup.this,DRAFT.class); 
			Startup.this.startActivity(newintent);
		}
		if(v==chat)
		{
			Intent newintent=new Intent(Startup.this,BluetoothChat.class);
			Startup.this.startActivity(newintent);
		}
	}
	
}
