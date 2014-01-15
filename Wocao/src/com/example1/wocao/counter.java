package com.example1.wocao;

import java.io.Serializable;
import java.util.Comparator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class counter extends Activity implements Serializable, Comparator<counter>{
	
	int counterid=0;
	int count = 0;
	String name1="";
	Context context  = getBaseContext();
	
	public counter()
	{
		
	}
	public counter(String name,int theid,int count)
	{
		this.name1=name;
		this.counterid=theid;
		this.count=count;
		
	}
	public counter (String name,int theid)
	{
		this.name1=name;
		this.counterid=theid;
	}
	// reset counter
	public void reset()
	{
		this.count=0;
	}
	// rename the counter
	public void rename(String name)
	{
		this.name1=name;
	}
	// add 1 to counter
	public void add()
	{
		this.count=this.count+1;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return this.name1;
	}
	@Override
	public int compare(counter lhs, counter rhs) {
		// TODO Auto-generated method stub
		
		return lhs.count-rhs.count;
	}
	
	

}
