package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import View.SelectMovie;

public class EmailMaker {
	SelectMovie movieGUI;
	public EmailMaker(SelectMovie movieGUI){
		this.movieGUI = movieGUI;
		makeEmail();
	}
	
	public void makeEmail(){
		double totalMoney = 0;
		int totalTicket = 0;
		int child = 0;
		int adult = 0;
		int senior = 0;
		int student = 0;
		int eachFilm = 0;
		
		File email = new File("email.txt");
		try {
			FileWriter emailWriter = new FileWriter(email, false);
			emailWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i<movieGUI.movieArrangement.size(); i++){
			eachFilm = 0 ;
			for(int x=0; x<movieGUI.movieArrangement.get(i).getScreen().seatList.length; x++){
				for (int y=0; y<movieGUI.movieArrangement.get(i).getScreen().seatList[0].length; y++){
					if(movieGUI.movieArrangement.get(i).getScreen().seatList[x][y].getType().equals("empty")
						||movieGUI.movieArrangement.get(i).getScreen().seatList[x][y].getType().equals("asile"))
						;
					else{
						eachFilm++;
						if(movieGUI.movieArrangement.get(i).getScreen().seatList[x][y].getType().equals("adult")){
							adult++;
							totalMoney += 16;
							totalTicket++;
						}
						else if(movieGUI.movieArrangement.get(i).getScreen().seatList[x][y].getType().equals("child")){
							child++;
							totalMoney += 8;
							totalTicket++;
						}
						else if(movieGUI.movieArrangement.get(i).getScreen().seatList[x][y].getType().equals("senior")){
							senior++;
							totalMoney += 12.8;
							totalTicket++;
						}
						else if(movieGUI.movieArrangement.get(i).getScreen().seatList[x][y].getType().equals("student")){
							student++;
							totalMoney += 13.6;
							totalTicket++;
						}
					}
				
			}
		}
		
		try {
				FileWriter emailWriter = new FileWriter(email, true);
				BufferedWriter emailBuffer = new BufferedWriter(emailWriter);
				emailBuffer.write(movieGUI.movieArrangement.get(i).getName()
						+" in " + movieGUI.movieArrangement.get(i).getScreenName() + " at " 
						+ movieGUI.movieArrangement.get(i).getStartTime() 
						+" total seats sold: " + eachFilm + "\n");
				emailBuffer.flush();
				emailBuffer.close();
				emailWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			FileWriter emailWriter = new FileWriter(email, true);
			BufferedWriter emailBuffer = new BufferedWriter(emailWriter);
			emailBuffer.write("\n");
			emailBuffer.write("Total sale of the day: " + totalMoney + "\n");
			emailBuffer.write("Total ticket of the day: " + totalTicket + "\n");
			emailBuffer.write("Child ticket of the day: " + child + "\n");
			emailBuffer.write("Adult ticket of the day: " + adult + "\n");
			emailBuffer.write("Senior ticket of the day: " + senior + "\n");
			emailBuffer.write("student ticket of the day: " + student + "\n");
			emailBuffer.flush();
			emailBuffer.close();
			emailWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
