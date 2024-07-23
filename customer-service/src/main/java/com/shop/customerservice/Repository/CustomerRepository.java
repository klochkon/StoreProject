package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
