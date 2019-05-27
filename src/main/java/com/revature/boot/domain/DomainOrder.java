package com.revature.boot.domain;

import java.math.BigDecimal;

public class DomainOrder {

	String id;
	String name;
	String accountid;
	String customerauthorizedbyid;
	String description;
	String status;
	BigDecimal totalamount;
	String type;

	public DomainOrder() {
		super();
	}

	public DomainOrder(String id, String name, String accountid, String customerauthorizedbyid, String description,
			String status, BigDecimal totalamount, String type) {
		super();
		this.id = id;
		this.name = name;
		this.accountid = accountid;
		this.customerauthorizedbyid = customerauthorizedbyid;
		this.description = description;
		this.status = status;
		this.totalamount = totalamount;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getCustomerauthorizedbyid() {
		return customerauthorizedbyid;
	}

	public void setCustomerauthorizedbyid(String customerauthorizedbyid) {
		this.customerauthorizedbyid = customerauthorizedbyid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DomainOrder [id=" + id + ", name=" + name + ", accountid=" + accountid + ", customerauthorizedbyid="
				+ customerauthorizedbyid + ", description=" + description + ", status=" + status + ", totalamount="
				+ totalamount + ", type=" + type + "]";
	}

}
