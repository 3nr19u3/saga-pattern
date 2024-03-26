package com.lgutierrez.saga.order.entity;

import com.lgutierrez.saga.order.util.TicketStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TRAVEL_TICKET")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelTicket {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer code;
    private TicketStatus status;
    private Boolean isPaid;
    private Boolean backpack;
    private String seatCode;
    //TODO : CONFIGURE RELATIONSHIPS
    private Integer autobussId;
    private Integer userId;
}
