package com.revature.boot.domain;

import java.math.BigDecimal;

public class DomainAccount {

	String id;
	String name;
	String phone;
	String tickersymbol;
	String website;
	String yearstarted;
	BigDecimal annualrevenue;
	String description;

	public DomainAccount() {
		super();
	}

	public DomainAccount(String id, String name, String phone, String tickersymbol, String website, String yearstarted,
			BigDecimal annualrevenue, String description) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.tickersymbol = tickersymbol;
		this.website = website;
		this.yearstarted = yearstarted;
		this.annualrevenue = annualrevenue;
		this.description = description;
	}

	public String getId() {
		return this.id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTickersymbol() {
		return tickersymbol;
	}

	public void setTickersymbol(String tickersymbol) {
		this.tickersymbol = tickersymbol;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getYearstarted() {
		return yearstarted;
	}

	public void setYearstarted(String yearstarted) {
		this.yearstarted = yearstarted;
	}

	public BigDecimal getAnnualrevenue() {
		return annualrevenue;
	}

	public void setAnnualrevenue(BigDecimal annualrevenue) {
		this.annualrevenue = annualrevenue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DomainAccount [name=" + name + ", phone=" + phone + ", tickersymbol=" + tickersymbol + ", website="
				+ website + ", yearstarted=" + yearstarted + ", annualrevenue=" + annualrevenue + ", description="
				+ description + "]";
	}

}
