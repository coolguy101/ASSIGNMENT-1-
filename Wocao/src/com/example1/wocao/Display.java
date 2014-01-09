package com.example1.wocao;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display extends Activity {
    private TextView c_display=null;
    private Button button = null;
    counter theobj;
    databasehelp mydbhelper = new databasehelp(getBaseContext());
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		// Show the Up button in the action bar.
		setupActionBar();
		button = (Button) findViewById(R.id.button1);
		Intent intent = getIntent();
		theobj = (counter) intent.getSerializableExtra("object");
		 SQLiteDatabase dis_db = mydbhelper.getReadableDatabase();
		 
		c_display= (TextView) findViewById(R.id.dis_id);
		c_display.setText(theobj.counterid+"");
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
    public void add(View view)
    {
    	theobj.count=theobj.count+1;
    	c_display.setText(theobj.count+"");
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
