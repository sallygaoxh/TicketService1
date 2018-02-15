package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import DAO.TicketMaker;
import Model.Ticket;

public class Confirmation extends JFrame implements ActionListener{
	SelectMovie movieGUI;
	int movieIndex;
	String movieName;
	char row;
	int column;
	JButton btnCancel,btnSubmitPay;
	JComboBox comboBox;
	
	private JTextField ageText;
	private JTextField IDtext;
	int age = 0;
	String ticketType = "adult";
	String studentID = "";
	String screenName;
	String startTime;
	JLabel label,lblStudentId;
 
	public Confirmation(SelectMovie movieGUI){
		this.movieGUI = movieGUI;
	}
	
	public Confirmation(SelectMovie movieGUI, int movieIndex, JLabel label){
		this.movieGUI = movieGUI;
		this.movieIndex = movieIndex;
		this.movieName = movieGUI.movieArrangement.get(movieIndex).getName();
		this.screenName = movieGUI.movieArrangement.get(movieIndex).getScreenName();
		this.startTime = movieGUI.movieArrangement.get(movieIndex).getStartTime();
		this.label = label;
		this.row = label.getText().charAt(0);
		this.column = Integer.parseInt(""+label.getText().charAt(1));
		init();
	}

	public void init(){
		this.setSize(500,450);
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		
		JLabel typeLabel = new JLabel("Select a ticke type:");
		typeLabel.setBounds(66, 82, 123, 16);
		this.getContentPane().add(typeLabel);
		
		JLabel ageLabel = new JLabel("Input your age:");
		ageLabel.setBounds(66, 167, 123, 16);
		this.getContentPane().add(ageLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(296, 78, 134, 27);
		this.getContentPane().add(comboBox);
		comboBox.addItem("adult");
		comboBox.addItem("child");
		comboBox.addItem("senior");
		comboBox.addItem("student");
		
		ageText = new JTextField();
		ageText.setBounds(296, 161, 134, 28);
		this.getContentPane().add(ageText);
		ageText.setColumns(10);
		ageText.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }  
        });  
		
		lblStudentId = new JLabel("Student ID:");
		lblStudentId.setBounds(66, 258, 110, 16);
		this.getContentPane().add(lblStudentId);
		lblStudentId.setVisible(false);
		
		IDtext = new JTextField();
		IDtext.setBounds(296, 252, 134, 28);
		this.getContentPane().add(IDtext);
		IDtext.setColumns(10);
		IDtext.setVisible(false);
		
		btnSubmitPay = new JButton("Submit & Pay");
		btnSubmitPay.setBounds(66, 357, 117, 29);
		this.getContentPane().add(btnSubmitPay);
		//The listener of the submit button
		btnSubmitPay.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(313, 357, 117, 29);
		this.getContentPane().add(btnCancel);

		btnCancel.addActionListener(this);
		
	}
	
