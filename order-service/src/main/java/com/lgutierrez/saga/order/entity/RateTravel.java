package com.lgutierrez.saga.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="RATE_TRAVEL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateTravel {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer rate;
    private Integer description;
    //TODO : CONFIGURE RELATIONSHIPS
    private Integer travelId;
    private Integer userId;
}
