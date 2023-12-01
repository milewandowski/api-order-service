package com.lewandowski.apiorderservice.order.domain.port;

import com.lewandowski.apiorderservice.order.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public final class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentServiceClient paymentServiceClient;

    public Order get(String id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order create(Order order) {
        Order savedOrder = orderRepository.save(order);
        // TODO: error handling
        Order paidOrder = paymentServiceClient.makePayment(savedOrder);

        return orderRepository.save(paidOrder);
    }
}
