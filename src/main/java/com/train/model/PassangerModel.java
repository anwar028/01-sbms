package com.train.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassangerModel {

	
	private String ticketId;
	private String name;

	private String source;

	private String destination;
	@CreationTimestamp
	private LocalDate journeyDate;


	private Double fare;

	private Long trainNumber;
}
