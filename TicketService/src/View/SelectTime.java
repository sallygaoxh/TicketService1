package View;

import javax.swing.*;
import javax.swing.border.LineBorder;

import DAO.getMovie;
import Model.Movie;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class SelectTime{
	private SelectMovie movieGUI; 
	ArrayList<String[]> timeList = new ArrayList<String[]>();
	ArrayList<Movie> movieArrangement = new ArrayList<Movie>();
	String movieName;
	JLabel backLabel;
	private Container con;
	JFrame frame = new JFrame();
	
	public SelectTime(SelectMovie movieGUI, String movieName){
		this.movieGUI = movieGUI;
		this.movieName = movieName;
		init();
	}
	public void init(){
		frame.setTitle("Timetable for "+movieName);
		java.net.URL imgURL = SelectTime.class.getResource("/icons/background.jpg");
		ImageIcon img = new ImageIcon(imgURL);
		backLabel = new JLabel(img);
		frame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));
		backLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = frame.getContentPane();
		con.setLayout(null);
		
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 28));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(269, 65, 486, 43);
		titleLabel.setText(movieName);
		frame.getContentPane().add(titleLabel);
		((JPanel)con).setOpaque(false);
		
		addTimeTable();
		
		frame.setSize(1024, 640);
		frame.setVisible(true);
	}
	
	public void addTimeTable(){
		//load the list of each film
		timeList = new getMovie().getTimeList(movieName);
		//layout the time list
		int x = 300;
		int y = 120;
		
		//Compare system time with each item
		for (int i = 0;i<timeList.size();i++){
			long timemillis = System.currentTimeMillis();   
	         //转换日期显示格式   
	         SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	         String str = df.format(new Date(timemillis));
	        
	         String[] timeNow, timeMovie;
	         timeNow = str.split(":");
	         timeMovie = timeList.get(i)[1].split(":");
	         System.out.println(timeNow[0]+timeNow[1]);
	         System.out.println(timeMovie[0]+timeMovie[1]);
	         if(Integer.parseInt(timeNow[0])<Integer.parseInt(timeMovie[0])){
	        	 layoutTime(timeList.get(i),x,y);
	        	 y+=100;
	         }else if (Integer.parseInt(timeNow[0])==Integer.parseInt(timeMovie[0])){
	        	 if(Integer.parseInt(timeNow[1])<=Integer.parseInt(timeMovie[1])){
	        		 layoutTime(timeList.get(i),x,y);
	        		 y+=100;
	        	 }
	         }
		}
	}
	
	public void layoutTime(final String[] time,int x,int y){
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(x, y, 424, 70);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		//Decoration
		final JLabel label = new JLabel("");
		label.setText(time[0]+" at "+time[1]);
		label.setBounds(0, 0, 424, 70);
		label.setOpaque(true);
		label.setBackground(new Color(230, 230, 250));
		label.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		label.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SelectSeat seatGUI = new SelectSeat(movieGUI, movieName,time[0],time[1]);
				frame.dispose();
			}
		});
	}
}
