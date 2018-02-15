package Model;

import java.util.ArrayList;

public class Screen {
	String screenName;
	int rows;
	int columns;
	public String asile;
	public Seat[][] seatList = null;
//	ArrayList<String> asiles = new ArrayList<>();
	String[] asiles;
	
	public Screen(){	
		
	}
	
	public Screen(String screenName){
		this.screenName = screenName;	
	}
	
	public void setScreenName(String name){
		this.screenName = name;
	}
	
	public String getScreenName(){
		return this.screenName;
	}
	
	public void setRows(int rows){
		this.rows = rows;
	}
	
	public int getRows(){
		return this.rows;
	}
	
	public void setColumns(int columns){
		this.columns = columns;
	}
	
	public int getColumns(){
		return this.columns;
	}
	
	public void setSeatList(int row, int column){
		this.seatList = new Seat[row][column];
		for(int i = 0; i<row;i++){
			for(int j = 0;j<column;j++){
				this.seatList[i][j] = new Seat();
			}
		}
	}
	
	public Seat[][] getSeatList(){
		return this.seatList;
	}
	
	public void setAsile(String asile){
		this.asile = asile;
	}
	
	public void setAsiles(){
		this.asiles = this.asile.split("\\.");
		for(int i = 0;i<this.asiles.length;i++){
			String[] split = this.asiles[i].split("-");
			this.seatList[Integer.parseInt(split[0])][Integer.parseInt(split[1])].setAsile();
		}
	}
	
	public String[] getAsiles(){
		return this.asiles;
	}
	
	public void setSeatList(){
		
	}
}
