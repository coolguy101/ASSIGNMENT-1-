package com.example1.wocao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehelp extends SQLiteOpenHelper {
	public static final int VERSION = 1;
	public databasehelp(Context context)
	{
		super(context,"mycounterdatabase",null,VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL("create table table1(ID int,NAME text,COUNT int)");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
