package com.example.examqa2;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class Exam extends Activity implements Runnable{
	
	public static int i =0;
	Model model = null;
	int questionNumber = 0;
	Quizinfo qInfo = null;
	
	TextView txtQNo = null;
	TextView txtQuestion = null;
	TextView time=null;
	Button btn_next=null;
	RadioButton Rdbutton1=null;
	RadioButton Rdbutton2=null;
	Timer timer=null;
	Thread timeUpdateThread;
	boolean bool=true;
	String key=Constants.TIME_KEY;
	ProgressDialog dialog;
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam_activity);
	
		this.model = Model.getInstance();
		
		 dialog = new ProgressDialog(Exam.this);
		   dialog.setMessage("Please wait while data is being fetched!");
		   dialog.setCancelable(false);
		   dialog.show();
	      new Datafromserver().execute();
		
	
	      
	}
	
	public void savepreferences(String key,String value){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        Intent sd=new Intent(Exam.this,Result.class);
        startActivity(sd);
       }
	
	
	
	public void bindQuestion(Quizinfo q)
	{
		this.txtQNo.setText(String.valueOf(q.getQuestionNumber()));
		this.txtQuestion.setText(q.getQuestion());
		String[] choices=q.getChoices().split(",");
		
        		
		
		this.Rdbutton1.setText(choices[0]);
		this.Rdbutton2.setText(choices[1]);
	}
	
	public void isDataLoaded(Boolean bVal)
	{
		if(bVal)
		{
			this.dialog.dismiss();
			this.qInfo = this.model.getQuestionInfo(questionNumber);
			this.questionNumber++;
			
			this.txtQNo = (TextView)findViewById(R.id.textView8);
			this.txtQuestion = (TextView)findViewById(R.id.textView7);
		     	
			
			this.time=(TextView)findViewById(R.id.textView5);		
			
			this.Rdbutton1=(RadioButton)findViewById(R.id.radioButton1);
			this.Rdbutton2=(RadioButton)findViewById(R.id.radioButton2);
			
			this.bindQuestion(this.qInfo);
			
			
			timer=new Timer();
		      this.timeUpdateThread = new Thread(this, "Demo Thread");
			    System.out.println("Child thread: " + this.timeUpdateThread);
			    this.timeUpdateThread.start(); // Start the thread
		    
		}
	}
   @SuppressWarnings("deprecation")
public void BUTTON_NEXT(View view){
	   this.qInfo = this.model.getQuestionInfo(questionNumber);
	   this.questionNumber++;
	   if(this.qInfo!= null){
	   this.bindQuestion(this.qInfo);
	   
	   }
	   else
	   {
		   Toast.makeText(getApplicationContext(), "no more questions", Toast.LENGTH_SHORT).show();
		   this.bool=false;
		   String value=this.time.getText().toString();
		   savepreferences( key, value);
		   	   
	   }
   }
	@Override
	public void run() 
	{
		while(bool)
		{
			new UpdateTimeAsynchronously(this.timer).execute();
			
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	
	 private class UpdateTimeAsynchronously extends AsyncTask<String, Timer, String> {

		 Timer updateTimer = null;
		 
		public UpdateTimeAsynchronously(Timer pTimer){
			    this.updateTimer = pTimer;
		}
		protected void onPostExecute(String val) {
			//time.setText(Integer.toString(timer.getHours())+":"+Integer.toString(timer.getMinutes())+":"+Integer.toString(timer.getSeconds()));
			time.setText(val);
	     }

		@Override
		protected String doInBackground(String... params) {
			this.updateTimer.updatetime(this.updateTimer);
			String time = "";
			String zero = "0";
			if(this.updateTimer.getHours() <= 9)
				time += zero;
			time += this.updateTimer.getHours() + " : ";
			if(this.updateTimer.getMinutes() <= 9)
				time += zero;
			time += this.updateTimer.getMinutes() + " : ";
			if(this.updateTimer.getSeconds() <= 9)
				time += zero;
			time += this.updateTimer.getSeconds();
			return time;
			
		}
		 
	 };
	 
	 
	 private class Datafromserver extends AsyncTask<String, String, String>{

		 String json=null;
		 InputStream is=null;
		 DefaultHttpClient httpClient;
		 HttpGet httpGet;
		 HttpResponse httpResponse;
		 HttpEntity httpEntity;
		 BufferedReader reader;
		 StringBuilder sb;
		 String line="";
		 String url="http://10.0.2.2/QAserver/connection.php";
		 
		@Override
		protected String doInBackground(String... params) {
			try{
				
			
			httpClient = new DefaultHttpClient();
		    //HttpPost httpPost = new HttpPost(url);
		    httpGet = new HttpGet(this.url);
		    httpResponse = httpClient.execute(httpGet);
		    httpEntity = httpResponse.getEntity();
		    is = httpEntity.getContent();   
		    reader = new BufferedReader(new InputStreamReader(
		      is, "iso-8859-1"), 8);
		    sb = new StringBuilder();
		    line = null;
		    while ((line = reader.readLine()) != null) {
		     sb.append(line + "\n");
		    }
		    is.close();
		    json = sb.toString();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			return json;
		}
	 	    
		protected void onPostExecute(String json) {
			JsonHelper.populateHashFromJSON(json, model.questionArrayList);
			isDataLoaded(true);
		}
	 
	 }
}