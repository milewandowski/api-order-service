package com.lewandowski.apiorderservice.order.domain.port;

import com.lewandowski.apiorderservice.order.domain.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "payment-service", url = "http://localhost:8081")
public interface PaymentServiceClient {

    @PostMapping(value = "/payment")
    Order makePayment(Order order);
}
