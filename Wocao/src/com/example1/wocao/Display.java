package com.example1.wocao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends Activity {
    private TextView c_display=null;
    private TextView stats1=null;
    private TextView stats2=null;
    private TextView stats3=null;
    private Button button = null;
    counter theobj;
    databasehelp mydbhelper=null;
    SQLiteDatabase dis_db=null;
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
		dis_db = mydbhelper.getReadableDatabase();
		stats1 = (TextView) findViewById(R.id.textView1);
		stats2 = (TextView) findViewById(R.id.textView2);
		stats3 = (TextView) findViewById(R.id.textView3);
		stats1.setText("123");
		stats2.setText("123");
		stats3.setText("123");
		c_display= (TextView) findViewById(R.id.dis_id);
		c_display.setText(theobj.counterid+"");
		String [] col = {"ID","NAME","COUNT"};
		Cursor c = dis_db.query("table1",col,"ID = "+theobj.counterid,null,null,null,null);
		c.moveToFirst();
		int wocao = c.getInt(0);
		String wocao2 = c.getString(1);
		int wocao3 = c.getInt(2);
		c_display.setText(wocao+wocao2+wocao3);
		theobj.count=wocao3;
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

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
	// when add button is clicked it will add one to the counter and update the data base
    public void add(View view)
    {
    	theobj.count++;
    	//c_display.setText(theobj.count+"");
    	
    	ContentValues values = new ContentValues();
    	values.put("COUNT", theobj.count);
    	dis_db.update("table1",values, "ID ="+theobj.counterid, null);
    	String [] wocao = {"ID","NAME","COUNT"};
    	
    	Cursor c  = dis_db.query("table1",wocao, "ID = "+theobj.counterid, null, null, null, null);
    	c.moveToFirst();
    	c_display.setText(c.getInt(0)+c.getString(1)+c.getString(2)+"wocao");
    	
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
		Toast.makeText(Display.this, "this is print1", Toast.LENGTH_LONG).show();
		super.onBackPressed();
	}
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode ==2 )
		{
			theobj.name1=data.getStringExtra("newname");
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
