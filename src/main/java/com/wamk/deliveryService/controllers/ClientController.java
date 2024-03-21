package com.wamk.deliveryService.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wamk.deliveryService.dtos.ClientDTO;
import com.wamk.deliveryService.dtos.ClientNewDTO;
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
		if(!list.isEmpty())
			for(Client client : list) {
				Long id = client.getId();
				client.add(linkTo(methodOn(ClientController.class).findById(id)).withSelfRel());
			}
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		Client client = clientService.findById(id);
		client.add(linkTo(methodOn(ClientController.class).findAll()).withSelfRel());
		return ResponseEntity.ok().body(new ClientDTO(client));
	}
	
	@PostMapping
	public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientNewDTO clientNewDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveWithAddress(clientNewDTO));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> updateClient(@Valid @RequestBody ClientDTO clientDTO,
			@PathVariable Long id){
		var clientUpdated = clientService.update(new Client(clientDTO), id);
		return ResponseEntity.ok(new ClientDTO(clientUpdated));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable Long id){
		clientService.delete(id);
		return ResponseEntity.noContent().build();

	}
}
