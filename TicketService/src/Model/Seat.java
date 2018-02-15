package Model;

import javax.swing.JLabel;

public class Seat {
	int column;
	char row;
	String type = "empty"; //empty; aisle; adult; child; student; senior
	String movieName;
	String startTime;
	String screen;
// 	JLabel label = new JLabel();
	
	public Seat(){
		
	}
	
	public void setColumn(int c){
		this.column = c;
	}
	public int getColumn(){
		return this.column;
	}
	public void setRow(char row){
		this.row = row;
	}
	public char getRow(){
		return this.row;
	}
//	public void setLabel(JLabel label){
//		this.label = label;
//	}
//	public JLabel getLabel(){
//		return this.label;
//	}
	public String getMovieName(){
		return this.movieName;
	}
	public void setMovieName(String movieName){
		this.movieName = movieName;
	}
	public String getStartTime(){
		return this.startTime;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String toString(){
		return ""+this.row+this.column;
	}
	
	public void setAsile(){
		this.type = "asile";
	}
	public void setStudent (){
		this.type = "student";
	}
	public void setChild(){
		this.type = "child";
	}
	public void setSenior(){
		this.type = "senior";
	}
	public void setAdult(){
		this.type = "adult";
	}
	public String getType(){
		return this.type;
	}
}
