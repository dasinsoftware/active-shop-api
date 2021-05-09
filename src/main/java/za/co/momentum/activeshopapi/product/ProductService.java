package za.co.momentum.activeshopapi.product;

import org.springframework.stereotype.Component;
import za.co.momentum.activeshopapi.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProductsByIds(Set<Long> ids) throws ResourceNotFoundException {
        var products = productRepository.findAllById(ids);
        if (products.size() == ids.size()){
            return products;
        }else{
            throw new ResourceNotFoundException("Product");
        }
    }
}
