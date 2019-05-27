package com.revature.sf.connection;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import com.revature.boot.domain.DomainAsset;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Asset;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;

public class AssetHelper {

	EnterpriseConnection connection;

	AssetHelper(EnterpriseConnection c) {
		connection = c;
	}

	public List<DomainAsset> getAllAssets() {
		List<DomainAsset> assetList = new LinkedList<DomainAsset>();
		try {
			QueryResult qr = connection
					.query("SELECT Id, Name, Status, ContactId, AssetProvidedById, ParentId, RootAssetId FROM Asset");
			boolean done = false;
			while (qr.getSize() > 0 && !done) {
				SObject[] records = qr.getRecords();
				for (SObject so : records) {
					Asset a = (Asset) so;
					assetList.add(new DomainAsset(a.getId(), a.getName(), a.getStatus(), a.getContactId(),
							a.getAssetProvidedById(), a.getParentId(), a.getRootAssetId()));
				}
				done = qr.isDone();
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return assetList;
	}

	public DomainAsset saveNewAsset(@Valid DomainAsset a) {
		Asset newAsset = assetFromDomainAsset(a);
		SObject[] sObjects = { newAsset };
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

	public DomainAsset updateAsset(@Valid DomainAsset a) {
		Asset newAsset = assetFromDomainAsset(a);
		SObject[] sObjects = { newAsset };
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

	private Asset assetFromDomainAsset(DomainAsset a) {
		Asset newAsset = new Asset();
		newAsset.setAssetProvidedById(a.getAssetprovidedbyid());
		newAsset.setContactId(a.getContactid());
		newAsset.setId(a.getId());
		newAsset.setName(a.getName());
		newAsset.setParentId(a.getParentid());
		newAsset.setRootAssetId(a.getRootassetid());
		newAsset.setStatus(a.getStatus());
		return newAsset;
	}

	public DomainAsset domainAssetFromAsset(Asset a) {
		return new DomainAsset(a.getId(), a.getName(), a.getStatus(), a.getContactId(), a.getAssetProvidedById(),
				a.getParentId(), a.getRootAssetId());
	}

}
