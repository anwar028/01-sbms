package com.train.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.train.entity.Ticket;

public interface TrainRepositiory extends JpaRepository<Ticket,String> {
	
	@Query("SELECT t FROM Ticket t WHERE t.pnr = :pnr")
	Ticket findByPnr(String pnr);

}
