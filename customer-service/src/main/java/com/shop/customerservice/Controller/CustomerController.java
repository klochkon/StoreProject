package com.shop.customerservice.Controller;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("save")
    public Customer saveUser(@RequestBody Customer customer) {
        return service.saveUser(customer);
    }

    @PutMapping("update")
    public Customer updateUser(@RequestBody Customer customer) {
        return service.updateUser(customer);
    }

    @DeleteMapping("delete")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
    }


}
