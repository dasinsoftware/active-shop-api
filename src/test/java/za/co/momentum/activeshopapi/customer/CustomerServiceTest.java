package za.co.momentum.activeshopapi.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.momentum.activeshopapi.exceptions.InsufficientPointsException;
import za.co.momentum.activeshopapi.exceptions.ResourceNotFoundException;
import za.co.momentum.activeshopapi.product.Product;
import za.co.momentum.activeshopapi.product.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    ProductService productService;
    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void given_Nonexistent_Customer_throwException(){
        when(customerRepository.findById(800L)).thenThrow(new ResourceNotFoundException("Customer"));
        Assertions.assertThrows(ResourceNotFoundException.class,() ->
            customerService.addToCart(800L, PurchaseRequest.builder().build())
        );
    }

    @Test
    void given_InsufficientPoints_throwException(){
        when(customerRepository.findById(3L))
                               .thenReturn(Optional.of(Customer.builder()
                                                               .points(5L)
                                                               .build()));
        when(productService.getProductsByIds(Set.of(1L)))
                .thenReturn(List.of(Product.builder()
                                           .code(1L)
                                           .pointsCost(25L)
                                           .build()));
        Assertions.assertThrows(InsufficientPointsException.class,() ->
                customerService.addToCart(3L, PurchaseRequest.builder()
                                                .productIds(Set.of(1L))
                                                .build()));
    }

    @Test
    void given_Nonexistent_Product_throwException(){
        when(customerRepository.findById(3L))
                .thenReturn(Optional.of(Customer.builder()
                        .points(5L)
                        .build()));
        when(productService.getProductsByIds(Set.of(1000L)))
                .thenThrow(new ResourceNotFoundException("Product"));
        Assertions.assertThrows(ResourceNotFoundException.class,() ->
                customerService.addToCart(3L, PurchaseRequest.builder()
                        .productIds(Set.of(1000L))
                        .build()));
    }

    @Test
    void given_Customer_PurchaseRequest_Subtractpoints_From_Balance(){
        when(customerRepository.findById(3L))
                .thenReturn(Optional.of(Customer.builder()
                        .points(500L)
                        .build()));
        when(productService.getProductsByIds(Set.of(1L)))
                .thenReturn(List.of(Product.builder()
                        .code(1L)
                        .pointsCost(25L)
                        .build()));
        when(customerRepository.save(any())).thenReturn(Customer.builder().points(475L).build());
        var modifiedCustomer = customerService.addToCart(3L, PurchaseRequest.builder()
                .productIds(Set.of(1L))
                .build());
        Assertions.assertEquals(475L, modifiedCustomer.getPoints());
    }
}
