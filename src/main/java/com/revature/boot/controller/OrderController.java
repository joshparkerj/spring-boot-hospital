package com.revature.boot.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.boot.domain.DomainOrder;
import com.revature.boot.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping
	public List<DomainOrder> getAll() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public DomainOrder getById(@PathVariable("id") String id) {
		return orderService.getById(id);
	}

	@PostMapping
	public DomainOrder add(@RequestBody @Valid DomainOrder a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return orderService.saveNewOrder(a);
	}

	@PutMapping
	public DomainOrder update(@RequestBody @Valid DomainOrder a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return orderService.updateOrder(a);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") String id) {
		orderService.deleteById(id);
		return "deleted!";
	}

}
