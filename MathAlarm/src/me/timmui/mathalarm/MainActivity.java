package me.timmui.mathalarm;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

	public String hour;
	public String minute;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Thread myThread = null;
		Runnable myRunnableThread = new CountUpRunner();
		myThread = new Thread(myRunnableThread);
		myThread.start();
        
		
		Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
		TextView tv =(TextView)findViewById(R.id.tv1);
		//tv.setText(mHour+" "+mMinute+" "+second);

		
		
		//-------------- Time Picker -----------------
		final TextView txtTime = (TextView) findViewById(R.id.txtTime);
		
		// Process to get Current Time
		
		final TimePickerDialog tm = new TimePickerDialog(this,
        new TimePickerDialog.OnTimeSetListener() {
 
            @Override
            public void onTimeSet(TimePicker view, int hourIn,
                    int minuteIn) {
            	hour = Integer.toString(hourIn);
            	minute = Integer.toString(minuteIn);
            	if (minuteIn < 10)
            	{
            		txtTime.setText("Selected time: "+hourIn + ": 0" + minuteIn);	
            	}
            	else
                txtTime.setText("Selected time: "+hourIn + ":" + minuteIn);
            	TextView tv1 = (TextView)findViewById(R.id.pickedTime);
        		//tv1.setText(hour+"  "+minute);
        		//CharSequence cq = (DateFormat.format("dd/MM/yyyy kk:mm", System.currentTimeMillis()));
        		//String systemTime = convertTime(cq);
        		//tv1.setText("current time is "+systemTime);
            }

			/*private String convertTime(CharSequence cq) {
				// TODO Auto-generated method stub
				final StringBuilder sb = new StringBuilder(cq.length());
    			sb.append(cq);
    			return sb.toString();
			}*/
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
	                int mHour = c.get(Calendar.HOUR_OF_DAY);
	                int mMinute = c.get(Calendar.MINUTE);
	                int second = c.get(Calendar.SECOND);
	                TextView tv = (TextView)findViewById(R.id.tv1);
	                tv.setText(mHour+" "+mMinute+" "+second);
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

class CountUpRunner implements Runnable{
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
