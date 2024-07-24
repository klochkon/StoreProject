package com.shop.customerservice.Controller;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("save")
    public Customer saveUser(@RequestBody Customer customer) {
        return service.saveCustomer(customer);
    }

    @PutMapping("update")
    public Customer updateUser(@RequestBody Customer customer) {
        return service.updateCustomer(customer);
    }

    @DeleteMapping("delete-by-id-{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteCustomerById(id);
    }

    @GetMapping("find-by-id-{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return service.findCustomerById(id);
    }

    @GetMapping("find-all")
    public List<Customer> findAllCustomer() {
        return service.findAllCustomer();
    }



}
