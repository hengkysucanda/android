package com.davidgassner.plainolnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.davidgassner.plainolnotes.data.NoteItem;

public class NoteEditorActivity extends Activity {

	private NoteItem note;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_editor);
		
		Intent intent = this.getIntent();
		note = new NoteItem();
		note.setKey(intent.getStringExtra("key"));
		note.setText(intent.getStringExtra("text"));
		
		EditText et = (EditText) findViewById(R.id.noteText);
		et.setText(note.getText());
		et.setSelection(note.getText().length());
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	public void saveAndFinish(View v){
		EditText et = (EditText) findViewById(R.id.noteText);
		String txt = et.getText().toString();
		Intent intent = new Intent();
		intent.putExtra("key", note.getKey());
		intent.putExtra("text", txt);
		setResult(RESULT_OK,intent);
		finish();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			saveAndFinish(null);
		}
		return false;
	}
}
