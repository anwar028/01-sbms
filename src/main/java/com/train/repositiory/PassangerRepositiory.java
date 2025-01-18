package com.train.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.entity.Passenger;
import com.train.entity.Ticket;

public interface PassangerRepositiory extends JpaRepository<Passenger,String> {
    

}
