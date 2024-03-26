package com.lgutierrez.saga.order.repository;

import com.lgutierrez.saga.order.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Integer> {
}
