 package com.train.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.entity.Passenger;
import com.train.entity.Ticket;
import com.train.exception.TicketNotFoundException;
import com.train.model.PassangerModel;
import com.train.model.TicketModel;
import com.train.repositiory.PassangerRepositiory;
import com.train.repositiory.TrainRepositiory;

@Service 
public class TicketService implements ITicketService {

	private TrainRepositiory ticketRepository;

	private PassangerRepositiory passengerRepository;

	private Map<String, Ticket> tickmap = new HashMap<>();

	@Autowired
	public void setTicketRepository(TrainRepositiory ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
 
	@Autowired
	public void setPassengerRepository(PassangerRepositiory passengerRepository) {
		this.passengerRepository = passengerRepository;
	}

	@Override
	public TicketModel bookTicket(PassangerModel passangerModel) {
		// Create and save a new Passenger entity
		Passenger passenger = new Passenger();
		passenger.setName(passangerModel.getName());
		passenger.setSource(passangerModel.getSource());
		passenger.setDestination(passangerModel.getDestination());
		passenger.setTrainNumber(passangerModel.getTrainNumber());
		Passenger savedPassenger = passengerRepository.save(passenger);
		 
		
		// Generate a random 10-digit PNR
		String pnr = "";
		for (int i = 0; i < 10; i++) {
			pnr += (int) (Math.random() * 10);
		}

		// Create and set up the Ticket entity
		Ticket ticket = new Ticket();
		ticket.setName(savedPassenger.getName());
		ticket.setSource(savedPassenger.getSource());
		ticket.setDestination(savedPassenger.getDestination());
		ticket.setPnr(pnr);
		ticket.setTicketStatus("confirmed");
		ticket.setTrainNumber(savedPassenger.getTrainNumber());

		// Save the Ticket entity
		Ticket savedTicket = ticketRepository.save(ticket);

		// Convert Ticket to TicketModel
		TicketModel ticketModel = new TicketModel();
		ticketModel.setTicketId(savedTicket.getTickedId());
		ticketModel.setName(savedTicket.getName());
		ticketModel.setJourneyDate(savedTicket.getJourneyDate());
		ticketModel.setSource(savedTicket.getSource());
		ticketModel.setDestination(savedTicket.getDestination());
		ticketModel.setPnr(savedTicket.getPnr());
		ticketModel.setTicketStatus(savedTicket.getTicketStatus());
		ticketModel.setTrainNumber(savedTicket.getTrainNumber());

		return ticketModel;
	}

	@Override
	public Ticket getTicketByPnr(String pnr) throws TicketNotFoundException {

		Ticket ticket = ticketRepository.findByPnr(pnr);

		if (ticket != null) {
			return ticket;
		} else {

			throw new TicketNotFoundException("No Ticket found with this " + pnr);
		}
	}

	@Override
	public Ticket updateTicket(Passenger passenger, String ticketId) {
		Ticket existingticket = null;
		Optional<Ticket> byId = ticketRepository.findById(ticketId);
		if (byId.isPresent()) {
			existingticket = byId.get();
		}
		existingticket.setSource(passenger.getSource());
		existingticket.setDestination(passenger.getDestination());
		existingticket.setName(passenger.getName());
		existingticket.setTrainNumber(passenger.getTrainNumber());
		ticketRepository.save(existingticket);
		return existingticket;
	}

//	
	@Override
	public List<Ticket> getAlltickets() throws TicketNotFoundException {
		List<Ticket> all = ticketRepository.findAll();
		if (all != null) {
			return all;
		} else {

			throw new TicketNotFoundException("No Ticket avaible");
		}
	}

	@Override
	public String deleteTicket(String ticketId) throws TicketNotFoundException {

		if (!ticketRepository.existsById(ticketId)) {
			
			throw new TicketNotFoundException("No Ticket available with ID: " + ticketId);
		}

		ticketRepository.deleteById(ticketId);
		return "Ticket with ID " + ticketId + " successfully deleted.";

	}

}
