package com.lgutierrez.saga.order.entity;

import com.lgutierrez.saga.order.util.TravelStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="TRAVEL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Travel {
    @Id
    @GeneratedValue
    private Integer id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TravelStatus travelStatus;
    private Integer price;
    //TODO : CONFIGURE RELATIONSHIPS
    private Integer routeId;
    private Integer userId;
}
