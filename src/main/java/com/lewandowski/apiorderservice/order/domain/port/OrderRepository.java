package com.lewandowski.apiorderservice.order.domain.port;

import com.lewandowski.apiorderservice.order.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
