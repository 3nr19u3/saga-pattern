package com.lgutierrez.saga.order.service;

import com.lgutierrez.saga.order.entity.Autobuss;
import com.lgutierrez.saga.order.repository.AutobussRepository;
import com.lgutierrez.saga.order.util.AutobussType;
import jakarta.annotation.PostConstruct;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutobussService {
    private AutobussRepository autobussRepository;

    private Integer id;
    private String plate;
    private String description;
    private AutobussType autobussType;
    private Integer routeId;


    @PostConstruct
    public void initAutoBussInDB(){
        autobussRepository.saveAll(
                Stream.of(
                          new Autobuss(null,"BWD-650","Volvo - 2010",AutobussType.STANDAR,1),
                          new Autobuss(null,"AAA-450","MERCEDES - 2011",AutobussType.STANDAR,2),
                          new Autobuss(null,"AXR-010","FOTON - 2008",AutobussType.VIP,3))
                        .collect(Collectors.toList()));
    }
}
