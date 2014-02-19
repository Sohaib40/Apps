package com.example.examqa2;


import java.util.Date;

public class Timer {

	private int hours;
	private int minutes;
	private int seconds;
	
	   @SuppressWarnings("deprecation")
	public Timer  getCurrentTime(){
		   
		   
		   
		   Date date=new Date();
		   date.getTime();
		   this.hours=date.getHours();
		   this.minutes=date.getMonth();
		   this.seconds=date.getSeconds();
		  
		   return this;
		   
	   }

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	public  Timer updatetime(Timer t)
	{
		if(t.seconds >= 59){
			if(t.getMinutes()>= 59){
				t.setHours(t.getHours()+1);
				t.setMinutes(0);
				t.setMinutes(0);
			}
			else{
				
			
			t.setMinutes(t.getMinutes()+1);
			t.setSeconds(0);
		}
			return t;
	}
		t.setSeconds(t.getSeconds()+1);
		return t;
	}
  }



      