package za.co.momentum.activeshopapi.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.co.momentum.activeshopapi.GeneralControllerAdvice;
import za.co.momentum.activeshopapi.InsufficientPointsException;
import za.co.momentum.activeshopapi.ResourceNotFoundException;

import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest{
    private ObjectMapper objectMapper;
    private MockMvc mvc;
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerController customerController;
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new GeneralControllerAdvice())
                .build();
    }

    @Test
    public void given_invalid_purchase_request_return_400_error_message() throws Exception {
        mvc.perform(post("/v1/customers/1/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PurchaseRequest.builder().productIds(null).build())))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Please provide at least 1 product")));

        mvc.perform(post("/v1/customers/1/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PurchaseRequest.builder().productIds(Set.of()).build())))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("Please provide at least 1 product")));
    }

    @Test
    void given_not_existing_customer_return_NOT_FOUND() throws Exception{
        var validPurchaseRequest = PurchaseRequest.builder()
                .productIds(Set.of(1L))
                .build();
        when(customerService.addToCart(100L, validPurchaseRequest))
                            .thenThrow(new ResourceNotFoundException("Customer"));
        mvc.perform(post("/v1/customers/100/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validPurchaseRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Customer")));
    }

    @Test
    void given_purchase_points_greater_than_customers_points_return_CONFLICT() throws Exception{
        var validPurchaseRequest = PurchaseRequest.builder()
                .productIds(Set.of(1L, 2L, 3L, 4L))
                .build();
        when(customerService.addToCart(2L, validPurchaseRequest))
                .thenThrow(new InsufficientPointsException());
        mvc.perform(post("/v1/customers/2/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validPurchaseRequest)))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("Insufficient points available")));
    }

    @Test
    void given_purchase_points_less_or_equal_than_customers_points_return_OK() throws Exception{
        var validPurchaseRequest = PurchaseRequest.builder()
                .productIds(Set.of(4L))
                .build();
        when(customerService.addToCart(2L, validPurchaseRequest)).thenReturn(Customer.builder().build());
        mvc.perform(post("/v1/customers/2/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validPurchaseRequest)))
                .andExpect(status().isOk());
    }
}
