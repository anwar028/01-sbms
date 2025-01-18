 package com.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.binding.PassengerBinding;

import com.train.binding.TicketBinding;
import com.train.entity.Passenger;
import com.train.entity.Ticket;
import com.train.exception.TicketNotFoundException;
import com.train.model.PassangerModel;
import com.train.model.TicketModel;
import com.train.responce.ResponceHandler;
import com.train.service.TicketService;

@RestController
@RequestMapping("/api")
public class TicketController {

	private TicketService service;

	public TicketController(TicketService service) {
		super();
		this.service = service;
	}

	@PostMapping(value = "/bookTicket", consumes = { "application/json" }, produces = { "application/json" })
	
	public ResponseEntity<TicketBinding> bookTicket(@RequestBody PassengerBinding passengerBinding) {

		PassangerModel passengerModel = new PassangerModel();
		passengerModel.setName(passengerBinding.getName());
		passengerModel.setSource(passengerBinding.getSource());
		passengerModel.setDestination(passengerBinding.getDestination());
		passengerModel.setTrainNumber(passengerBinding.getTrainNumber());

		TicketModel ticketModel = service.bookTicket(passengerModel);

		TicketBinding ticketBinding = new TicketBinding();
		ticketBinding.setName(ticketModel.getName());
		ticketBinding.setTicketId(ticketModel.getTicketId());
		ticketBinding.setSource(ticketModel.getSource());
		ticketBinding.setJourneyDate(ticketModel.getJourneyDate());
		ticketBinding.setDestination(ticketModel.getDestination());
		ticketBinding.setPnr(ticketModel.getPnr());
		ticketBinding.setTicketStatus(ticketModel.getTicketStatus());
		ticketBinding.setTrainNumber(ticketModel.getTrainNumber());
		System.out.println(ticketModel);
        return new ResponseEntity<>(ticketBinding, HttpStatus.CREATED);

	}

	@GetMapping("/getTicket/{pnr}")
	public ResponseEntity<Ticket> getTicket(@PathVariable String pnr) throws TicketNotFoundException {
		Ticket ticketByPnr = service.getTicketByPnr(pnr);
		return new ResponseEntity<Ticket>(ticketByPnr, HttpStatus.OK);
//		return ResponceHandler.responceBuilder("Ticket Created Succefully", HttpStatus.CREATED,ticketBinding);
	}

	@PutMapping("/updateTicket/{ticketId}")
	public ResponseEntity<?> updateTicket(@RequestBody Passenger passenger, @PathVariable String ticketId) {

		 Ticket updateTicket = service.updateTicket(passenger, ticketId);
		 return new ResponseEntity<Ticket>(updateTicket, HttpStatus.FOUND);
		
	}

	@GetMapping("/getAllTickets")
	public ResponseEntity<List<Ticket>> getAllTicket() throws TicketNotFoundException {
		List<Ticket> allTickets = service.getAlltickets();
        return new ResponseEntity<List<Ticket>>(allTickets, HttpStatus.OK);
	}

	@DeleteMapping("/deleteTicket/{ticketId}")
	public ResponseEntity<String> deleteTicket(@PathVariable String ticketId) throws TicketNotFoundException{
		String deleteTicket = service.deleteTicket(ticketId);
		return new ResponseEntity<String>(deleteTicket, HttpStatus.OK);
	}

}
