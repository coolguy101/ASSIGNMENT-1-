package com.example1.wocao;


// this is the code for making custom listview adapter
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

 
public class CustomAdapter extends ArrayAdapter<counter>{
	public LayoutInflater inflater = null;
	public ArrayList<counter> somearray;
	public TextView thetextview=null;
	public CustomAdapter(Context context, int resource,ArrayList<counter> thearray) 
	{
		super(context, resource,thearray);
		this.somearray=thearray;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View theview = convertView; 
		if(theview==null)
			{
			  theview = inflater.inflate(R.layout.item2, null);
			}
		counter obj_counter = somearray.get(position);
		String thename= obj_counter.name1;
		((TextView) theview.findViewById(R.id.wocao)).setText(thename);
		convertView = theview; 
		
		return convertView;
	}
	
    
}
