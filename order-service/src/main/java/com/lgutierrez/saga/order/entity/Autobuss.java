package com.lgutierrez.saga.order.entity;

import com.lgutierrez.saga.order.util.AutobussType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="AUTOBUSS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autobuss {
    @Id
    @GeneratedValue
    private Integer id;
    private String plate;
    private String description;
    private AutobussType autobussType;
    //TODO : CONFIGURE RELATIONSHIPS
    private Integer routeId;
}
