package com.wamk.deliveryService.services;

import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.entities.Client;
import com.wamk.deliveryService.entities.Order;
import com.wamk.deliveryService.entities.enums.OrderStatus;
import com.wamk.deliveryService.repositories.AddressRepository;
import com.wamk.deliveryService.repositories.ClientRepository;
import com.wamk.deliveryService.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

    Order order = new Order(null, "Refrigerante", 2, 2.50, OffsetDateTime.now(), null, OrderStatus.READY, null);

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    void save() {
        assertEquals(0, orderRepository.count());

        Order orderSaved = orderService.save(order);

        assertNotNull(orderSaved.getId());
        assertEquals("Refrigerante", orderSaved.getNameOrder());
        assertEquals(2, orderSaved.getQuantity());
        assertEquals(2.50, orderSaved.getPrice());
        assertEquals(OrderStatus.READY, orderSaved.getStatus());
        assertEquals(1, orderRepository.count());
    }

    @Test
    void findAll() {
        orderRepository.save(order);

        List<Order> list = orderService.findAll();

        assertEquals(list.size(), 1);
        assertEquals(list.size(), orderRepository.count());
    }

    @Test
    void findById() {
        orderRepository.save(order);

        Long id = orderRepository.findAll().get(0).getId();

        Order orderFinded = orderService.findById(id);
        assertEquals("Refrigerante", orderFinded.getNameOrder());
        assertEquals(2, orderFinded.getQuantity());
        assertEquals(2.50, orderFinded.getPrice());
        assertEquals(OrderStatus.READY, orderFinded.getStatus());
    }

    @Test
    void update() {
        orderRepository.save(order);
        order.setQuantity(5);

        Long id = orderRepository.findAll().get(0).getId();

        Order orderUpdated = orderService.update(order, id);

        assertEquals(5, orderUpdated.getQuantity());
    }

//    @Test
//    @Transactional
//    void delete() {
////        addressRepository.save(address);
////        clientRepository.save(client);
//        orderRepository.save(order);
//
//        assertEquals(1, orderRepository.count());
//
//        Long id = orderRepository.findAll().get(0).getId();
//
//        orderService.findById(id);
//
//        assertEquals(0, orderRepository.count());
//    }

    @Test
    void finish() {
        orderRepository.save(order);

        Long id = orderRepository.findAll().get(0).getId();

        orderService.finish(id);

        Order orderFinished = orderService.findById(id);
        assertEquals(OrderStatus.FINISHED, orderFinished.getStatus());
    }
}