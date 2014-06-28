package com.davidgassner.plainolnotes;

import java.util.List;

import com.davidgassner.plainolnotes.data.NoteItem;
import com.davidgassner.plainolnotes.data.NoteSQLDataSource;
import com.davidgassner.plainolnotes.data.NoteSQLHelper;
import com.davidgassner.plainolnotes.data.NotesDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private NotesDataSource datasource;
	private NoteSQLDataSource noteSQLDataSource;
	List<NoteItem> notesList;
	
	private NoteSQLHelper sqlHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		datasource = new NotesDataSource(this);
		
		noteSQLDataSource = new NoteSQLDataSource(this);
		noteSQLDataSource.open();
		
		refreshDisplay();
		
		sqlHelper = new NoteSQLHelper(this);
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1001 && resultCode ==RESULT_OK){
			NoteItem noteItem = new NoteItem();
			noteItem.setKey(data.getStringExtra("key"));
			noteItem.setText(data.getStringExtra("text"));
			noteSQLDataSource.create(noteItem);
		}
		
		refreshDisplay();

	}

	private void refreshDisplay() {
//		notesList = datasource.findAll();
		notesList=noteSQLDataSource.findAll();
		ArrayAdapter<NoteItem> adapter =
				new ArrayAdapter<NoteItem>(this, R.layout.list_item_layout, notesList);
		setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.action_create) {
			createNote();
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void createNote() {
		NoteItem note = NoteItem.getNew();
		Intent intent = new Intent(this, NoteEditorActivity.class);
		intent.putExtra("key", note.getKey());
		intent.putExtra("text", note.getText());
		startActivityForResult(intent, 1001);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		NoteItem note = notesList.get(position);
		Intent intent = new Intent(this, NoteEditorActivity.class);
		intent.putExtra("key", note.getKey());
		//
		intent.putExtra("text", note.getText());
		startActivityForResult(intent, 1001);
		
	}
	
}
