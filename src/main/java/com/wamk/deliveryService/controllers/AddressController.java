package com.wamk.deliveryService.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

	@PostMapping
	public ResponseEntity<Address> saveAddress(@Valid @RequestBody AddressDTO addressDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(addressService.save(new Address(addressDTO)));
	}

	@GetMapping
	public ResponseEntity<List<Address>> findAll(){
		List<Address> list = addressService.findAll();
		if(!list.isEmpty()) {
			for(Address ads : list) {
				Long id = ads.getId();
				ads.add(linkTo(methodOn(AddressController.class).findById(id)).withSelfRel());
			}
		}
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> findById(@PathVariable Long id){
		Address address = addressService.findById(id);
		address.add(linkTo(methodOn(AddressController.class).findAll()).withSelfRel());
		return ResponseEntity.ok().body(new AddressDTO(address));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> updateAddress(@Valid @RequestBody AddressDTO addressDTO,
			@PathVariable Long id){
		Address addressUpdated = addressService.update(new Address(addressDTO), id);
		return ResponseEntity.ok(new AddressDTO(addressUpdated));
	}
}
