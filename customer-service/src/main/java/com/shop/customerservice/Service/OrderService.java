package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Order;
import com.shop.customerservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public void saveOrder(Order order) {
        repository.save(order);
    }

    public void updateOrder(Order order) {
        repository.save(order);
    }

    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

    public Order findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Order> findAllByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }
}


