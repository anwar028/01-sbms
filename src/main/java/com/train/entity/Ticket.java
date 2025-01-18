package com.train.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name="TICKET")
public class Ticket {
	
	
	@Id
	@GeneratedValue(generator = "ticket_id_gen", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "ticket_id_gen", strategy = "com.train.idgenerator.TicketIdGenerator")
	
    @Column(name = "Ticket_ID")
	private String tickedId;
	@Column(name = "Passenger_name")
	private String name;
	@Column(name = "Passenger_source")
	private String source;
    @Column(name = "Passenger_destination")
    private String destination;
    
    @CreationTimestamp
	@Column(name = "Passenger_Created_data",updatable=false)
	private LocalDate journeyDate;
	

	@Column(name = "Trian_Num")
	private Long trainNumber;
    @Column(name = "Ticket_Pnr")
	private String pnr;
	@Column(name = "Ticket_Status")
	private String ticketStatus;
	
	

}
