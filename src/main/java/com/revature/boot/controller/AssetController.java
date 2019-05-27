package com.revature.boot.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.boot.domain.DomainAsset;
import com.revature.boot.service.AssetService;

@RestController
@RequestMapping("/assets")
public class AssetController {

	@Autowired
	AssetService assetService;

	@GetMapping
	public List<DomainAsset> getAll() {
		return assetService.getAllAssets();
	}

	@GetMapping("/{id}")
	public DomainAsset getById(@PathVariable("id") String id) {
		return assetService.getById(id);
	}

	@PostMapping
	public DomainAsset add(@RequestBody @Valid DomainAsset a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return assetService.saveNewAsset(a);
	}

	@PutMapping
	public DomainAsset update(@RequestBody @Valid DomainAsset a, Errors errors) {
		if (errors.hasErrors())
			return null;
		return assetService.updateAsset(a);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") String id) {
		assetService.deleteById(id);
		return "deleted!";
	}

}
