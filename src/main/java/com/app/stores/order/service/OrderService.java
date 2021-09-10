package com.app.stores.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.stores.order.dto.OrderDTO;
import com.app.stores.order.entity.Order;
import com.app.stores.order.exception.StoresException;
import com.app.stores.order.repository.OrderRepository;
import com.app.stores.order.repository.ReorderRepository;

@Service
@Transactional
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderRepository orderrepo;

	@Autowired
	ReorderRepository reorderRepo;

	// Get specific order by id
	public List<OrderDTO> getSpecificOrder(String orderid) throws StoresException {

		logger.info("Order details of Id {}", orderid);

		Iterable<Order> order = orderrepo.findByOrderid(orderid);
		List<OrderDTO> orderDTO = new ArrayList<OrderDTO>();

		order.forEach(ord -> {
			orderDTO.add(OrderDTO.valueOf(ord));
		});
		if (orderDTO.isEmpty())
			throw new StoresException("Service.ORDERS_NOT_FOUND");
		logger.info("{}", orderDTO);
		return orderDTO;
	}

	// Get all order details
	public List<OrderDTO> getAllOrder() throws StoresException {

		Iterable<Order> orders = orderrepo.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();

		orders.forEach(order -> {
			OrderDTO orderDTO = OrderDTO.valueOf(order);
			orderDTOs.add(orderDTO);
		});
		if (orderDTOs.isEmpty())
			throw new StoresException("Service.ORDERS_NOT_FOUND");
		logger.info("Order Details : {}", orderDTOs);
		return orderDTOs;
	}


	// Place order
	public String saveOrder(OrderDTO orderDTO) throws StoresException {

		Order order = orderrepo.getOrderByBuyerIdAndAddress(orderDTO.getBuyerid(), orderDTO.getAddress());
		if (order != null) {
			return order.getOrderid();
		} else {
			throw new StoresException("Services.ORDER_NOT_PLACED");
		}

	}

	// Reorder
	public boolean reOrder(OrderDTO orderDTO) throws StoresException {
		logger.info("Reordering the order{}", orderDTO.getOrderid());
		Order ord = reorderRepo.findByOrderid(orderDTO.getOrderid());
		if (ord != null && ord.getOrderid().equals(orderDTO.getOrderid())) {
			return true;
		} else {
			throw new StoresException("Services.ORDER_NOT_PLACED");
		}
	}

	// Delete order
	public void deleteOrder(String orderid) throws StoresException {
		Optional<Order> ord = orderrepo.findById(orderid);
		ord.orElseThrow(() -> new StoresException("Service.ORDERS_NOT_FOUND"));
		orderrepo.deleteById(orderid);
	}
}