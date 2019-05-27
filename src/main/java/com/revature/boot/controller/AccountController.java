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

import com.revature.boot.domain.DomainAccount;
import com.revature.boot.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping
	public List<DomainAccount> getAll() {
		return accountService.getAllAccounts();
	}

	@GetMapping("/{id}")
	public DomainAccount getById(@PathVariable("id") String id) {
		return accountService.getById(id);
	}

	@PostMapping
	public DomainAccount add(@RequestBody @Valid DomainAccount a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return accountService.saveNewAccount(a);
	}

	@PutMapping
	public DomainAccount update(@RequestBody @Valid DomainAccount a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return accountService.updateAccount(a);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") String id) {
		accountService.deleteById(id);
		return "deleted!";
	}

}
