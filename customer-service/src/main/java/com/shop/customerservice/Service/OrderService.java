package com.shop.customerservice.Service;

import com.shop.customerservice.DTO.OrderDublicateDTO;
import com.shop.customerservice.Model.Order;
import com.shop.customerservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    @KafkaListener(topics = "order-topic", groupId = "${spring.kafka.consumer-groups.order-group.group-id}")
    public Order saveOrder(OrderDublicateDTO orderDublicateDTO) {
        Order order;

        order = Order.builder()
                .id(orderDublicateDTO.getId())
                .cart(orderDublicateDTO.getCart())
                .customerId(orderDublicateDTO.getCustomerId())
                .cost(orderDublicateDTO.getCost())
                .build();

        return repository.save(order);
    }

    public Order updateOrder(Order order) {
        return repository.save(order);
    }

    public void deleteOrderById(Long id) {
        repository.deleteById(id);
    }

    public Order findOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Order> findAllByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }
}



