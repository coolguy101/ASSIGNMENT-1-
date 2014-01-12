package com.example1.wocao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
        System.out.print("asdfaksdf");
        alist_counter = new ArrayList<counter>();
        counter counter1= new counter("Example Counter",the_counter_id);
        
        // create new database
        mydbhelper2 =  new databasehelp(MainActivity.this);
        thedb = mydbhelper2.getWritableDatabase();
        ContentValues values = new ContentValues(); 
        alist_counter.add(counter1);
        values.put("ID", 0);
        values.put("NAME", counter1.name1);
        values.put("COUNT", counter1.count);
        thedb.insert("table1",null, values);
        the_counter_id++;
//        counter cmp1 = new counter ("cmp1",99);
  //      counter cmp2 = new counter ("cmp2",98);
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
		Toast.makeText(MainActivity.this, "this is print in onResult", Toast.LENGTH_LONG).show();
		
		counter wocao = (counter) data.getSerializableExtra("getdata");
		
		Toast.makeText(MainActivity.this, "this is print in onResult2", Toast.LENGTH_LONG).show();
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
