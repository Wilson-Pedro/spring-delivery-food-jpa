package com.wamk.deliveryService.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.repositories.AddressRepository;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	
	public Address findById(Long id) {
		return addressRepository.findById(id).orElseThrow(
				EntityNotFoundException::new);
	}

	public Address update(Address address, Long id) {
		Address addressUpdated = findById(id);
		BeanUtils.copyProperties(address, addressUpdated);
		addressUpdated.setId(id);
		return addressRepository.save(addressUpdated);
	}

	public void delete(Long id) {
		addressRepository.delete(findById(id));
	}
}
