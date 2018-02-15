package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import Model.Screen;

public class SelectSeat{
	public String movieName;
	public String screenName;
	public String time;
	Screen screen;
	int movieIndex = 0;
	private SelectMovie movieGUI;
	String ticketType;
	char row = 'A';
	int column = 1;
	int i,j;
	JFrame frame = new JFrame();
	JLabel backLabel;
	private Container con;
	
	public SelectSeat(SelectMovie movieGUI, String movieName, String screenName, String time){
		this.movieGUI = movieGUI;
		this.movieName = movieName;
		this.screenName = screenName;
		this.time = time;
		System.out.println(movieGUI.movieArrangement.size());
		for(int i = 0;i<movieGUI.movieArrangement.size();i++){
			if (movieGUI.movieArrangement.get(i).getName().equals(movieName)&&
				movieGUI.movieArrangement.get(i).getScreenName().equals(screenName)&&
				movieGUI.movieArrangement.get(i).getStartTime().equals(time)){
				this.screen = this.movieGUI.movieArrangement.get(i).getScreen();
				movieIndex = i;	
			}
		}
		init();
	}
	
	public SelectSeat(SelectMovie movieGUI, int movieIndex){
		this.movieGUI = movieGUI;
		//get the specific screen of corresponding movie
		this.screen = this.movieGUI.movieArrangement.get(movieIndex).getScreen();
	}
	
	public void init(){
//		Confirmation confirmation = movieGUI.getConfirmation();
		
		frame.setTitle(movieName + " at " + screenName+" , "+time );
		java.net.URL imgURL = SelectSeat.class.getResource("/icons/background.jpg");
		ImageIcon img = new ImageIcon(imgURL);
		backLabel = new JLabel(img);
		frame.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));
		backLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = frame.getContentPane();
		((JPanel)con).setOpaque(false);
		frame.getContentPane().setLayout(null);
		
		JPanel screenpanel = new JPanel();
		screenpanel.setOpaque(false);
		screenpanel.setBounds(300, 28, 424, 50);
		frame.getContentPane().add(screenpanel);
		screenpanel.setLayout(null);
		
		JLabel lblScreen = new JLabel("Screen");
		lblScreen.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblScreen.setBackground(new Color(144,144,144));
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblScreen.setBounds(0,0, 424, 50);
		screenpanel.add(lblScreen);
		
		int x = (1024-(this.screen.seatList[0].length*50+(this.screen.seatList[0].length-1)*20))/2;
		int y = 100;

		for (i = 0; i<this.screen.seatList.length; i++){
			for(j = 0; j<this.screen.seatList[0].length; j++){
				JPanel panel = new JPanel();
				panel.setOpaque(false);
				panel.setBounds(x, y, 50, 50);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				System.out.print(this.screen.seatList[i][j].getType());
				JLabel seatLabel = new JLabel();
//				this.screen.seatList[i][j].getLabel().setBounds(0, 0, 50, 50);
//				this.screen.seatList[i][j].getLabel().setOpaque(true);
//				this.screen.seatList[i][j].getLabel().setToolTipText(this.screen.seatList[i][j].getType());
				seatLabel.setBounds(0, 0, 50, 50);
				seatLabel.setOpaque(true);
				seatLabel.setToolTipText(this.screen.seatList[i][j].getType());
				
				if(this.screen.seatList[i][j].getType().equals("empty")){
					seatLabel.setBackground(new Color(230, 230, 250));
					panel.add(seatLabel);
					this.screen.seatList[i][j].setRow(row);
					this.screen.seatList[i][j].setColumn(column);
					seatLabel.setText(""+row+column);
					seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
					column++;
					
					seatLabel.addMouseListener(new MouseAdapter(){
						@Override
						public void mouseClicked(MouseEvent arg0) {
							frame.dispose();
							Confirmation confirmation;
							confirmation = new Confirmation(movieGUI, movieIndex, ((JLabel) arg0.getSource()));
							confirmation.setIndex(movieIndex);
							confirmation.setJLabel((JLabel) arg0.getSource());
							confirmation.setVisible(true);
							
						}
					});
				}
				else if (this.screen.seatList[i][j].getType().equals("asile"))
					;
				//For selected seats
				else{
					seatLabel.setBackground(new Color(141,141,141));
					panel.add(seatLabel);
					this.screen.seatList[i][j].setRow(row);
					this.screen.seatList[i][j].setColumn(column);
					seatLabel.setText(""+row+column);
					seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
					column++;
				}
				x += 70;
			}
			System.out.println();
			x = (1024-(this.screen.seatList[0].length*50+(this.screen.seatList[0].length-1)*20))/2;
			column = 1;
			y += 80;
			row ++;
		}
		
		frame.setSize(1024, 640);
		frame.setVisible(true);
	}
	
	public void reload(){
		frame.removeAll();
		frame.repaint();
	}
	
}
