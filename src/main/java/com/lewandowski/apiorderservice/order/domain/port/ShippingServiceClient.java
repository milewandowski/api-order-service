package com.lewandowski.apiorderservice.order.domain.port;

import com.lewandowski.apiorderservice.order.domain.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "shipping-service", url = "http://localhost:8082")
public interface ShippingServiceClient {

    @PostMapping("/shipment")
    Order sendForShipment(Order order);
}
