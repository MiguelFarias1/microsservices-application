package com.microservice.invectoryservice;

import com.microservice.invectoryservice.model.Inventory;
import com.microservice.invectoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class InvectoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvectoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(InventoryRepository repository) {

		return args -> {

			Inventory inventory = new Inventory();

			inventory.setSkuCode("iphone_14");
			inventory.setQuantity(100);

			Inventory inventory1 = new Inventory();

			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);

			repository.saveAll(Arrays.asList(inventory1, inventory));
		};
	}
}
