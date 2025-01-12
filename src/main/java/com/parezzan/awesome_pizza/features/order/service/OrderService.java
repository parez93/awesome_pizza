package com.parezzan.awesome_pizza.service;

import com.parezzan.awesome_pizza.entity.Order;
import com.parezzan.awesome_pizza.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String customerName, String pizzaType) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setPizzaType(pizzaType);
        order.setStatus("PENDING");
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
