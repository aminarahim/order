package com.app.stores.order.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.app.stores.order.entity.Order;

public class OrderDTO {

	String orderid;
	String buyerid;
	Double amount;
	Date dd;
	@NotNull(message = "Please provide delivery address")
	@Max(100)
	String address;
	String status;

	public OrderDTO() {
		super();
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDd() {
		return dd;
	}

	public void setDd(Date dd) {
		this.dd = dd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static OrderDTO valueOf(Order orders) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderid(orders.getOrderid());
		orderDTO.setBuyerid(orders.getBuyerid());
		orderDTO.setAmount(orders.getAmount());
		orderDTO.setDd(orders.getDd());
		orderDTO.setAddress(orders.getAddress());
		orderDTO.setStatus(orders.getStatus());
		return orderDTO;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderid=" + orderid + ", buyerid=" + buyerid + ", amount=" + amount + ", dd="
				+ dd + ", address=" + address + ", status=" + status + "]";
	}

}
