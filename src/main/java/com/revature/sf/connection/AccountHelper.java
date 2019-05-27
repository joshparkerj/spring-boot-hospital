package com.revature.sf.connection;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import com.revature.boot.domain.DomainAccount;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;

public class AccountHelper {

	EnterpriseConnection connection;

	AccountHelper(EnterpriseConnection c) {
		connection = c;
	}

	public List<DomainAccount> getAllAccounts() {
		List<DomainAccount> accountList = new LinkedList<DomainAccount>();
		try {
			QueryResult qr = connection.query(
					"SELECT Id, Name, Phone, TickerSymbol, Website, YearStarted, AnnualRevenue, Description FROM Account");
			boolean done = false;
			while (qr.getSize() > 0 && !done) {
				SObject[] records = qr.getRecords();
				for (SObject so : records) {
					Account a = (Account) so;
					BigDecimal ar = null;
					Double ardouble = a.getAnnualRevenue();
					if (ardouble != null)
						ar = BigDecimal.valueOf(ardouble);
					accountList.add(
							new DomainAccount(a.getId(), a.getName(), a.getPhone(), a.getTickerSymbol(), a.getWebsite(),
									a.getYearStarted(), ar, a.getDescription()));
				}
				done = qr.isDone();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return accountList;
	}

	public DomainAccount saveNewAccount(DomainAccount a) {
		Account newAccount = fromDomainAccount(a);
		SObject[] sObjects = { newAccount };
		try {
			SaveResult[] sr = connection.create(sObjects);
			if (sr[0].getSuccess()) {
				a.setId(sr[0].getId());
				return a;
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DomainAccount updateAccount(@Valid DomainAccount a) {
		Account newAccount = fromDomainAccount(a);
		SObject[] sObjects = { newAccount };
		try {
			SaveResult[] sr = connection.update(sObjects);
			if (sr[0].getSuccess()) {
				a.setId(sr[0].getId());
				return a;
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Account fromDomainAccount(DomainAccount a) {
		Account newAccount = new Account();
		newAccount.setId(a.getId());
		newAccount.setName(a.getName());
		newAccount.setPhone(a.getPhone());
		newAccount.setTickerSymbol(a.getTickersymbol());
		newAccount.setDescription(a.getDescription());
		newAccount.setWebsite(a.getWebsite());
		newAccount.setAnnualRevenue(a.getAnnualrevenue().doubleValue());
		newAccount.setYearStarted(a.getYearstarted());
		return newAccount;
	}

	public DomainAccount domainAccountFromAccount(Account a) {
		return new DomainAccount(a.getId(), a.getName(), a.getPhone(), a.getTickerSymbol(), a.getWebsite(),
				a.getYearStarted(), BigDecimal.valueOf(a.getAnnualRevenue()), a.getDescription());
	}

}
