package me.timmui.mathalarm;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	//variable declaration
	PendingIntent pendingIntent;
    BroadcastReceiver br;
    AlarmManager alarmManager;
    Calendar alarm;
    boolean alarmActiviated;
    
    //sets the delay of the timer.
    final static private long DISPLAY_DELAY = 5000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		setUp();
		//-------------- Time Picker -----------------
		
		// Process to get Current Time
        final Calendar c = Calendar.getInstance();
        alarm = Calendar.getInstance();
        alarm.set(Calendar.YEAR, 3000);
        alarm.set(Calendar.SECOND, 0);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
		
		final TimePickerDialog tm = new TimePickerDialog(this,
        new TimePickerDialog.OnTimeSetListener() {
 
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay,
                    int minute) {
                alarm.set(Calendar.HOUR_OF_DAY, hourOfDay);
                alarm.set(Calendar.MINUTE, minute);
                alarm.set(Calendar.YEAR, 2015);
                alarmActiviated = false;
                
                //initializes the alarm manager and set the wakeup time
				alarmManager.set( AlarmManager.RTC_WAKEUP, alarm.getTimeInMillis(), pendingIntent );
            }
        }, mHour, mMinute, false);
				
		Button b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//initializes the alarm manager and set the wakeup time
				alarmManager.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 
						DISPLAY_DELAY, pendingIntent );
				//AlarmClockInfo info = new AlarmClockInfo();
			}
		});
		
		Button b2 = (Button)findViewById(R.id.b2);
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tm.show ();
			}
		});
		
		Button b3 = (Button)findViewById(R.id.b3);
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
			}
		});
	}
	public void setUp()
	{
		//receives intent made by alarm manager
		br = new BroadcastReceiver(){
			@Override
			//some sec after,receives the alarm manager and makes a toast.
			public void onReceive(Context c, Intent i){
				Intent intent = new Intent(MainActivity.this,TimesUp.class);
				startActivity(intent);
				alarmActiviated = true;
			}
		};
		//register the receiver
		registerReceiver(br, new IntentFilter("me.timmui.mathalarm"));
		//Makes a new intent when receives broadcast
		pendingIntent = PendingIntent.getBroadcast(this,0,new Intent("me.timmui.mathalarm"),0);
		alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
