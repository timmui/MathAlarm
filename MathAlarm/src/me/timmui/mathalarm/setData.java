package me.timmui.mathalarm;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.TimePicker;

public class setData extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setdata);
		//name.setText(getIntent().getExtras().getString("userName"));
		TimePicker tm = (TimePicker)findViewById(R.id.timePicker1);
	}
}