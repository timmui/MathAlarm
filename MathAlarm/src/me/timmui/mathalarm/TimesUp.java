package me.timmui.mathalarm;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimesUp extends ActionBarActivity {
	int first;
	int second;
	int op;
	int answer;
	Ringtone ringtone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		TextView tv = (TextView)findViewById(R.id.question);
		throwAlarm();
		getMath(tv);

		final EditText et = (EditText)findViewById(R.id.editText1);

		Button b1 = (Button) findViewById (R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					if (answer == Integer.valueOf(et.getText().toString())){
						Intent intent = new Intent(TimesUp.this,MainActivity.class);
						ringtone.stop();
						startActivity(intent);
					}
				}
				catch (NumberFormatException e) {
					Log.d("NumInput",e.toString());
				}
			}
		});
	}
	@Override
	public void onBackPressed() {
	}

	private void throwAlarm (){
		Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		if(alarmSound == null){
			alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			if(alarmSound == null){
				alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			}
		}

		if (alarmSound != null){
			ringtone = RingtoneManager.getRingtone(this,(alarmSound));
			ringtone.play();
		}
	}

	public void getMath(TextView outputview){
		Random rand = new Random();
		first = rand.nextInt((100-10)+1)+1;
		second = rand.nextInt((100-10)+1)+1;
		op = rand.nextInt((3-1)+1)+1;
		while (op == 3)
		{
			if (first<=5||second<=5)
			{
				outputview.setText(first+" x "+second+" = ");
				answer = first*second;
			}
			else
			{
				first = rand.nextInt((100-10)+1)+1;
				second = rand.nextInt((100-10)+1)+1;
				op = rand.nextInt((2-1)+1)+1;
			}
			break;
		}
		if (op == 1)
		{
			outputview.setText(first+" + "+second+" = ");
			answer = first+second;
		}
		else if (op == 2){
			if (first != second){
				outputview.setText(first+" - "+second+" = ");
				answer = first-second;}
			else{
				outputview.setText(first+" - "+(second+1)+" = ");
				answer = first-(second+1);}
		}
	}

}
