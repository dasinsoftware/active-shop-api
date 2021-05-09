package za.co.momentum.activeshopapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import za.co.momentum.activeshopapi.customer.Customer;
import za.co.momentum.activeshopapi.customer.CustomerRepository;
import za.co.momentum.activeshopapi.product.Product;
import za.co.momentum.activeshopapi.product.ProductRepository;

import java.util.Set;

@SpringBootApplication
public class ActiveShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveShopApiApplication.class, args);
	}

}
