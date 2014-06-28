package com.davidgassner.plainolnotes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteSQLHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME ="notes.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String NOTE_TABLE="notes";
	public static final String COLUMN_ID="notes_id";
	public static final String COLUMN_NOTE="notes_text";
	
	public static final String TABLE_CREATE = 
				"CREATE TABLE " + NOTE_TABLE + " ( " + 
				COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,  " + 
				COLUMN_NOTE + " TEXT)";
			;
	
	public NoteSQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+ NOTE_TABLE);
		onCreate(db);
	}

}

















