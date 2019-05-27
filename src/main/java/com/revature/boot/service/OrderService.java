package com.revature.boot.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import com.revature.boot.domain.DomainOrder;
import com.revature.sf.connection.EnterpriseConnectionHandler;

@Service
public class OrderService {

	private EnterpriseConnectionHandler con = EnterpriseConnectionHandler.INSTANCE;

	public List<DomainOrder> getAllOrders() {
		return con.getAllOrders();
	}

	public DomainOrder getById(String id) {
		return con.getOrderById(id);
	}

	public DomainOrder saveNewOrder(DomainOrder a) {
		return con.saveNewOrder(a);
	}

	public void deleteById(String id) {
		con.deleteById(id);
	}

	public DomainOrder updateOrder(@Valid DomainOrder a) {
		return con.updateOrder(a);
	}

}
