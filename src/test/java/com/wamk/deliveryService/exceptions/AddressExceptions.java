package com.wamk.deliveryService.exceptions;

import com.wamk.deliveryService.services.AddressService;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AddressExceptions {

    @Autowired
    AddressService addressService;

    @Test
    void entityNotFoundException() {

        assertThrows(EntityNotFoundException.class, () -> addressService.findById(70L));
    }
}
