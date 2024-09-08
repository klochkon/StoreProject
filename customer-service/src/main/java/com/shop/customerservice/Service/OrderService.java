package com.shop.customerservice.Service;

import com.shop.customerservice.DTO.OrderDuplicateDTO;
import com.shop.customerservice.Model.Order;
import com.shop.customerservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    @KafkaListener(topics = "order-topic", groupId = "${spring.kafka.consumer-groups.order-group.group-id}")
    public Order saveOrder(OrderDuplicateDTO orderDuplicateDTO) {
        Order order;

        order = Order.builder()
                .id(orderDuplicateDTO.getId())
                .cart(orderDuplicateDTO.getCart())
                .customerId(orderDuplicateDTO.getCustomerId())
                .cost(orderDuplicateDTO.getCost())
                .build();

        return repository.save(order);
    }

    @CachePut(value = {"order", "allOrders"}, key = "#customer.id")
    public Order updateOrder(Order order) {
        return repository.save(order);
    }

    @CacheEvict(value = {"order", "allOrders"}, key = "#id")
    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "order", key = "#id")
    public Order findOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable(value = "allOrders")
    public List<Order> findAllByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }
}



