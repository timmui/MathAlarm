package me.timmui.mathalarm;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	Calendar alarm;
	Boolean alarmActiviated = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread myThread = null;

	    Runnable myRunnableThread = new CountDownRunner();
	    myThread= new Thread(myRunnableThread);   
	    myThread.start();
		
		//-------------- Time Picker -----------------
		final TextView txtTime = (TextView) findViewById(R.id.txtTime);
		
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
                txtTime.setText(String.format("Selected time: %02d:%02d",hourOfDay,minute));
                
                alarm.set(Calendar.HOUR_OF_DAY, hourOfDay);
                alarm.set(Calendar.MINUTE, minute);
                alarm.set(Calendar.YEAR, 2015);
                alarmActiviated = false;
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
	public void doWork() {
	    runOnUiThread(new Runnable() {
	        public void run() {
	            try{
	                Calendar c = Calendar.getInstance();
	                
	                if (c.compareTo(alarm) >= 0 && alarmActiviated == false){
	                	Intent intent = new Intent(MainActivity.this,TimesUp.class);
	                	startActivity(intent);
	                	alarmActiviated = true;
	                }
	                
	                //tv1.setText(hour+" "+minute+" "+second);
	            }catch (Exception e) {}
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
	class CountDownRunner implements Runnable{
	    // @Override
	    public void run() {
	            while(!Thread.currentThread().isInterrupted()){
	                try {
	                doWork();
	                    Thread.sleep(1000); // Pause of 1 Second
	                } catch (InterruptedException e) {
	                        Thread.currentThread().interrupt();
	                }catch(Exception e){
	                }
	            }
	    }
	}
}
