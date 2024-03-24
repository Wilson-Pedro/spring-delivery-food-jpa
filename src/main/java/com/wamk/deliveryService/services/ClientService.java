package com.wamk.deliveryService.services;

import java.util.List;

import com.wamk.deliveryService.services.exception.PhoneException;
import org.springframework.beans.BeanUtils;
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
	public Client saveWithAddress(ClientNewDTO clientNewDTO) {
		if(clientRepository.existsByPhone(clientNewDTO.getPhone()))
            throw new PhoneException();
		var client = fromDTO(clientNewDTO);
		addressRepository.save(client.getAddress());
		return clientRepository.save(client);
	}

	public Client update(Client client, Long id) {
		Client clientUpdated = findById(id);
		clientUpdated.setName(client.getName());
		clientUpdated.setPhone(client.getPhone());
		return clientRepository.save(clientUpdated);
	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(
				EntityNotFoundException::new);
	}

	@Transactional
	public void delete(Long id) {
		var client = findById(id);
		addressRepository.delete(client.getAddress());
		clientRepository.delete(client);
	}

	public Client fromDTO(ClientNewDTO objDTO) {
		Address adr = new Address(null, objDTO.getCep(), objDTO.getNeighborhood(), objDTO.getStreet(), objDTO.getHouseNumber());
		return new Client(null, objDTO.getName(), objDTO.getPhone(), adr);
	}
}
