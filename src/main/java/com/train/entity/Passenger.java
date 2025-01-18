package com.train.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name="PASSENGER")
public class Passenger {

	@Id
	@GeneratedValue(generator = "passenger_id_gen", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "passenger_id_gen", strategy = "com.train.idgenerator.PassengerIdGenerator")
	@Column(name = "Passenger_id")
	private String pId;
	@Column(name = "Passenger_name")
	private String name;
	@Column(name = "Passenger_source")
	private String source;
	@Column(name = "Passenger_destination") 
	private String destination;
	
	@CreationTimestamp
	@Column(name = "Passenger_Created_date",updatable=false)
	private LocalDateTime journeyDate;
	

	
	@Column(name = "Tikcet_Price")
	private Double fare;
	@Column(name = "Train_Number")
	private Long trainNumber;
	
	
	
}
