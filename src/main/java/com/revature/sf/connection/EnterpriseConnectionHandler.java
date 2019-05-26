package com.revature.sf.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import com.revature.boot.domain.DomainContact;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public enum EnterpriseConnectionHandler {

	INSTANCE;

	private EnterpriseConnection connection;
	private String authEndPoint = "";
	private String username = "";
	private String password = "";
	private String securityToken = "";
	private ContactHelper contactHelper;

	private EnterpriseConnectionHandler() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("src/connection.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.username = props.getProperty("username");
		this.password = props.getProperty("password");
		this.authEndPoint = props.getProperty("endpointurl");
		this.securityToken = props.getProperty("securitytoken");
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
	}

	public List<DomainContact> getAllContacts() {
		return contactHelper.getAllContacts();
	}

	public void logout() {
		try {
			connection.logout();
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
	}

	public void deleteContactById(String id) {

	}

	public DomainContact getContactById(String id) {
		return null;
	}

	public DomainContact saveNewContact(DomainContact a) {
		return null;
	}

}
