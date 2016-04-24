package com.example.crypton;

import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.SmsMessage;
import android.widget.Toast;



public class SmsReceiver extends BroadcastReceiver  {
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

	ArrayList<String> your_array_list = new ArrayList<String>();
    @Override
    public void onReceive(Context context, Intent intent) 
    {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (intent.getAction().equals(ACTION)) {

            StringBuilder sb = new StringBuilder();
            String str2 = new String();
            String str = new String();

            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                for (Object pdu : pdus){
                SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdu);
                sb.append(messages.getDisplayOriginatingAddress());
                str2 = messages.getDisplayOriginatingAddress();
                sb.append(messages.getDisplayMessageBody());
                str= messages.getDisplayMessageBody();
                
                
                Uri lookupUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str2));
                Cursor c = context.getContentResolver().query(lookupUri, new String[]{ContactsContract.Data.DISPLAY_NAME},null,null,null);
                try {
                    c.moveToFirst();
                 String  displayName = c.getString(0);
                 str2 = displayName;   

                } catch (Exception e) {
                	e.printStackTrace();
                }finally{
                    c.close();
                }
                
            	DatabaseHandler db = new DatabaseHandler(context);
            	db.addSMS(new IncomingSMS(str,str2));      
            }
            
            
           
            //---display the new SMS message---
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
			
            int icon = R.drawable.icon2;
            CharSequence tickerText = str;
            long when = System.currentTimeMillis();

            Notification notification = new Notification(icon, tickerText, when);
            CharSequence contentTitle = str2;
            CharSequence contentText = str;
           Intent notificationIntent = new Intent();
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,  new Intent(context, INBOX.class), 0);

            notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
            notification.vibrate = new long[] { 100, 250, 100, 500};
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            mNotificationManager.notify(1, notification);
            
            
            
            
            
            
            
            
        /* Intent i = new Intent(context, StoreMsg.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("sms", str);
            i.putExtra("phonenumber",str2);
            context.startActivity(i);*/

            
        }   
    }  
    
}
}
