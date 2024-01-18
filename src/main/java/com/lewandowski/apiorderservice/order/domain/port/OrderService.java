package com.lewandowski.apiorderservice.order.domain.port;

import com.lewandowski.apiorderservice.order.domain.model.Order;
import com.lewandowski.apiorderservice.order.domain.model.Status;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public final class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentServiceClient paymentServiceClient;
    private final ShippingServiceClient shippingServiceClient;

    public Order get(String id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order create(Order order) {
        try {
            paymentServiceClient.makePayment(order);
        } catch (FeignException.FeignClientException e) {
            order.setStatus(Status.PAYMENT_FAILED);
            log.error("Payment failed for order with order id: [{}]", order.getId());
            return orderRepository.save(order);
        }

        try {
            shippingServiceClient.sendForShipment(order);
        } catch (FeignException e) {
            order.setStatus(Status.SHIPMENT_FAILED);
            log.error("Shipment failed for order with order id: [{}]", order.getId());
            return  orderRepository.save(order);
        }
        order.setStatus(Status.SENT);
        Order savedOrder = orderRepository.save(order);
        log.info("Order with id [{}] has been sent", savedOrder.getId());

        return savedOrder;
    }
}
