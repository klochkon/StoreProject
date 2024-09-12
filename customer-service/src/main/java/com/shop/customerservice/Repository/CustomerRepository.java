package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

}

