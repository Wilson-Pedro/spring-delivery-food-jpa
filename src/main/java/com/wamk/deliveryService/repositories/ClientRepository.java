package com.wamk.deliveryService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wamk.deliveryService.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
