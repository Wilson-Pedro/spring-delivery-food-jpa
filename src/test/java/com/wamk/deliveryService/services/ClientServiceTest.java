package com.wamk.deliveryService.services;

import com.wamk.deliveryService.dtos.ClientNewDTO;
import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.entities.Client;
import com.wamk.deliveryService.repositories.AddressRepository;
import com.wamk.deliveryService.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

    Address address = new Address(null, "1234-9876", "Vera Cruz", "Street of Apples", 100);

    Client client = new Client(null, "Wilson", "(92) 11908-4422", address);

    @BeforeEach
    void setup() {
        addressRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    @Transactional
    void save() {
        assertEquals(0, addressRepository.count());
        ClientNewDTO clientNewDTO = new ClientNewDTO(null, "Wilson", "(92) 11908-4422","1234-9876", "Vera Cruz", "Street of Apples", 100);

        Client clientSaved = clientService.saveWithAddress(clientNewDTO);

        assertNotNull(clientSaved.getId());
        assertEquals("Wilson", clientSaved.getName());
        assertEquals("(92) 11908-4422", clientSaved.getPhone());
        assertEquals(1, addressRepository.count());
    }

    @Test
    @Transactional
    void findAll() {
        addressRepository.save(address);
        clientRepository.save(client);

        List<Client> list = clientService.findAll();

        assertEquals(list.size(), 1);
        assertEquals(list.size(), addressRepository.count());
    }

    @Test
    @Transactional
    void findById() {
        addressRepository.save(address);
        clientRepository.save(client);

        Long id = clientRepository.findAll().get(0).getId();

        Client clientFinded = clientService.findById(id);

        assertEquals("Wilson", clientFinded.getName());
        assertEquals("(92) 11908-4422", clientFinded.getPhone());
    }

    @Test
    @Transactional
    void update() {
        addressRepository.save(address);
        clientRepository.save(client);
        client.setPhone("(92) 22119-5533");

        Long id = clientRepository.findAll().get(0).getId();

        Client clientUpdated = clientService.update(client, id);

        assertEquals("(92) 22119-5533", clientUpdated.getPhone());
    }

    @Test
    @Transactional
    void delete() {
        addressRepository.save(address);
        clientRepository.save(client);

        Long id = clientRepository.findAll().get(0).getId();

        clientService.delete(id);

        assertEquals(0, addressRepository.count());
    }
}