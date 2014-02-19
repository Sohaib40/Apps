package com.example.examqa2;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;


public class Result extends Activity{

	TextView txt2=null;
	TextView txt3=null;
	TextView txt4=null;
	String  TIME="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_activity);
 
       
	 TextView txt1= (TextView) findViewById(R.id.textView5);
	 txt1.setText("You have completed your test in :");
	 
	 txt2=(TextView)findViewById(R.id.textView6);
	 txt3=(TextView)findViewById(R.id.textView7);
	 txt4=(TextView)findViewById(R.id.textView8);
	 
	 
	 loadSavedPreferences();
	
	}

	private void loadSavedPreferences() {
		
		
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String name = sharedPreferences.getString(Constants.TIME_KEY, "");
		
		this.TIME=name;
		
		String parts[]=TIME.split(":");
		
		txt2.setText(parts[0]+ "Hours");
		txt3.setText(parts[1]+ "Minutes");
		txt4.setText(parts[2]+ "Seconds");
		    
	}


}
