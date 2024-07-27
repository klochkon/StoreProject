package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.OrderBody;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBodyRepository extends JpaRepository<OrderBody, Long> {
}
