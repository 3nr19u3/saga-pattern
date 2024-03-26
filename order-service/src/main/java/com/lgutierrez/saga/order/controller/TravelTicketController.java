package com.lgutierrez.saga.order.controller;

import com.lgutierrez.saga.commons.dto.OrderRequestDto;
import com.lgutierrez.saga.order.entity.TravelTicket;
import com.lgutierrez.saga.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-ticket")
public class TravelTicketController {

    private OrderService orderService;

    @PostMapping("/create")
    public TravelTicket createTicket(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    //@PostMapping("/enable-seat")
    //public String enableSeat(@RequestBody String seatCode){
    //    return orderService.enableSeat(seatCode);
    //}

    @GetMapping()
    public List<TravelTicket> getOrders(){
        return orderService.getAllOrders();
    }

}
