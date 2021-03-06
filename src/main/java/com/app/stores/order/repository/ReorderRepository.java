package com.app.stores.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.stores.order.entity.Order;

public interface ReorderRepository extends JpaRepository<Order, String>{
	Order findByOrderid(String orderid);
}
