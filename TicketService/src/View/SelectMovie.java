package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.alibaba.fastjson.JSON;

import DAO.EmailMaker;
import DAO.getMovie;
import Model.Movie;
import Model.Screen;
public class SelectMovie{
	private static SelectMovie movieGUI;
//	private SelectTime timeGUI;
//	private SelectSeat seatGUI;
//	private Confirmation confirmation;
	JFrame movieFrame = new JFrame();
	ArrayList<String> movieNameList = new ArrayList<String>();
	public ArrayList<Movie> movieArrangement = new ArrayList<Movie>();
	JLabel backLabel;
	private Container con;
	
	public SelectMovie(){
		loadMovie();
		init();
	}
	
//	public SelectTime getTimeGUI(){
//		return this.timeGUI;
//	}
//	
//	public SelectSeat getSeatGUI(){
//		return this.seatGUI;
//	}
//	
//	public void setSeatGUI(SelectSeat seatGUI){
//		this.seatGUI = seatGUI;
//	}
	
//	public void setConfirmation(Confirmation confirmation){
//		this.confirmation = confirmation;
//	}
//	
//	public Confirmation getConfirmation(){
//		return this.confirmation;
//	}
	
	public void loadMovie(){
		getMovie movieInfoGetter = new getMovie();
		this.movieNameList = movieInfoGetter.getMovieNameList();	
		this.movieArrangement = movieInfoGetter.getMovieArrangement();
		for (int i = 0; i<this.movieArrangement.size();i++){
			System.out.println(this.movieArrangement.get(i).toString());
			this.movieArrangement.get(i).setScreen();	
		}
	}
	
	public void init(){
		movieFrame.setTitle("Please select the movie you want");
		java.net.URL imgURL = SelectMovie.class.getResource("/icons/background.jpg");
		ImageIcon img = new ImageIcon(imgURL);
		backLabel = new JLabel(img);
		movieFrame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));
		backLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = movieFrame.getContentPane();
		con.setLayout(null);
		((JPanel)con).setOpaque(false);
		
		int x = 88;
		int y = 10;
		for(int i = 0; i<this.movieNameList.size();i++){
			if(0<i&&i<3)
				x = x+282;
			else if(i == 3){
				x = 260;
				y = 309;
			}else if (i == 4){
				x = 612;
				y = 309;
			}
			layoutMovie(movieNameList.get(i),x,y);
		}
		
		movieFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				EmailMaker email = new EmailMaker(movieGUI);
				JOptionPane.showMessageDialog(null,"An email has send to your mail box.","Information!",JOptionPane.INFORMATION_MESSAGE);
				System.exit(1);
			}
		});
		
		movieFrame.setSize(1024, 640);
		movieFrame.setVisible(true);
	}
	
	public void layoutMovie(final String movieName,int x,int y){
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(x, y, 194, 289);
		movieFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JLabel label = new JLabel("");
		
		java.net.URL labelURL = SelectMovie.class.getResource("/icons/"+movieName+".png");
		label.setIcon(new ImageIcon(labelURL));
		label.setBounds(0, 0, 194, 289);
//		label.setName(movieName);
		panel.add(label);
		
		label.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(movieName);
				SelectTime timeGUI = new SelectTime(movieGUI ,movieName);
			}
		});
	}
	
	public static void main(String[] args) {
		//new a welcome page
//		Welcome a = new Welcome();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		a.dispose();
		
		//the frame with 5 posters
		movieGUI = new SelectMovie();
	}
}