//	public JFrame getFrame (){
//		return this.frame;
//	}
	
	public void setIndex(int movieIndex){
		this.movieIndex = movieIndex; 
		this.movieName = movieGUI.movieArrangement.get(movieIndex).getName();
		this.screenName = movieGUI.movieArrangement.get(movieIndex).getScreenName();
		this.startTime = movieGUI.movieArrangement.get(movieIndex).getStartTime();
	}
	
	public void setJLabel(JLabel label){
		this.label = label;
		this.row = label.getText().charAt(0);
		this.column = Integer.parseInt(""+label.getText().charAt(1));
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnCancel){
			this.setVisible(false);
		}
		else if(e.getSource()==btnSubmitPay){
			if (comboBox.getSelectedItem().equals("student")){
				if (IDtext.getText().length()==0){
					JOptionPane.showMessageDialog(null,"You need to provide student ID to buy a student ticket.");
					lblStudentId.setVisible(true);
					IDtext.setVisible(true);
				}
				else{
					age = Integer.parseInt(ageText.getText());
					if (age < 18)
						JOptionPane.showMessageDialog(null,"You can buy a child ticket.");
					else{
						JOptionPane.showMessageDialog(null,"OK.");
						ticketType = "student";
						studentID = IDtext.getText();
						for (int i = 0; i<movieGUI.movieArrangement.get(movieIndex).screen.seatList.length; i++){
							for(int j = 0; j<movieGUI.movieArrangement.get(movieIndex).screen.seatList[0].length; j++){
								if(movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getRow()==row&&
									movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getColumn()==column){
									movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].setStudent();
									label.setBackground(new Color(141,141,141));
									label.setToolTipText(ticketType);
									SelectSeat seatGUI = new SelectSeat(movieGUI, movieName,screenName,startTime);
									Ticket ticket = new Ticket(movieName,ticketType,screenName,startTime,""+row+column);
									ticket.setStudentID(studentID);
									ticket.initTickId();
									TicketMaker maker = new TicketMaker(ticket);
//									movieGUI.setSeatGUI(new SelectSeat(movieGUI, movieIndex));
									this.dispose();
								}
							}
						}
					}
				}
				
			}
			
			else if(comboBox.getSelectedItem().equals("senior")){
				lblStudentId.setVisible(false);
				IDtext.setVisible(false);
				if (ageText.getText().length()==0)
					JOptionPane.showMessageDialog(null,"You need to provide your age to buy a senior ticket.");
				else{
					age = Integer.parseInt(ageText.getText());
					if (age<55){
						JOptionPane.showMessageDialog(null,"You are not allow to buy a senior ticket.");
					}else{
						JOptionPane.showMessageDialog(null,"OK.");
						ticketType = "senior";
						for (int i = 0; i<movieGUI.movieArrangement.get(movieIndex).screen.seatList.length; i++){
							for(int j = 0; j<movieGUI.movieArrangement.get(movieIndex).screen.seatList[0].length; j++){
								if(movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getRow()==row&&
									movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getColumn()==column){
									movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].setSenior();
									label.setBackground(new Color(141,141,141));
									label.setToolTipText(ticketType);
									SelectSeat seatGUI = new SelectSeat(movieGUI, movieName,screenName,startTime);
									Ticket ticket = new Ticket(movieName,ticketType,screenName,startTime,""+row+column);
									ticket.initTickId();
									TicketMaker maker = new TicketMaker(ticket);
//									movieGUI.getSeatGUI().setVisible(true);
//									this.setVisible(false);
									this.dispose();
								}
							}
						}
					}
				}
			}
			
			else if (comboBox.getSelectedItem().equals("child")){
				lblStudentId.setVisible(false);
				IDtext.setVisible(false);
				if (ageText.getText().length()==0)
					JOptionPane.showMessageDialog(null,"You need to provide your age to buy a child ticket.");
				else{
					age = Integer.parseInt(ageText.getText());
					if (age>17){
						JOptionPane.showMessageDialog(null,"You are not allow to buy a child ticket.");
					}
					else{
						JOptionPane.showMessageDialog(null,"OK.");
						ticketType = "child";
						for (int i = 0; i<movieGUI.movieArrangement.get(movieIndex).screen.seatList.length; i++){
							for(int j = 0; j<movieGUI.movieArrangement.get(movieIndex).screen.seatList[0].length; j++){
								if(movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getRow()==row&&
									movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getColumn()==column){
									movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].setChild();
									label.setBackground(new Color(141,141,141));
									label.setToolTipText(ticketType);
									SelectSeat seatGUI = new SelectSeat(movieGUI, movieName,screenName,startTime);
									Ticket ticket = new Ticket(movieName,ticketType,screenName,startTime,""+row+column);
									ticket.initTickId();
									TicketMaker maker = new TicketMaker(ticket);
//									movieGUI.getSeatGUI().setVisible(true);
									this.dispose();
								}
							}
						}
					}
				}
			}
			//adult 
			else {
				JOptionPane.showMessageDialog(null,"OK.");
//				System.out.println(movieGUI.movieArrangement.get(movieIndex).toString());
				for (int i = 0; i<movieGUI.movieArrangement.get(movieIndex).screen.seatList.length; i++){
					for(int j = 0; j<movieGUI.movieArrangement.get(movieIndex).screen.seatList[0].length; j++){
						if(movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getRow()==row&&
							movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].getColumn()==column){
							movieGUI.movieArrangement.get(movieIndex).screen.seatList[i][j].setAdult();
							label.setBackground(new Color(141,141,141));
							label.setToolTipText(ticketType);
							SelectSeat seatGUI = new SelectSeat(movieGUI, movieName,screenName,startTime);
							//Encapsulate user information and choices into a ticket class
							Ticket ticket = new Ticket(movieName,ticketType,screenName,startTime,""+row+column);
							ticket.initTickId();
							TicketMaker maker = new TicketMaker(ticket);
//							movieGUI.getSeatGUI().setVisible(true);
							this.dispose();
						}
					}
				}	
			}
		}
	}
	
}
