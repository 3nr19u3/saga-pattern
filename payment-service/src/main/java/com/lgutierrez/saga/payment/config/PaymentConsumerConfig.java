package com.lgutierrez.saga.payment.config;

import com.lgutierrez.saga.commons.event.OrderEvent;
import com.lgutierrez.saga.commons.event.OrderStatus;
import com.lgutierrez.saga.commons.event.PaymentEvent;
import com.lgutierrez.saga.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {
    @Autowired
    private PaymentService paymentService;
    @Bean
    public Function<Flux<OrderEvent>,Flux<PaymentEvent>> paymentProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        //get the user_id
        //check the payment transaction && that the user have a PENDING payment
        //if payment process is success -> Payment COMPLETED
        //if payment process failure -> Payment FAILED (produce another event to orderMS)
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
        }else{
            return Mono.fromRunnable(()->this.paymentService.cancelOrder(orderEvent));
        }
    }
}
