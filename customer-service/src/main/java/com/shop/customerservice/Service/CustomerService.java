package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @CacheEvict(value = {"customer", "allCustomer"}, key = "#id")
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Cacheable(value = "customer", key = "#id")
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "allCustomer")
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }


}
