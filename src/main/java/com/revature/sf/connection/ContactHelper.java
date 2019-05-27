package com.revature.sf.connection;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import com.revature.boot.domain.DomainContact;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
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

	public DomainContact saveNewContact(@Valid DomainContact a) {
		Contact newContact = contactFromDomainContact(a);
		SObject[] sObjects = { newContact };
		try {
			SaveResult sr = connection.create(sObjects)[0];
			if (sr.getSuccess()) {
				a.setId(sr.getId());
				return a;
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DomainContact updateContact(@Valid DomainContact a) {
		Contact newContact = contactFromDomainContact(a);
		SObject[] sObjects = { newContact };
		try {
			SaveResult sr = connection.update(sObjects)[0];
			if (sr.getSuccess()) {
				a.setId(sr.getId());
				return a;
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Contact contactFromDomainContact(DomainContact a) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(a.getBirthdate());
		Contact newContact = new Contact();
		newContact.setAccountId(a.getAccountid());
		newContact.setBirthdate(gc);
		newContact.setEmail(a.getEmail());
		newContact.setFirstName(a.getFirst());
		newContact.setId(a.getId());
		newContact.setLastName(a.getLast());
		newContact.setPhone(a.getPhone());
		return newContact;
	}

	public DomainContact domainContactFromContact(Contact c) {
		return new DomainContact(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone(),
				c.getBirthdate().getTime(), c.getAccountId());
	}

}
