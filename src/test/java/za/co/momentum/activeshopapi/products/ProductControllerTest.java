package za.co.momentum.activeshopapi.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.co.momentum.activeshopapi.GeneralControllerAdvice;
import za.co.momentum.activeshopapi.product.Product;
import za.co.momentum.activeshopapi.product.ProductController;
import za.co.momentum.activeshopapi.product.ProductService;

import java.util.List;


import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new GeneralControllerAdvice())
                .build();
    }

    @Test
    public void given_call_to_get_products_return_OK() throws Exception {
        when(productService.getProducts()).thenReturn(List.of(Product.builder().code(1L).build()));
        mvc.perform(get("/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].code").value("1"))
        .andDo(MockMvcResultHandlers.print());
    }
}
