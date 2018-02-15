package DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Model.Ticket;

public class TicketMaker {
	public TicketMaker(Ticket ticket){
		File ticketFile = new File("ticket-" + ticket.getTicketID() + ".txt");
		try {
			FileWriter ticketWriter = new FileWriter(ticketFile, false);
			BufferedWriter ticketBuffer = new BufferedWriter(ticketWriter);
			ticketBuffer.write("Film name: " + ticket.getMovieName() + "\n");
			ticketBuffer.write("Start time: " + ticket.getStartTime()  + "\n");
			ticketBuffer.write("Screen number: " + ticket.getScreenName() + "\n");
			ticketBuffer.write("Position: " + ticket.getPosition() + "\n");
			ticketBuffer.write("Ticket type: " +  ticket.getTicketType() + "\n");
			ticketBuffer.write("Ticket ID: " + ticket.getTicketID() + "\n");
			if(ticket.getTicketType().equals("student")){
				ticketBuffer.write("Student ID: " + ticket.getStudentID() + "\n");
			}
			ticketBuffer.flush();
			ticketBuffer.close();
			ticketWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
