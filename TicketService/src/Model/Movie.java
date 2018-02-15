package Model;

import DAO.getScreen;

public class Movie {
	String name;
	String startTime;
	String screenName;
	String duration;
	public Screen screen;
	
	public Movie(String name, String startTime, String screenName){
		this.name = name;
		this.startTime = startTime;
		this.screenName = screenName;
	}
	
	public Movie(){
		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setStartTime(String time){
		this.startTime = time;
	}
	public String getStartTime(){
		return this.startTime;
	}
	public void setScreenName(String screenName){
		this.screenName = screenName;
	}
	public String getScreenName(){
		return this.screenName;
	}
	public String getDuration(){
		return this.duration;
	}
	public void setDuration(String duration){
		this.duration = duration;
	}
	public void setScreen(){
		this.screen = new getScreen().getScreenLayout(this.screenName);
	}
	public Screen getScreen(){
		return this.screen;
	}
	public String toString(){
		return "Movie name: "+this.name + " ,screen: "+ this.screenName +" ,start time: "+this.startTime + ", duration:"+this.duration;
	}
}
