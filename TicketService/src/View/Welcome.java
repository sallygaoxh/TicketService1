package View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Welcome extends JFrame{
	JLabel backLabel;
	private Container con;
	
	public Welcome(){
		init();
	}
	
	public void init(){
		this.setTitle("Ticket Self Service");
		java.net.URL imgURL = Welcome.class.getResource("/icons/timg.jpg");
		ImageIcon img = new ImageIcon(imgURL);
		backLabel = new JLabel(img);
		this.getLayeredPane().add(backLabel, new Integer(Integer.MIN_VALUE));
		backLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = this.getContentPane();
		((JPanel)con).setOpaque(false);
		
		this.setSize(1024, 628);
		this.setVisible(true);
	}
}
