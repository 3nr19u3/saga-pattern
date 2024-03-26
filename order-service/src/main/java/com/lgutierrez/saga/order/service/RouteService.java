package com.lgutierrez.saga.order.service;

import com.lgutierrez.saga.order.entity.Route;
import com.lgutierrez.saga.order.repository.RouteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @PostConstruct
    public void initRouteInDB(){
        routeRepository.saveAll(
                Stream.of(
                new Route(null,"ruta1",20,true,"lima-huacho"),
                new Route(null,"ruta2",30,true,"lima-barranca"),
                new Route(null,"ruta3",50,true,"lima-huaraz"))
                .collect(Collectors.toList()));
    }
}
