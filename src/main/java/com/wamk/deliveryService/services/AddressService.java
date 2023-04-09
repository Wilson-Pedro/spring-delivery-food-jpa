package com.wamk.deliveryService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.repositories.AddressRepository;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	
	public Address findById(Long id) {
		return addressRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found: " + id));
	}
}
