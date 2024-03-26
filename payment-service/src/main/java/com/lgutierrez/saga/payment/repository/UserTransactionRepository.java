package com.lgutierrez.saga.payment.repository;

import com.lgutierrez.saga.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {

}
