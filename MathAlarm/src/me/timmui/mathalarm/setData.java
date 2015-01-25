package me.timmui.mathalarm;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
<<<<<<< HEAD
import android.app.TimePickerDialog;
=======
import android.content.Intent;
>>>>>>> origin/master
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class setData extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setdata);
		//name.setText(getIntent().getExtras().getString("userName"));
<<<<<<< HEAD
		
=======
		TimePicker tm = (TimePicker)findViewById(R.id.timePicker1);
		Button back = (Button)findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(setData.this,MainActivity.class);
				startActivity(intent);
			}
		});
>>>>>>> origin/master
	}
}