package com.wamk.deliveryService.services;

import java.util.List;

import com.wamk.deliveryService.services.exception.PhoneException;
import org.jetbrains.annotations.NotNull;
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
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	@Transactional
	public Client saveWithAddress(ClientNewDTO clientNewDTO) {
		var client = fromDTO(clientNewDTO);
		if(clientRepository.existsBytelefone(client.getPhone()))
			throw new PhoneException();
		client.setId(null);
		addressRepository.save(client.getAddress());
		return clientRepository.save(client);
	}

	public Client update(Client clientUpdated, Long id) {
		Client clientFinded = findById(id);
		BeanUtils.copyProperties(clientUpdated, clientFinded);
		clientUpdated.setId(id);
		return clientRepository.save(clientUpdated);
	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Client findById(Long id) {
		return clientRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException());
	}

	public void delete(Long id) {
		clientRepository.delete(findById(id));
	}

	public Client fromDTO(ClientNewDTO objDTO) {
		Address adr = new Address(null, objDTO.getCEP(), objDTO.getNeighborhood(), objDTO.getStreet(), objDTO.getNumeroCasa());
		return new Client(null, objDTO.getName(), objDTO.getPhone(), adr);
	}
}
