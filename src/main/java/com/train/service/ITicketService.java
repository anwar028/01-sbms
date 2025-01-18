package com.train.service;

import java.util.List;
import com.train.entity.Passenger;
import com.train.entity.Ticket;
import com.train.exception.TicketNotFoundException;
import com.train.model.PassangerModel;
import com.train.model.TicketModel;

public interface ITicketService {

	TicketModel bookTicket(PassangerModel passangerModel);
	 
	Ticket getTicketByPnr(String pnr) throws TicketNotFoundException;
	
	Ticket updateTicket(Passenger passenger,String ticketId);
//	
	List<Ticket> getAlltickets() throws TicketNotFoundException;
//	
	String deleteTicket(String ticketId) throws TicketNotFoundException;
	
}



















