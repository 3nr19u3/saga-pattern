package com.lgutierrez.saga.commons.dto;

import com.lgutierrez.saga.commons.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    private Integer userId;
    private int amount;
    private PaymentStatus paymentStatus;
    private Integer travelTicketId;
}
