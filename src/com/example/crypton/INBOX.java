package com.example.crypton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;  
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
  
public  class INBOX extends Activity {
String sms,phonenumber;
ListView list;
ArrayList<IncomingSMS> your_array_list = new ArrayList<IncomingSMS>();
AdapterView.AdapterContextMenuInfo info;
DatabaseHandler db = new DatabaseHandler(this);

public   void onCreate (Bundle icicle) 
{
   super. onCreate (icicle);
   setContentView (R.layout.inbox);
   list = (ListView) findViewById (R.id.lv);
   your_array_list = db.getAllsms();       
//  int q= db.getContactsCount();
 // your_array_list.add(q);
   db.close();
   Collections.reverse(your_array_list);

   //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,  your_array_list);
   list.setAdapter(new CustomAdapter(your_array_list,this));
   registerForContextMenu(list);
   list.setOnItemClickListener(new OnItemClickListener() {
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		v.showContextMenu();
		 phonenumber =(String) ((TextView) v.findViewById(R.id.From)).getText();
		 sms =(String) ((TextView) v.findViewById(R.id.description)).getText();

    }
});
}

@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	// TODO Auto-generated method stub
	super.onCreateContextMenu(menu, v, menuInfo);		
			
		info = (AdapterContextMenuInfo) menuInfo;
		System.out.println("Reached");
		 
		int id = (int) list.getAdapter().getItemId(info.position);			
		//menu.setHeaderTitle(your_array_list.get(info.position).getDesc());		
		menu.add(Menu.NONE, v.getId(), 0, "View");
		menu.add(Menu.NONE, v.getId(), 0, "Delete");
}

@Override  
public boolean onContextItemSelected(MenuItem item) {
    if (item.getTitle() == "View") {
    	Intent newintent=new Intent(INBOX.this,ViewMessage.class);
    	Bundle bundle=new Bundle();
    	bundle.putString("phonenumber",phonenumber);
    	bundle.putString("sms",sms);

    	newintent.putExtras(bundle);
    	INBOX.this.startActivity(newintent);
    	}  
    else if (item.getTitle() == "Delete") {
    	try{
			db.deleteSMS(new IncomingSMS(sms,phonenumber)); 
			db.close();

			Intent i = new Intent(INBOX.this,INBOX.class); 
    		INBOX.this.startActivity(i);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }
    else 	{
    	return false;
    	}  
return true;  
}

@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();			
}

//custom adapter class

public class CustomAdapter extends BaseAdapter {

    private ArrayList<IncomingSMS> _data;
    Context _c;
    
    CustomAdapter (ArrayList<IncomingSMS> data, Context c){
        _data = data;
        _c = c;
    }
   
    public int getCount() {
        // TODO Auto-generated method stub
        return _data.size();
    }
    
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return _data.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
   
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
         View v = convertView;
         if (v == null) 
         {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_message, null);
         }

           ImageView image = (ImageView) v.findViewById(R.id.icon);
           TextView fromView = (TextView)v.findViewById(R.id.From);
          // TextView subView = (TextView)v.findViewById(R.id.subject);
           TextView descView = (TextView)v.findViewById(R.id.description);
         //  TextView timeView = (TextView)v.findViewById(R.id.time);

           IncomingSMS msg = _data.get(position);
           image.setImageResource(msg.icon);
           fromView.setText(msg.from);
           //subView.setText("Subject: "+msg.sub);
           descView.setText(msg.desc);
        //   timeView.setText(msg.time);		              		
        
           image.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}    						
	});
        
        return v; 
}
}

}
    
   
  