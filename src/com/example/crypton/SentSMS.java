package com.example.crypton;


public class SentSMS {
    int icon=R.drawable.sender;
    String from;
    String sub;
    String desc;
    String time;
    public SentSMS()
    {
    	
    }
    public SentSMS(String finalmessage, String phonenumber) {
		// TODO Auto-generated constructor stub
    	this.desc=finalmessage;
    	this.from=phonenumber;
	}
	public String getphonenumber() {
        return from;
    }

    public void setphonenumber(String from) {
        this.from = from;
    }
    
    public int getIcon() {
        return icon;
    }

    
    /*public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }*/
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
	public void setIcon(int icLauncher) {
		// TODO Auto-generated method stub
		this.icon=icLauncher;
	}
	
}