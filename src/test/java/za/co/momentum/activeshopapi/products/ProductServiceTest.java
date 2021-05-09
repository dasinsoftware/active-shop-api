package za.co.momentum.activeshopapi.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.momentum.activeshopapi.exceptions.ResourceNotFoundException;
import za.co.momentum.activeshopapi.product.Product;
import za.co.momentum.activeshopapi.product.ProductRepository;
import za.co.momentum.activeshopapi.product.ProductService;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    @Test
    void given_Get_Products(){
        when(productRepository.findAll()).thenReturn(List.of(Product.builder().code(1L).name("Hairdryer").build()));
        Assertions.assertEquals(1,productRepository.findAll().size());
    }

    @Test
    void getProductsByIds_NonExistent_Product_throwException(){
        when(productRepository.findAllById(Set.of(100L))).thenReturn(List.of());
        Assertions.assertThrows(ResourceNotFoundException.class,() -> productService.getProductsByIds(Set.of(100L)));
    }

    @Test
    void getProductsByIds_Existent_Products_Return_Products(){
        when(productRepository.findAllById(Set.of(1L))).thenReturn(List.of(Product.builder()
                .code(1L)
                .name("Hairdryer")
                .pointsCost(3500L)
                .build()));
        var products = productService.getProductsByIds(Set.of(1L));
        Assertions.assertEquals("Hairdryer", products.get(0).getName());
    }
}
