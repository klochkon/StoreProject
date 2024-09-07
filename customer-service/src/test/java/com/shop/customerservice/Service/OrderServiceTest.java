package com.shop.customerservice.Service;

import com.shop.customerservice.DTO.OrderDuplicateDTO;
import com.shop.customerservice.DTO.ProductDuplicateDTO;
import com.shop.customerservice.Model.Order;
import com.shop.customerservice.Repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService service;

    @Mock
    private OrderRepository repository;

    private OrderDuplicateDTO orderDuplicateDTO;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Map<ProductDuplicateDTO, Integer> cart = new HashMap<>();


        cart.put("Name", 1);
        order = Order.builder()
                .id(1L)
                .customerId(2L)
                .cart(cart)
                .cost(new BigDecimal(1.00))
                .build();

        orderDuplicateDTO = OrderDuplicateDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .cart(order.getCart())
                .cost(order.getCost())
                .build();
    }

    @Test
    void saveOrder() {
        when(repository.save(any(Order.class))).thenReturn(order);
        Order testOrder = service.saveOrder(orderDuplicateDTO);
        assertEquals(order, testOrder);
        verify(repository, times(1)).save(any(Order.class));
    }

    @Test
    void updateOrder() {
        when(repository.save(any(Order.class))).thenReturn(order);
        Order testOrder = service.updateOrder(order);
        assertEquals(order, testOrder);
        verify(repository, times(1)).save(any(Order.class));
    }

    @Test
    void deleteOrderById() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteOrderById(order.getId());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(order));
        Order testOrder = service.findOrderById(order.getId());
        assertEquals(order, testOrder);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findAllByCustomerId() {
        List<Order> orders = List.of(order);
        when(repository.findAllByCustomerId(anyLong())).thenReturn(orders);
        List<Order> testOrders = service.findAllByCustomerId(order.getCustomerId());
        assertEquals(orders, testOrders);
        verify(repository, times(1)).findAllByCustomerId(anyLong());
    }
}