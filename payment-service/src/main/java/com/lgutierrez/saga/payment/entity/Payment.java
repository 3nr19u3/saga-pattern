package com.lgutierrez.saga.payment.entity;

import com.lgutierrez.saga.commons.event.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private int id;
    private int amount;
    private PaymentStatus paymentStatus;
    //TODO : CONFIGURE RELATIONSHIPS
    private Integer travelTicketId;
    private Integer UserId;

}
