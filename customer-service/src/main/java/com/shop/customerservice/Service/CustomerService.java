package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public void updateCustomer(Customer customer) {
        repository.save(customer);
    }

    @CacheEvict(value = {"customer", "allCustomer"}, key = "#id")
    public void deleteCustomerById(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "customer", key = "#id")
    public Customer findCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable(value = "allCustomer")
    public List<Customer> findAllCustomer() {
        return repository.findAll();
    }


}
