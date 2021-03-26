package com.delivery4.Reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery4.modul.Order;

@Repository
public interface OrderReprository extends JpaRepository<Order, Long>{

}
