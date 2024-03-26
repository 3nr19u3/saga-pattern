package com.lgutierrez.saga.order.config;

import com.lgutierrez.saga.commons.event.OrderStatus;
import com.lgutierrez.saga.commons.event.PaymentStatus;
import com.lgutierrez.saga.order.entity.TravelTicket;
import com.lgutierrez.saga.order.repository.TravelTicketRepository;
import com.lgutierrez.saga.order.service.OrderStatusPublisher;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateHandler {
    private TravelTicketRepository repository;
    private OrderStatusPublisher publisher;

    public void updateOrder(int id, Consumer<TravelTicket> consumer){
        repository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(TravelTicket travelTicket) {
        OrderStatus orderStatus = travelTicket.getIsPaid() ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_REFUSED;

    }

}
