package com.lgutierrez.saga.payment.repository;

import com.lgutierrez.saga.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    Payment findByTravelTicketId(Integer travelTicketId);
}
