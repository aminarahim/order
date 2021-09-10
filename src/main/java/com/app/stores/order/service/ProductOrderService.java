package com.app.stores.order.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.stores.order.dto.ProductsorderedDTO;
import com.app.stores.order.entity.Productsorder;
import com.app.stores.order.exception.StoresException;
import com.app.stores.order.repository.ProductsOrderRepo;

@Service
@Transactional
public class ProductOrderService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductsOrderRepo orderproRepo;

	public List<ProductsorderedDTO> getProductById(String prodid) throws StoresException{
		logger.info("Productname request for product {}", prodid);
		Iterable<Productsorder> proord = orderproRepo.findByProdid(prodid);
		List<ProductsorderedDTO> proorderDTO = new ArrayList<ProductsorderedDTO>();
		proord.forEach(pord -> {
			proorderDTO.add(ProductsorderedDTO.valueOf(pord));
		});
		logger.info("Productname for product : {}", proord);
		return proorderDTO;
	}
}
