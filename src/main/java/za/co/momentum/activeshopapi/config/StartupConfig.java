package za.co.momentum.activeshopapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.co.momentum.activeshopapi.customer.Customer;
import za.co.momentum.activeshopapi.customer.CustomerRepository;
import za.co.momentum.activeshopapi.product.Product;
import za.co.momentum.activeshopapi.product.ProductRepository;

import java.util.Set;

@Configuration
public class StartupConfig {
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, CustomerRepository customerRepository) {
        return (args) -> {
            try {
                productRepository.saveAll(Set.of(
                        Product.builder().code(1L).name("Hairdryer").pointsCost(500L).build(),
                        Product.builder().code(2L).name("Toaster").pointsCost(350L).build(),
                        Product.builder().code(3L).name("Blender").pointsCost(800L).build(),
                        Product.builder().code(4L).name("Shaver").pointsCost(1000L).build(),
                        Product.builder().code(5L).name("Stapler").pointsCost(150L).build()
                ));
                customerRepository.saveAll(Set.of(
                        Customer.builder().id(1L).name("Das").points(2500L).build(),
                        Customer.builder().id(2L).name("Sheree").points(1000L).build(),
                        Customer.builder().id(3L).name("Kumaran").points(150L).build(),
                        Customer.builder().id(4L).name("Justin").points(340L).build()
                ));
            }catch(Exception ignored){}
        };
    }
}
