package com.lgutierrez.saga.commons.dto;

import com.lgutierrez.saga.commons.event.OrderStatus;
import com.lgutierrez.saga.commons.event.PaymentStatus;

public class OrderResponseDto {
    private Integer userId;
    private String travelTicket;
    private PaymentStatus paymentStatus;
    private String route;
    private String autobuss;
    private String seat;
    private OrderStatus orderStatus;
}
