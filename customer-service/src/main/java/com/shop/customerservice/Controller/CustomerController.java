package com.shop.customerservice.Controller;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("save")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return service.saveCustomer(customer);
    }

    @PutMapping("update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return service.updateCustomer(customer);
    }

    @DeleteMapping("delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        service.deleteCustomerById(id);
    }

    @GetMapping("find/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return service.findCustomerById(id);
    }

    @GetMapping("find/all")
    public List<Customer> findAllCustomer() {
        return service.findAllCustomer();
    }
}
