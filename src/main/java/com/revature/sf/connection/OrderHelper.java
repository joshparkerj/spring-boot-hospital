package com.revature.sf.connection;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import com.revature.boot.domain.DomainOrder;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Order;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;

public class OrderHelper {

	EnterpriseConnection connection;

	OrderHelper(EnterpriseConnection c) {
		connection = c;
	}

	public List<DomainOrder> getAllOrders() {
		List<DomainOrder> orderList = new LinkedList<DomainOrder>();
		try {
			QueryResult qr = connection.query(
					"SELECT Id, Name, AccountId, CustomerAuthorizedById, Description, Status, TotalAmount, Type FROM Order");
			boolean done = false;
			while (qr.getSize() > 0 && !done) {
				SObject[] records = qr.getRecords();
				for (SObject so : records) {
					Order o = (Order) so;
					orderList.add(new DomainOrder(o.getId(), o.getName(), o.getAccountId(),
							o.getCustomerAuthorizedById(), o.getDescription(), o.getStatus(),
							BigDecimal.valueOf(o.getTotalAmount()), o.getType()));
				}
				done = qr.isDone();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public DomainOrder saveNewOrder(DomainOrder a) {
		Order newOrder = new Order();
		newOrder.setId(a.getId());
		newOrder.setName(a.getName());
		newOrder.setAccountId(a.getAccountid());
		newOrder.setCustomerAuthorizedById(a.getCustomerauthorizedbyid());
		newOrder.setDescription(a.getDescription());
		newOrder.setStatus(a.getStatus());
		newOrder.setTotalAmount(a.getTotalamount().doubleValue());
		newOrder.setType(a.getType());
		SObject[] sObjects = { newOrder };
		try {
			SaveResult[] sr = connection.create(sObjects);
			if (sr[0].getSuccess()) {
				a.setId(sr[0].getId());
				return a;
			}
			return null;
		} catch (ConnectionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public DomainOrder updateOrder(@Valid DomainOrder a) {
		Order newOrder = orderFromDomainOrder(a);
		SObject[] sObjects = { newOrder };
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

	private Order orderFromDomainOrder(DomainOrder a) {
		Order newOrder = new Order();
		newOrder.setAccountId(a.getAccountid());
		newOrder.setCustomerAuthorizedById(a.getCustomerauthorizedbyid());
		newOrder.setDescription(a.getDescription());
		newOrder.setId(a.getId());
		newOrder.setName(a.getName());
		newOrder.setStatus(a.getStatus());
		newOrder.setTotalAmount(a.getTotalamount().doubleValue());
		newOrder.setType(a.getType());
		return newOrder;
	}

	public DomainOrder domainOrderFromOrder(Order o) {
		return new DomainOrder(o.getId(), o.getName(), o.getAccountId(), o.getCustomerAuthorizedById(),
				o.getDescription(), o.getStatus(), BigDecimal.valueOf(o.getTotalAmount()), o.getType());
	}

}
