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
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;
    final static private long DISPLAY_DELAY = 5000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		setUp();
		Button b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				am.set( AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 
						DISPLAY_DELAY, pi );
				//AlarmClockInfo info = new AlarmClockInfo();
			}
		});
	}
	public void setUp()
	{
		br = new BroadcastReceiver(){
			@Override
			public void onReceive(Context c, Intent i){
				Toast.makeText(c, DISPLAY_DELAY/1000+" secs later", Toast.LENGTH_SHORT).show();
			}
		};
		registerReceiver(br, new IntentFilter("me.timmui.mathalarm"));
		pi = PendingIntent.getBroadcast(this,0,new Intent("me.timmui.mathalarm"),0);
		am = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
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
