package com.lgutierrez.saga.order.service;

import com.lgutierrez.saga.commons.dto.OrderRequestDto;
import com.lgutierrez.saga.commons.event.OrderStatus;
import com.lgutierrez.saga.order.entity.Route;
import com.lgutierrez.saga.order.exception.APIException;
import com.lgutierrez.saga.order.repository.RouteRepository;
import com.lgutierrez.saga.order.util.TicketStatus;
import com.lgutierrez.saga.order.entity.TravelTicket;
import com.lgutierrez.saga.order.repository.TravelTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class OrderService {
    @Autowired
    private TravelTicketRepository travelTicketRepository;

    @Autowired
    private RouteRepository routeRepository;
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public TravelTicket createOrder(OrderRequestDto orderRequestDto) {
        TravelTicket travelTicket = travelTicketRepository.save(convertDtoToEntity(orderRequestDto));

        orderRequestDto.setOrderId(travelTicket.getId());
        //validate seat available
        if(orderRequestDto.getAutobussId().equals(travelTicket.getAutobussId())){
            TravelTicket tt = travelTicketRepository.findBySeatCode(orderRequestDto.getSeatCode());
            if(tt == null)
                travelTicket.setSeatCode(orderRequestDto.getSeatCode());
            else
                throw new APIException(HttpStatus.BAD_REQUEST, "seatCode is unavailable");
        }

        //set amount to create the payment in payment service it based into routeId
        routeRepository.findById(orderRequestDto.getRouteId()).ifPresent(route -> orderRequestDto.setAmount(route.getCost()));
        //produce kafka event with ORDER_CREATED status
        orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return travelTicket;
    }

    /*****
    //@Transactional
    public String enableSeat(String seatCode) {
        TravelTicket tt = travelTicketRepository.findBySeatCode(seatCode);

        if(tt == null)
            throw new APIException(HttpStatus.BAD_REQUEST, "seatCode is unavailable");

        tt.setStatus(TicketStatus.REFUSED);
        tt.setIsPaid(false);
        tt.setBackpack(null);
        tt.setSeatCode(null);
        travelTicketRepository.save(tt);
        return "Changes saved successfully";

    }
     *****/

    public List<TravelTicket> getAllOrders(){
        return travelTicketRepository.findAll();
    }

    private TravelTicket convertDtoToEntity(OrderRequestDto orderRequestDto){
        TravelTicket travelTicket = new TravelTicket();
        travelTicket.setUserId(orderRequestDto.getUserId());
        travelTicket.setAutobussId(orderRequestDto.getAutobussId());
        //generate random code to ticket
        travelTicket.setCode(1234);
        travelTicket.setStatus(TicketStatus.CREATED);
        travelTicket.setIsPaid(false);
        travelTicket.setBackpack(orderRequestDto.getBackpack());
        return travelTicket;
    }


}
