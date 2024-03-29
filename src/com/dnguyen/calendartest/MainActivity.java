package com.dnguyen.calendartest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.text.Format;
import android.app.Activity;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private Cursor mCursor = null;
	private static final String[] COLS = new String[]
		{CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mCursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI, COLS, null, null, null);
		mCursor.moveToFirst();
		
		Button b = (Button) findViewById(R.id.next);
		b.setOnClickListener(this);
		b = (Button) findViewById(R.id.previous);
		b.setOnClickListener(this);
		onClick(findViewById(R.id.previous));
	}
	
	public void onClick(View v)
	{
		TextView tv = (TextView) findViewById(R.id.data);
		String title = "NA";
		Long start = 0L;
		
		switch(v.getId()){
		case R.id.next:
			if(!mCursor.isLast())
				mCursor.moveToNext();
		break;
		case R.id.previous:
			if(!mCursor.isFirst())
				mCursor.moveToPrevious();
			break;
		}
		
		Format df = DateFormat.getDateFormat(this);
		Format tf = DateFormat.getTimeFormat(this);
		try{
			title = mCursor.getString(0);
			start = mCursor.getLong(1);
		} catch (Exception e){
			
		}
		tv.setText(title + " on " + df.format(start) + " at " + tf.format(start));
	}
	
	/*public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
