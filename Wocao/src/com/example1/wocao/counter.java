package com.example1.wocao;

import java.io.Serializable;

public class counter implements Serializable{
	int counterid=0;
	int count = 0;
	String name1="";
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
	
	

}
