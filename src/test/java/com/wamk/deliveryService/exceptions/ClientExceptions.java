package com.wamk.deliveryService.exceptions;

import com.wamk.deliveryService.dtos.ClientNewDTO;
import com.wamk.deliveryService.repositories.AddressRepository;
import com.wamk.deliveryService.repositories.ClientRepository;
import com.wamk.deliveryService.services.ClientService;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;
import com.wamk.deliveryService.services.exception.PhoneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;


    @BeforeEach
    void setup() {
        addressRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    void entityNotFoundException() {

        assertThrows(EntityNotFoundException.class, () -> clientService.findById(70L));
    }

    @Test
    void phoneException() {
        ClientNewDTO clientNewDTO = new ClientNewDTO(null, "Wilson", "(92) 11908-4422","1234-9876", "Vera Cruz", "Street of Apples", 100);
        clientService.saveWithAddress(clientNewDTO);

        assertThrows(PhoneException.class, () -> clientService.saveWithAddress(clientNewDTO));
    }
}
