package com.lewandowski.apiorderservice.order.adapter.api;

import com.lewandowski.apiorderservice.order.domain.model.Address;
import com.lewandowski.apiorderservice.order.domain.model.Order;
import com.lewandowski.apiorderservice.order.domain.model.Status;
import com.lewandowski.apiorderservice.order.domain.port.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    Order get(@PathVariable String id) {
        return orderService.get(id);
    }

    @GetMapping
    List<Order> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    Order create(@RequestBody final Order order) {
        return orderService.create(order);
    }

    @GetMapping("/add")
    Order create() {
        Address address = new Address("Poland", "01-234", "Warsaw", "Aleje Jero");
        Order order = new Order();
        order.setName("Thomas Shelby");
        order.setAmount(new BigDecimal("99.87"));
        order.setStatus(Status.RECEIVED);
        order.setAddress(address);
        order.setProductIds(List.of("6564dcc54034f24941433bd4", "6564dcd24034f24941433bd5"));

        return orderService.create(order);
    }
}
