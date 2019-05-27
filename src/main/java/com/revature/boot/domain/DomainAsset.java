package com.revature.boot.domain;

public class DomainAsset {

	String id;
	String name;
	String status;
	String contactid;
	String assetprovidedbyid;
	String parentid;
	String rootassetid;

	public DomainAsset() {
		super();
	}

	public DomainAsset(String id, String name, String status, String contactid, String assetprovidedbyid,
			String parentid, String rootassetid) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.contactid = contactid;
		this.assetprovidedbyid = assetprovidedbyid;
		this.parentid = parentid;
		this.rootassetid = rootassetid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContactid() {
		return contactid;
	}

	public void setContactid(String contactid) {
		this.contactid = contactid;
	}

	public String getAssetprovidedbyid() {
		return assetprovidedbyid;
	}

	public void setAssetprovidedbyid(String assetprovidedbyid) {
		this.assetprovidedbyid = assetprovidedbyid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getRootassetid() {
		return rootassetid;
	}

	public void setRootassetid(String rootassetid) {
		this.rootassetid = rootassetid;
	}

	@Override
	public String toString() {
		return "DomainAsset [id=" + id + ", name=" + name + ", status=" + status + ", contactid=" + contactid
				+ ", assetprovidedbyid=" + assetprovidedbyid + ", parentid=" + parentid + ", rootassetid=" + rootassetid
				+ "]";
	}

}
