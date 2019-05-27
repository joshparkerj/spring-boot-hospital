package com.revature.boot.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.revature.boot.domain.DomainContact;
import com.revature.sf.connection.EnterpriseConnectionHandler;

@Service
public class ContactService {

	private EnterpriseConnectionHandler con = EnterpriseConnectionHandler.INSTANCE;

	public List<DomainContact> getAllContacts() {
		return con.getAllContacts();
	}

	public DomainContact getById(String id) {
		return con.getContactById(id);
	}

	public DomainContact saveNewContact(DomainContact a) {
		return con.saveNewContact(a);
	}

	public void deleteById(String id) {
		con.deleteById(id);
	}

	public DomainContact updateContact(@Valid DomainContact a) {
		return con.updateContact(a);
	}

	public List<DomainContact> getWorkers() {
		return con.getWorkers();
	}

	public List<DomainContact> getPatients() {
		return con.getPatients();
	}

}
