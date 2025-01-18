package com.train.binding;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketBinding {
	
	private String ticketId;
	
	
	private String name;

	private String source;

	private String destination;
	@CreationTimestamp
	private LocalDate journeyDate;

   private Long trainNumber;
	
	private String pnr;

    private String ticketStatus;

}
