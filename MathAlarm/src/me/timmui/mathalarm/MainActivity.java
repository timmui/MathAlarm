package me.timmui.mathalarm;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv =(TextView)findViewById(R.id.tv1);
		tv.setText("Pick a time");
		
		//-------------- Time Picker -----------------
		final TextView txtTime = (TextView) findViewById(R.id.txtTime);
		
		// Process to get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
		
		final TimePickerDialog tm = new TimePickerDialog(this,
        new TimePickerDialog.OnTimeSetListener() {
 
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                    int minute) {
                txtTime.setText("Selected time: "+hourOfDay + ":" + minute);
            }
        }, mHour, mMinute, false);
		
		Button b2 = (Button)findViewById(R.id.b2);
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tm.show ();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate t he menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
