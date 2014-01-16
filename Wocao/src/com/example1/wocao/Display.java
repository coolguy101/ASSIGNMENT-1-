package com.example1.wocao;

import java.util.ArrayList;

import org.joda.time.DateTime;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Display extends Activity {
    private TextView c_display=null;

    private TextView c_name = null;
    private Button button = null;
    counter theobj;
    databasehelp mydbhelper=null;
    ArrayList<DateTime> time_list= new ArrayList<DateTime>();
    ArrayList<Integer> int_time = new ArrayList<Integer>();
    ArrayList<String> hourlist;
    ArrayList<String> weeklist;
    ArrayList<String> monthlist;
    SQLiteDatabase dis_db=null;
    DateTime counter_date = null;
    Context cou_context= null;
    ListView data_list ;
    ArrayAdapter<String> array_list_Adapter ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		// Show the Up button in the action bar.
		setupActionBar();
		
		button = (Button) findViewById(R.id.button1);
		Intent intent = getIntent();
		theobj = (counter) intent.getSerializableExtra("object");
		mydbhelper= new databasehelp(Display.this);
		data_list= (ListView) findViewById(R.id.scrollView1);
		dis_db = mydbhelper.getReadableDatabase();
		hourlist= new ArrayList<String>();
		
		weeklist= new ArrayList<String>();
		monthlist= new ArrayList<String>();
		array_list_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hourlist);
		
		c_name = (TextView) findViewById(R.id.thename1);

		//start new date
		counter_date= new DateTime();
	    cou_context = getApplicationContext();
	    // add new date
		
		

		c_display= (TextView) findViewById(R.id.dis_id);
		c_display.setText(theobj.counterid+"");
		c_name.setText(theobj.name1);
		String [] col = {"ID","NAME","COUNT"};
		Cursor c = dis_db.query("table1",col,"ID = "+theobj.counterid,null,null,null,null);
		
		c.moveToFirst();
		int wocao = c.getInt(0);
		String wocao2 = c.getString(1);
		int wocao3 = c.getInt(2);
		c_display.setText(wocao+wocao2+wocao3);
		theobj.count=wocao3;
		data_list.setAdapter(array_list_Adapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}
	//to reset the counter when "reset" is clicked,and also update the database
	public void reset(View view)
	{
		theobj.count=0;
		ContentValues values = new ContentValues();
		values.put("COUNT", 0);
		dis_db.update("table1", values, "ID = "+theobj.counterid,null);
		c_display.setText(theobj.count+"");
	}
	// to display stats
	public void stats(View view)
	{   String [] date1={"COUNT","DATE2"}; 
		Cursor the_cursor = dis_db.query("table1",date1 ,"ID = "+theobj.counterid,null, null, null, null);
		the_cursor.moveToFirst();
		int hour=1;
		int week =1;
		int month = 1;
		String pre="                       ";
		while (the_cursor.moveToNext())
		{   String  cmp = the_cursor.getString(1);
			if(cmp.contains(pre.substring(0, 7)))
			{
				month++;
				for(String jiba : hourlist)
				{
					if (jiba.substring(0,7)==cmp.substring(0, 7))
					{
						break;
					}
					else
					{
						hourlist.add(pre.substring(0,7)+" ---> "+month+"");		
					}
					
				}
				hourlist.add(pre.substring(0,7)+" ---> "+month+"");
			}
			if(cmp.contains(pre.subSequence(0, 13)))
			{
				hour++;
				hourlist.add(pre.substring(0,13)+" ---> "+hour+"");
			}
			if(cmp.contains(pre.subSequence(0, 10)))
			{
				week++;
				hourlist.add(pre.substring(0,10)+" ---> "+week+"");
			}
			
			pre=the_cursor.getString(1);
		}
		array_list_Adapter.notifyDataSetChanged();
	  
	} 
	// when add button is clicked it will add one to the counter and update the data base
    public void add(View view)
    {   
    	theobj.count++;
    	//c_display.setText(theobj.count+"");
    	ContentValues values = new ContentValues();
    	values.put("COUNT", theobj.count);
    	values.put("DATE2",counter_date+"");
    	values.put("ID", theobj.counterid);
    	values.put("NAME", theobj.name1);
    	dis_db.update("table1",values, "ID ="+theobj.counterid, null);
    	dis_db.insert("table1",null, values);
    	String [] wocao = {"ID","NAME","COUNT","DATE2"};
     	Cursor c  = dis_db.query("table1",wocao, "ID = "+theobj.counterid, null, null, null, null);
    	c.moveToFirst();
    	c_display.setText(c.getInt(0)+c.getString(1)+c.getString(2)+"wocao");
    
    	
    	//DateTime time = new DateTime();
    	//stats3.setText(c.getString(3).toString());
    }
    //rename the counter, and update the database as well
    public void rename(View view)
    {
    	Intent intent = new Intent(Display.this,Rename_act.class);
    	startActivityForResult(intent, 2);
    }
    
    //on back button clicked, send counter object back to MainActivity
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent intent = new Intent();
		intent.putExtra("getdata", theobj);
		setResult(RESULT_OK, intent);
		
		super.onBackPressed();
	}
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode ==2 )
		{
			theobj.name1=data.getStringExtra("newname");
			c_name.setText(theobj.name1);
			ContentValues values = new ContentValues();
			values.put("NAME", theobj.name1);
			dis_db.update("table1", values, "ID ="+ theobj.counterid,null);
			
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
