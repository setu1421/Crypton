package com.example.crypton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DecryptionCode extends Activity {
    EditText code1,code2;
    Button decrypt;
    String sms,phonenumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decrypt);
		code1=(EditText)findViewById(R.id.etcode1);
		code2=(EditText)findViewById(R.id.etcode2);
		decrypt=(Button)findViewById(R.id.bdecryptsend);
		Bundle bundle=getIntent().getExtras();
		sms=bundle.getString("sms");
		phonenumber=bundle.getString("phonenumber");
		
	   decrypt.setOnClickListener(new View.OnClickListener()
        {
	    	public void onClick(View v)
	    	{                
	    		try
	    		{
	    			Intent newintent = new Intent(DecryptionCode.this,Decryptedmessage.class); 
	    			int c1=Integer.parseInt(code1.getText().toString());
	    			int c2=Integer.parseInt(code2.getText().toString());
		
	    			char[] oldChar;
	    			char[] newChar;
	    			oldChar = new char[sms.length()];
	    			newChar = new char[sms.length()];

	    			char[] result1;
	    			char[] result2;
	    			result1 = new char[sms.length()];
	    			for ( int i=0; i<sms.length(); ++i) {
	    				oldChar[i] = sms.charAt(i);
	    				newChar[i] = (char) ( (oldChar[i] + c1-80)-c2 );
	    				result1[i] = newChar[i];
	    			}
	    			String str1= new String(result1);
		
	    			Bundle bundle=new Bundle();
	    			bundle.putString("sms",str1);
	    			bundle.putString("phoneno",phonenumber);
	    			newintent.putExtras(bundle);
	    			DecryptionCode.this.startActivity(newintent);
	    		}catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}
	    	}
        }); 
	
	}
}
