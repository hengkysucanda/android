package com.davidgassner.plainolnotes.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteSQLDataSource {
	SQLiteOpenHelper helper;
	SQLiteDatabase database;
	private static final String[] allCollumns = {
		NoteSQLHelper.COLUMN_ID,
		NoteSQLHelper.COLUMN_NOTE
		
	
	};
	
	public NoteSQLDataSource(Context context) {
		helper = new NoteSQLHelper(context);
	}
	public NoteItem create(NoteItem note){
		ContentValues values = new ContentValues();
		values.put(NoteSQLHelper.COLUMN_NOTE, note.getText());
		long id = database.insert(NoteSQLHelper.NOTE_TABLE, null, values);
		note.setId(id);
		
		return note;
	}
	
	public List<NoteItem> findAll (){
		List<NoteItem> notes = new ArrayList<NoteItem>();
		Cursor cursor = database.query(NoteSQLHelper.NOTE_TABLE, allCollumns, null, null, null, null, null);
		
		if(cursor.getCount() > 0){
			while(cursor.moveToNext()){
				NoteItem item = new NoteItem();
				item.setText(cursor.getString(cursor.getColumnIndex(NoteSQLHelper.COLUMN_NOTE)));
				item.setId(cursor.getLong(cursor.getColumnIndex(NoteSQLHelper.COLUMN_ID)));
				
				notes.add(item);
			}
		}
		
		return notes;
	}
	
	public void open() {
		database = helper.getWritableDatabase();
	}
	
	public void close (){
		helper.close();
	}
	
}
