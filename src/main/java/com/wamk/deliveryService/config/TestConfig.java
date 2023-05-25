package com.wamk.deliveryService.config;

import java.time.OffsetDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wamk.deliveryService.entities.Address;
import com.wamk.deliveryService.entities.Client;
import com.wamk.deliveryService.entities.Order;
import com.wamk.deliveryService.entities.enums.OrderStatus;
import com.wamk.deliveryService.repositories.AddressRepository;
import com.wamk.deliveryService.repositories.ClientRepository;
import com.wamk.deliveryService.repositories.OrderRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Address ad1 = new Address(null, "1234-5670", "Goiabas", "Rua das flores", 10);
		Address ad2 = new Address(null, "9804-3290", "Limões", "Rua das rosas", 11);
		
		Client c1 = new Client(null, "Lucas", "98 1245-6578", ad1, 0.0);
		Client c2 = new Client(null, "Julia", "98 9935-6578", ad2, 0.0);
		
		Order o1 = new Order(null, "Refrigerante", 2.50, OffsetDateTime.now(), null, OrderStatus.READY, c1);
		Order o2 = new Order(null, "Hamburguer", 10.0, OffsetDateTime.now(), null, OrderStatus.PREPARING, c1);
		Order o3 = new Order(null, "Salada", 7.50, OffsetDateTime.parse("2023-03-21T16:49:05Z"), null,OrderStatus.SHIPPED, c2);
		
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		clientRepository.saveAll(Arrays.asList(c1, c2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		double total1 = c1.getTotal();
		double total2 = c2.getTotal();
		
		c1.setTotal(total1);
		c2.setTotal(total2);
		
		clientRepository.saveAll(Arrays.asList(c1, c2));
		
	}
	
}
