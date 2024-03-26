package com.lgutierrez.saga.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Integer userId;
    private Integer travelTicketId;
    private Boolean backpack;
    private Integer routeId;
    private Integer autobussId;
    private String seatCode;
    private Integer orderId;
    private int amount;
}
