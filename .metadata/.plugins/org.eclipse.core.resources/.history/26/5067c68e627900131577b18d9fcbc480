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
		
		db.execSQL("CREATE TABLE TABLE1(ID INT PRIMARY KEY NOT NULL,NAME TEXT NOT NULL,COUNT INT NOT NULL)");
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
