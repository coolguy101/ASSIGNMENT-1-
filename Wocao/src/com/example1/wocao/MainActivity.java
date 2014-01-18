package com.example1.wocao;

import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.DateTime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
	
	DateTime thetime = null;
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
        //create new list
        showDialog("Welcome","Long Press to delete counters\n\nClick new counter to add\n\nClick sort to sort in order",MainActivity.this);
        alist_counter = new ArrayList<counter>();
        counter counter1= new counter("Example Counter",the_counter_id);
        thetime = new DateTime();
        // create new database
        mydbhelper2 =  new databasehelp(MainActivity.this);
        thedb = mydbhelper2.getWritableDatabase();
        //add a value to database example counter
        ContentValues values = new ContentValues(); 
        //alist_counter.add(counter1);
        values.put("ID", 0);
        values.put("NAME", counter1.name1);
        values.put("COUNT", counter1.count);
        //thedb.delete("table1",null, null);
        int flag=5;
        Cursor c1 = thedb.query("table1",null,null, null, null, null,null);
        ArrayList<Integer> the_id_list = new ArrayList<Integer>();
        if (c1.moveToFirst())
        {   
        	alist_counter.add(new counter(c1.getString(1),c1.getInt(0),c1.getInt(2)));
        	the_counter_id=c1.getInt(0);
        	the_id_list.add(c1.getInt(0));
        	while(c1.moveToNext())
        	{   flag=5;
        		for (int theid:the_id_list)
        	    {
        		    if (c1.getInt(0)==theid)
        		    {
        		         flag=1;
        		         break;
        		    }
        		    
        	    }
        	    if (flag==1)
        	    {
        	    	continue;
        	    }
        	    if (flag==5)
        	    {
        	    	the_id_list.add(c1.getInt(0));
        	    	alist_counter.add(new counter(c1.getString(1),c1.getInt(0),c1.getInt(2)));
        	    }
        		//alist_counter.add(new counter(c1.getString(1),c1.getInt(0),c1.getInt(2)));
        		//theid=c1.getInt(0);
        		if (c1.getInt(0)>the_counter_id)
        		{
        			the_counter_id=c1.getInt(0);
        		}
        	}
        	the_counter_id++;
        }
       /* while (c1.moveToFirst())
        {   
        	if (c1.getString(c1.getColumnIndex("NAME"))==null)
        	{  Toast.makeText(this,"the DB is not empty888",Toast.LENGTH_LONG).show();
        	   break;
        	   
            }*/
        	//Toast.makeText(this,"the DB is not empty",Toast.LENGTH_LONG).show();
        	/*c1.moveToFirst();
        	//alist_counter.add(new counter(c1.getString(1),c1.getInt(0),c1.getInt(2)));
        	
        	while(c1.moveToNext())
        	{
        		//alist_counter.add(new counter(c1.getString(1),c1.getInt(0),c1.getInt(2)));
        		the_counter_id= c1.getInt(0)+1;
        		Toast.makeText(this,"asdfasdf",Toast.LENGTH_LONG).show();
        	}*/
        
        
        //thedb.insert("table1",null, values);
        //the_counter_id++;
//        counter cmp1 = new counter ("cmp1",99);
  //      counter cmp2 = new counter ("cmp2",98);
        Toast.makeText(this, "LongPress to delete Counter",Toast.LENGTH_LONG).show();
        listview = (ListView)findViewById(R.id.list1);
        cut_adapter= new cutadapter(MainActivity.this,R.id.list1,alist_counter);
        
         textv= (TextView)findViewById(R.id.wocao);
        listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method
				Intent intent = new Intent(MainActivity.this,Display.class);
				intent.putExtra("object", alist_counter.get(arg2));
				startActivityForResult(intent,1);
				}
		});
        //long click to delete
        listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int id  = alist_counter.get(arg2).counterid;
				thedb.delete("table1", "ID ="+id,null);
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
        ContentValues values = new ContentValues();
        values.put("ID", new_count.counterid);
        values.put("NAME", new_count.name1);
        values.put("COUNT", new_count.count);
        thedb.insert("table1",null,values);
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
            //alist_counter.clear();
            
            Collections.sort(alist_counter,new countercmp());
            cut_adapter.notifyDataSetChanged();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
		


	}

    // to make  a dialog code is from stack overflow
	/*
	 * http://stackoverflow.com/questions/18891569/how-to-make-a-dialog-box-in-android-returning-a-value-boolean
	 */
	public Dialog showDialog(String title, String msg, final Activity activity) {

        final AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        /*alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();

                    activity.finish();

            }
        });*/
        alertDialog.show();

        return alertDialog;

    }
	
	/*@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.print("wocao");
		super.onStart();
		Toast.makeText(MainActivity.this, "this is print", Toast.LENGTH_LONG).show();
		Intent intent= getIntent();
		counter c1 = (counter) intent.getSerializableExtra("getdata");
		alist_counter.remove(c1.counterid);
		alist_counter.add(c1.counterid, c1);
		cut_adapter.notifyDataSetChanged();
		
	}*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		
		counter wocao = (counter) data.getSerializableExtra("getdata");
		
		
		for (counter c1 : alist_counter)
		{
			if(c1.counterid == wocao.counterid)
			{
				c1.count=wocao.count;
				c1.name1=wocao.name1;
				break;
			}
		}
		cut_adapter.notifyDataSetChanged();
		}



	
    
    
    
    
    
    
}
