package Model;

public class Ticket {
	String movieName;
	String ticketType;
	double price = 16.0;
	String studentID = "";
	String ticketId = "";
	String time;
	String screenName;
	String startTime;
	String position;
	
	public Ticket(String movieName,String ticketType, String screenName,String startTime, String position){
		this.movieName = movieName;
		this.ticketType = ticketType;
		this.screenName  = screenName;
		this.startTime = startTime;
		this.position = position;
	}
	
	public String getMovieName(){
		return this.movieName;
	}
	
	public void setPrice(){
		if(this.ticketType.equals("student")){
			//student ticket
			this.price = 16.0 *0.85;
		}else if(this.ticketType.equals("child")){
			//child ticket
			this.price = 16.0 *0.5;
		}
		else if(this.ticketType.equals("senior")){
			//senior ticket
			this.price = 16.0 *0.8;
		}
	}
	
	public String getTicketType(){
		return this.ticketType;
	}
	public double getPrice(){
		return this.price;
	}
	
	public void setStudentID (String studentID){
		this.studentID = studentID;
	}
	public String getStudentID(){
		return this.studentID;
	}
	
	public String getTicketID(){
		return this.ticketId;
	}
	
	public String getScreenName(){
		return this.screenName;
	}
	
	public String getStartTime(){
		return this.startTime;
	}
	
	public String getPosition(){
		return this.position;
	}
	
	public void initTickId(){
		int lastNum;
		for (int i = 0;i<8;i++){
			lastNum = (int)(1+Math.random()*(4-1+1));//(数据类型)(最小值+Math.random()*(最大值-最小值+1))
			this.ticketId+=lastNum;
		}
	}
	public String toString(){
		return this.movieName+","+this.ticketType+","+this.price;
	}
}
