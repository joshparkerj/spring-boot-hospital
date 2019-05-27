package com.revature.sf.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.validation.Valid;

import com.revature.boot.domain.DomainAccount;
import com.revature.boot.domain.DomainAsset;
import com.revature.boot.domain.DomainContact;
import com.revature.boot.domain.DomainOrder;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Asset;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.Order;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public enum EnterpriseConnectionHandler {

	INSTANCE;
	private EnterpriseConnection connection;
	private ContactHelper contactHelper;
	private AssetHelper assetHelper;
	private AccountHelper accountHelper;
	private OrderHelper orderHelper;

	private EnterpriseConnectionHandler() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("src/connection.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		String authEndPoint = props.getProperty("endpointurl");
		String securityToken = props.getProperty("securitytoken");
		try {
			ConnectorConfig config = new ConnectorConfig();
			config.setUsername(username);
			config.setPassword(password + securityToken);
			config.setAuthEndpoint(authEndPoint);
			connection = new EnterpriseConnection(config);
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
		contactHelper = new ContactHelper(connection);
		assetHelper = new AssetHelper(connection);
		accountHelper = new AccountHelper(connection);
		orderHelper = new OrderHelper(connection);
	}

	public List<DomainAccount> getAllAccounts() {
		return accountHelper.getAllAccounts();
	}

	public List<DomainAsset> getAllAssets() {
		return assetHelper.getAllAssets();
	}

	public List<DomainContact> getAllContacts() {
		return contactHelper.getAllContacts();
	}

	public List<DomainOrder> getAllOrders() {
		return orderHelper.getAllOrders();
	}

	public DomainAccount getAccountById(String id) {
		return accountHelper.domainAccountFromAccount((Account) getSObjectById(
				"SELECT Id, Name, Phone, TickerSymbol, Website, YearStarted, AnnualRevenue, Description FROM Account",
				id));
	}

	public DomainAsset getAssetById(String id) {
		return assetHelper.domainAssetFromAsset((Asset) getSObjectById(
				"SELECT Id, Name, Status, ContactId, AssetProvidedById, ParentId, RootAssetId FROM Asset", id));
	}

	public DomainContact getContactById(String id) {
		return contactHelper.domainContactFromContact((Contact) getSObjectById(
				"SELECT Id, FirstName, LastName, Phone, Email, Birthdate, AccountId FROM Contact", id));
	}

	public DomainOrder getOrderById(String id) {
		return orderHelper.domainOrderFromOrder((Order) getSObjectById(
				"SELECT Id, Name, AccountId, CustomerAuthorizedById, Description, Status, TotalAmount, Type FROM Order",
				id));
	}

	public DomainAccount saveNewAccount(DomainAccount a) {
		return accountHelper.saveNewAccount(a);
	}

	public DomainAsset saveNewAsset(DomainAsset a) {
		return assetHelper.saveNewAsset(a);
	}

	public DomainContact saveNewContact(DomainContact a) {
		return contactHelper.saveNewContact(a);
	}

	public DomainOrder saveNewOrder(DomainOrder a) {
		return orderHelper.saveNewOrder(a);
	}

	public DomainAccount updateAccount(@Valid DomainAccount a) {
		return accountHelper.updateAccount(a);
	}

	public DomainAsset updateAsset(@Valid DomainAsset a) {
		return assetHelper.updateAsset(a);
	}

	public DomainContact updateContact(@Valid DomainContact a) {
		return contactHelper.updateContact(a);
	}

	public DomainOrder updateOrder(@Valid DomainOrder a) {
		return orderHelper.updateOrder(a);
	}

	public void deleteById(String id) {
		String[] ids = { id };
		try {
			connection.delete(ids);
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}

	private SObject getSObjectById(String q, String id) {
		try {
			return connection.query(q + " Where Id = " + id).getRecords()[0];
		} catch (ConnectionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void logout() {
		try {
			connection.logout();
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
	}

}
