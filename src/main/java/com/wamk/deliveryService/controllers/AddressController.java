package com.wamk.deliveryService.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wamk.deliveryService.dtos.AddressDTO;
import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.services.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;

	@GetMapping
	public ResponseEntity<List<Address>> findAll(){
		List<Address> list = addressService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Address> findById(@PathVariable Long id){
		Address address = addressService.findById(id);
		return ResponseEntity.ok().body(address);
	}
	
	@PostMapping
	public ResponseEntity<Address> saveCleint(@Valid @RequestBody AddressDTO addressDTO){
		var address = new Address();
		BeanUtils.copyProperties(addressDTO, address);
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(address));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Address> updateAddress(@Valid @RequestBody AddressDTO addressDTO, 
			@PathVariable Long id){
		Address obj = addressService.findById(id);
		var address = new Address();
		BeanUtils.copyProperties(addressDTO, address);
		address.setId(obj.getId());
		return ResponseEntity.ok(addressService.save(address));
	}
}