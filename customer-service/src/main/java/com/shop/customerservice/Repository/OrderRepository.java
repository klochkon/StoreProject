package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long customerId);
}
