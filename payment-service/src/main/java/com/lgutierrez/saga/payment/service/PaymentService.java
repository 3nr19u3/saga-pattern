package com.lgutierrez.saga.payment.service;

import com.lgutierrez.saga.commons.dto.OrderRequestDto;
import com.lgutierrez.saga.commons.dto.PaymentRequestDto;
import com.lgutierrez.saga.commons.event.OrderEvent;
import com.lgutierrez.saga.commons.event.PaymentEvent;
import com.lgutierrez.saga.commons.event.PaymentStatus;
import com.lgutierrez.saga.payment.entity.Payment;
import com.lgutierrez.saga.payment.entity.UserTransaction;
import com.lgutierrez.saga.payment.repository.PaymentRepository;
import com.lgutierrez.saga.payment.repository.UserTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    //get the user_id
    //check the payment transaction && that there have a seat available
    //if have seat available -> Payment SUCCESS
    //if haven't seat available -> Payment FAILED (produce another event to orderMS)

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getUserId(),
                                                                    orderRequestDto.getAmount(),
                                                                    PaymentStatus.PAYMENT_PENDING,
                                                                    orderRequestDto.getTravelTicketId());

        Payment payment = paymentRepository.findByTravelTicketId(orderRequestDto.getTravelTicketId());

        if(payment.getPaymentStatus() == PaymentStatus.PAYMENT_PENDING){
            //go forward
            userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(),
                                                               orderRequestDto.getUserId(),
                                                               orderRequestDto.getTravelTicketId(),
                                                               orderRequestDto.getAmount()));
            return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
        }else{
            //go back(payment failed)
            return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED);
        }

    }

    @Transactional
    public void cancelOrder(OrderEvent orderEvent) {
        //delete the transaction
        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                });
        //update the seatNumber, isPaid, backpack and ticketStatus
        //String seatCode = orderEvent.getOrderRequestDto().getSeatCode();
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //HttpEntity<String> request = new HttpEntity<>(seatCode, headers);

        //ResponseEntity<String> responseEntity =
        //        restTemplate.postForEntity("http://localhost:8080/api/travel-ticket/enable-seat", request, String.class);


    }
}
