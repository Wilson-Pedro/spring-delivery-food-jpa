package com.wamk.deliveryService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.deliveryService.entities.Client;
import com.wamk.deliveryService.repositories.ClientRepository;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found: " + id));	
	}

	public void delete(Client client) {
		clientRepository.delete(client);
	}
}
