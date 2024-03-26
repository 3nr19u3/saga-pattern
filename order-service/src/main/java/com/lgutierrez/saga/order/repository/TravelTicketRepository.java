package com.lgutierrez.saga.order.repository;

import com.lgutierrez.saga.order.entity.TravelTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelTicketRepository extends JpaRepository<TravelTicket, Integer> {
    TravelTicket findBySeatCode(String seatCode);
}
