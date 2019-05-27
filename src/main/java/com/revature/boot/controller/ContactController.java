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
import com.revature.boot.domain.DomainContact;
import com.revature.boot.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	ContactService contactService;

	@GetMapping
	public List<DomainContact> getAll() {
		return contactService.getAllContacts();
	}

	@GetMapping("/workers")
	public List<DomainContact> getWorkers() {
		return contactService.getWorkers();
	}
	
	@GetMapping("/patients")
	public List<DomainContact> getPatients() {
		return contactService.getPatients();
	}

	@GetMapping("/{id}")
	public DomainContact getById(@PathVariable("id") String id) {
		return contactService.getById(id);
	}

	@PostMapping
	public DomainContact add(@RequestBody @Valid DomainContact a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return contactService.saveNewContact(a);
	}

	@PutMapping
	public DomainContact update(@RequestBody @Valid DomainContact a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return contactService.updateContact(a);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") String id) {
		contactService.deleteById(id);
		return "deleted!";
	}

}
