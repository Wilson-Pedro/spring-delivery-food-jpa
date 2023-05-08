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

import com.wamk.deliveryService.dtos.ClientDTO;
import com.wamk.deliveryService.entities.Client;
import com.wamk.deliveryService.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> list = clientService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client client = clientService.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	public ResponseEntity<Client> saveCleint(@Valid @RequestBody ClientDTO clientDTO){
		var client = new Client();
		BeanUtils.copyProperties(clientDTO, client);
		client.setTotal(0.0);
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> updateClient(@Valid @RequestBody ClientDTO clientDTO, 
			@PathVariable Long id){
		Client obj = clientService.findById(id);
		var client = new Client();
		BeanUtils.copyProperties(clientDTO, client);
		client.setId(obj.getId());
		return ResponseEntity.ok(clientService.save(client));
	}
	
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Object> deleteClient(@PathVariable Long id){
//		Client client = clientService.findById(id);
//		clientService.delete(client);
//		return ResponseEntity.ok("Client deleted, id: " + id);
//		
//	}
}
