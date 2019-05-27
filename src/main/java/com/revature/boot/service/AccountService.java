package com.revature.boot.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.revature.boot.domain.DomainAccount;
import com.revature.sf.connection.EnterpriseConnectionHandler;

@Service
public class AccountService {

	private EnterpriseConnectionHandler con = EnterpriseConnectionHandler.INSTANCE;

	public List<DomainAccount> getAllAccounts() {
		return con.getAllAccounts();
	}

	public DomainAccount getById(String id) {
		return con.getAccountById(id);
	}

	public DomainAccount saveNewAccount(DomainAccount a) {
		return con.saveNewAccount(a);
	}

	public void deleteById(String id) {
		con.deleteById(id);
	}

	public DomainAccount updateAccount(@Valid DomainAccount a) {
		return con.updateAccount(a);
	}

}
