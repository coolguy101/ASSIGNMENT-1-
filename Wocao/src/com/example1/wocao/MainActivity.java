package com.example1.wocao;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
    public int the_counter_id = 0;
    ListView listview = null;
    TextView textv=null;
    //String [] counters;
    //public counter[] counter_obj;
    //public ArrayAdapter<counter> newadp ;
    public cutadapter cut_adapter;
    public ArrayList<counter> alist_counter ;
    public databasehelp mydbhelper2 ;
    public SQLiteDatabase thedb;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        alist_counter = new ArrayList<counter>();
        counter counter1= new counter("Example Counter",the_counter_id);
        the_counter_id++;
        // create new database
        mydbhelper2 =  new databasehelp(getBaseContext());
        thedb = mydbhelper2.getWritableDatabase();
        ContentValues values = new ContentValues(); 
        alist_counter.add(counter1);
        values.put("ID", 0);
        values.put("NAME", counter1.name1);
        values.put("COUNT", counter1.count);
        long newrowid;
        newrowid = thedb.insert("TABLE1",null, values);
        Toast.makeText(this, "Welcome!",Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "LongPress to delete Counter",Toast.LENGTH_LONG).show();
        ListView listview = (ListView)findViewById(R.id.list1);
        cut_adapter= new cutadapter(MainActivity.this,R.id.list1,alist_counter);
        
        TextView textv= (TextView)findViewById(R.id.wocao);
        listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method
				Intent intent = new Intent(MainActivity.this,Display.class);
				
				intent.putExtra("object", alist_counter.get(arg2));
				startActivity(intent);
				
			}
		});
        //
        listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				alist_counter.remove(arg2);
				cut_adapter.notifyDataSetChanged();
				Toast.makeText(MainActivity.this, "counter deleted!", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
        listview.setAdapter(cut_adapter);
        
    
        
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        
        return true;
    }
    
    public counter creat_counter(String name)
    {   counter new_count = new counter(name,the_counter_id);
        the_counter_id++;
    	return new_count;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
        case R.id.item1:
            alist_counter.add(creat_counter("New Counter"));
            cut_adapter.notifyDataSetChanged();
            return true;
        case R.id.item2:
            alist_counter.clear();
            cut_adapter.notifyDataSetChanged();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }


	}



	
    
    
    
    
    
    
}
