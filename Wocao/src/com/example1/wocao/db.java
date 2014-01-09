package com.example1.wocao;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper{
	public db(Context context)
	{
		super(context,"cundb",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String q_table="CREATE TABLE cundbb (id INTEGER PRIMARY KEY, name TEXT,count INTEGER,date datetime)";
	    db.execSQL(q_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String query ="DROP TABLE IF EXISTS cundbb";
		db.execSQL(query);
	}
    public void insert(HashMap<String,String> queryValues)
    {
    	SQLiteDatabase database = this.getWritableDatabase();
    	ContentValues values = new ContentValues();
    	
	}
}