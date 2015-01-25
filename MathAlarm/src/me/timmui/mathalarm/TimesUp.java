package me.timmui.mathalarm;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class TimesUp extends ActionBarActivity {
	int first;
	int second;
	int op;
	int answer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		TextView tv = (TextView)findViewById(R.id.question);
		Random rand = new Random();
		first = rand.nextInt((10-1)+1)+1;
		second = rand.nextInt((10-1)+1)+1;
		op = rand.nextInt((3-1)+1)+1;
		
		if (op == 1){
			tv.setText(first+" + "+second+" = ");
			answer = first+second;
			}
		else if (op == 2){
			if (first > second){
				tv.setText(first+" - "+second+" = ");
				answer = first-second;}
			else if (first < second){
				tv.setText(second+" - "+first+" = ");
				answer = second-first;}
			else{
				tv.setText(first+" - "+(second+1)+" = ");
				answer = first-(second+1);}
			}
		else if (op == 3){
			tv.setText(first+" x "+second+" = ");
			answer = first*second;
			}
		
		final EditText et = (EditText)findViewById(R.id.editText1);
		
		Button b1 = (Button) findViewById (R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (answer == Integer.parseInt((et.getText().toString()))){
	            	Intent intent = new Intent(TimesUp.this,MainActivity.class);
	            	startActivity(intent);
	            }
			}
		});
		
		
		et.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        boolean handled = false;
		        if (actionId == EditorInfo.IME_ACTION_SEND) {
		            if (answer == Integer.parseInt((et.getText().toString()))){
		            	Intent intent = new Intent(TimesUp.this,MainActivity.class);
		            	startActivity(intent);
		            }
		            handled = true;
		        }
		        return handled;
		    }
		});
		
	}
	

}
