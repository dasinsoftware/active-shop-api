package za.co.momentum.activeshopapi.customer;

import org.springframework.stereotype.Component;
import za.co.momentum.activeshopapi.exceptions.ResourceNotFoundException;
import za.co.momentum.activeshopapi.exceptions.InsufficientPointsException;
import za.co.momentum.activeshopapi.product.Product;
import za.co.momentum.activeshopapi.product.ProductService;

@Component
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    public final ProductService productService;
    public CustomerServiceImpl(CustomerRepository customerRepository, ProductService productService) {
        this.customerRepository = customerRepository;
        this.productService = productService;
    }

    @Override
    public Customer addToCart(Long customerId, PurchaseRequest purchaseRequest)
            throws ResourceNotFoundException,InsufficientPointsException {
        var customer = customerRepository.findById(customerId)
                                                   .orElseThrow(() -> new ResourceNotFoundException("Customer"));
        var purchasePoints = productService.getProductsByIds(purchaseRequest.getProductIds())
                .stream()
                .mapToLong(Product::getPointsCost)
                .sum();
        if (purchasePoints > customer.getPoints())
            throw new InsufficientPointsException();
        customer.setPoints(customer.getPoints() - purchasePoints);
        return customerRepository.save(customer);
    }
}
