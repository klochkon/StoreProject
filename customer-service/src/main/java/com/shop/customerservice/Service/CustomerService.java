package com.shop.customerservice.Service;

import com.shop.customerservice.DTO.CustomerDTO;
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

    private final CustomerRepository repository;

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public Customer updateCustomer(Customer customer) {
        return repository.save(customer);
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


    public CustomerDTO findCustomerEmailAndNickNameById(Long customerId) {
        Customer customer = repository.findById(customerId).orElse(null);
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setEmail(customer.getEmail());
        customerDTO.setNickName(customer.getNickName());

        return customerDTO;
    }


}
