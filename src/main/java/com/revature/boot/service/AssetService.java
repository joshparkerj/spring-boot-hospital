package com.revature.boot.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.revature.boot.domain.DomainAsset;
import com.revature.sf.connection.EnterpriseConnectionHandler;

@Service
public class AssetService {

	private EnterpriseConnectionHandler con = EnterpriseConnectionHandler.INSTANCE;

	public List<DomainAsset> getAllAssets() {
		return con.getAllAssets();
	}

	public DomainAsset getById(String id) {
		return con.getAssetById(id);
	}

	public DomainAsset saveNewAsset(DomainAsset a) {
		return con.saveNewAsset(a);
	}

	public void deleteById(String id) {
		con.deleteById(id);
	}

	public DomainAsset updateAsset(@Valid DomainAsset a) {
		return con.updateAsset(a);
	}

}
