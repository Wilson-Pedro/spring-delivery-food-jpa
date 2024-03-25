package com.wamk.deliveryService.exceptions;

import com.wamk.deliveryService.services.OrderService;
import com.wamk.deliveryService.services.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OrderExceptions {

    @Autowired
    OrderService orderService;

    @Test
    void entityNotFoundException() {

        assertThrows(EntityNotFoundException.class, () -> orderService.findById(70L));
    }
}
