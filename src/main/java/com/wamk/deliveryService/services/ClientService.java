package com.wamk.deliveryService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.deliveryService.dtos.ClientNewDTO;
import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.entities.Client;
import com.wamk.deliveryService.repositories.AddressRepository;
import com.wamk.deliveryService.repositories.ClientRepository;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	@Transactional
	public Client insert(Client client) {
		addressRepository.save(client.getAddress());
		return clientRepository.save(client);
	}
	
	public List<Client> findAll(){
		return clientRepository.findAll();
	}
	
	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found: " + id));	
	}
	
	@Transactional
	public Client findBytelefone(String telefone) {
		return clientRepository.findBytelefone(telefone);
	}

	public void delete(Client client) {
		clientRepository.delete(client);
	}

	public Client fromDTO(@Valid ClientNewDTO objDTO) {
		Address adr = new Address(null, objDTO.getCEP(), objDTO.getBairro(), objDTO.getRua(), objDTO.getNumeroCasa());
		Client cli = new Client(null, objDTO.getName(), objDTO.getTelefone(), adr);
		return cli;
	}
}
