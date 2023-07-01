package com.wamk.deliveryService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wamk.deliveryService.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
