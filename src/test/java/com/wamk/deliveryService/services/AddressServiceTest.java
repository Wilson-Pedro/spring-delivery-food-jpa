package com.wamk.deliveryService.services;

import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceTest {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressRepository addressRepository;

    Address address = new Address(null, "1234-9876", "Vera Cruz", "Street of Apples", 100);

    @BeforeEach
    void setup() {
        addressRepository.deleteAll();
    }

    @Test
    void save() {
        assertEquals(0, addressRepository.count());

        Address addressSaved = addressService.save(address);

        assertEquals("1234-9876", addressSaved.getCep());
        assertEquals("Vera Cruz", addressSaved.getNeighborhood());
        assertEquals("Street of Apples", addressSaved.getStreet());
        assertEquals(100, addressSaved.getHouseNumber());
        assertEquals(1, addressRepository.count());
    }

    @Test
    void findAll() {
        addressRepository.save(address);

        List<Address> list = addressService.findAll();

        assertEquals(1, list.size());
        assertEquals(list.size(), addressRepository.count());
    }

    @Test
    void findById() {
        addressRepository.save(address);

        Long id = addressRepository.findAll().get(0).getId();

        Address addressFinded = addressService.findById(id);

        assertEquals("1234-9876", addressFinded.getCep());
        assertEquals("Vera Cruz", addressFinded.getNeighborhood());
        assertEquals("Street of Apples", addressFinded.getStreet());
        assertEquals(100, addressFinded.getHouseNumber());
    }

    @Test
    void update() {
        addressRepository.save(address);
        address.setHouseNumber(900);

        Long id = addressRepository.findAll().get(0).getId();

        Address addressUpdated = addressService.update(address, id);

        assertEquals(900, addressUpdated.getHouseNumber());
        assertEquals(1, addressRepository.count());
    }

    @Test
    void delete() {
        addressRepository.save(address);
        assertEquals(1, addressRepository.count());

        Long id = addressRepository.findAll().get(0).getId();

        addressService.delete(id);

        assertEquals(0, addressRepository.count());
    }
}