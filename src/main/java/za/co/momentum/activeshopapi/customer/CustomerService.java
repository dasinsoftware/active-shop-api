package za.co.momentum.activeshopapi.customer;

import za.co.momentum.activeshopapi.ResourceNotFoundException;

public interface CustomerService {
    Customer addToCart(Long customerId, PurchaseRequest purchaseRequest) throws ResourceNotFoundException;
}
