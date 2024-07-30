package com.shop.customerservice.Controller;

import com.shop.customerservice.Model.Order;
import com.shop.customerservice.Service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService service;

    @Transactional
    @PostMapping("save")
    public void saveOrder(@RequestBody Order order) {
        service.saveOrder(order);
    }

    @Transactional
    @PutMapping("update")
    public void updateOrder(Order order) {
        service.updateOrder(order);
    }

    @Transactional
    @DeleteMapping("delete/{id}")
    public void deleteOrderById(@PathVariable Long id) {
        service.deleteOrderById(id);
    }

    @GetMapping("find/{id}")
    public Order findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("find/{customerId}")
    public List<Order> findByCustomerId(@PathVariable Long customerId) {
        return service.findAllByCustomerId(customerId);
    }
}
