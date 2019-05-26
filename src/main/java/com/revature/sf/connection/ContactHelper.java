package com.revature.sf.connection;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.revature.boot.domain.DomainContact;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;

public class ContactHelper {

	EnterpriseConnection connection;

	ContactHelper(EnterpriseConnection c) {
		connection = c;
	}

	public List<DomainContact> getAllContacts() {
		List<DomainContact> contactList = new LinkedList<DomainContact>();
		try {
			QueryResult qr = connection
					.query("SELECT Id, FirstName, LastName, Phone, Email, Birthdate, AccountId FROM Contact");
			boolean done = false;
			while (qr.getSize() > 0 && !done) {
				SObject[] records = qr.getRecords();
				for (SObject so : records) {
					Contact c = (Contact) so;
					Calendar bcal = c.getBirthdate();
					Date bdate = null;
					if (bcal != null)
						bdate = bcal.getTime();
					contactList.add(new DomainContact(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(),
							c.getPhone(), bdate, c.getAccountId()));
				}
				done = qr.isDone();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return contactList;
	}

}
