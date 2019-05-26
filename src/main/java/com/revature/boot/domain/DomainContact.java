package com.revature.boot.domain;

import java.util.Date;

public class DomainContact {

	private String id;
	private String first;
	private String last;
	private String email;
	private String phone;
	private Date birthdate;
	private String accountid;

	public DomainContact(String id, String first, String last, String email, String phone, Date birthdate,
			String accountid) {
		super();
		this.id = id;
		this.first = first;
		this.last = last;
		this.email = email;
		this.phone = phone;
		this.birthdate = birthdate;
		this.accountid = accountid;
	}

	public DomainContact() {
		super();
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	@Override
	public String toString() {
		return "First Name: " + first + ", Last Name: " + last + ", Email: " + email + ", Phone: " + phone;
	}

}
