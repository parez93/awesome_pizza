package com.parezzan.awesome_pizza.features.order.service;

import com.parezzan.awesome_pizza.features.order.dto.request.SaveOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.request.UpdateOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.response.FullOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.SaveOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.UpdateOrderResponse;
import com.parezzan.awesome_pizza.features.order.entity.PizzaOrder;
import com.parezzan.awesome_pizza.features.order.entity.PizzaOrderStatusEnum;
import com.parezzan.awesome_pizza.features.order.mapping.PizzaOrderMapper;
import com.parezzan.awesome_pizza.features.order.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.parezzan.awesome_pizza.aspects.log.Loggable;


@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Loggable
    public SaveOrderResponse createOrder(SaveOrderRequest request) {
        PizzaOrder pizzaOrder = PizzaOrderMapper.saveOrderRequestToPizzaOrder(request);
        PizzaOrder pizzaOrderSaved = orderRepository.save(pizzaOrder);
        return PizzaOrderMapper.pizzaOrderToSaveOrderResponse(pizzaOrderSaved);
    }

    @Loggable
    public UpdateOrderResponse updateOrderStatus(String orderId) {
        PizzaOrder pizzaOrder = orderRepository.findById(UUID.fromString(orderId)).orElseThrow(() -> new IllegalArgumentException("Order not found"));

        pizzaOrder.setStatus(PizzaOrderStatusEnum.next(pizzaOrder.getStatus()).getValue());
        PizzaOrder pizzaOrderUpdated =  orderRepository.save(pizzaOrder);
        return PizzaOrderMapper.pizzaOrderToUpdateOrderResponse(pizzaOrderUpdated);
    }

    @Loggable
    public UpdateOrderResponse updateOrder(UpdateOrderRequest request) {
        PizzaOrder pizzaOrder = orderRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        BeanUtils.copyProperties(request, pizzaOrder);
        PizzaOrder pizzaOrderUpdated =  orderRepository.save(pizzaOrder);
        return PizzaOrderMapper.pizzaOrderToUpdateOrderResponse(pizzaOrderUpdated);
    }

    @Loggable
    public Boolean deleteOrder(String orderId) {
        PizzaOrder pizzaOrder = orderRepository.findById(UUID.fromString(orderId)).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderRepository.delete(pizzaOrder);
        return Boolean.TRUE;
    }


    @Loggable
    public List<FullOrderResponse> getAllOrders(Optional<String> customerName, boolean orderAsc) {
        List<PizzaOrder> pizzaOrders;
        Sort sorting = Sort.by(orderAsc ? Sort.Direction.ASC : Sort.Direction.DESC, "updatedAt");
        if(customerName.isEmpty() || customerName.get().isEmpty()) {
            pizzaOrders = orderRepository.findAll(sorting);
        } else {
            pizzaOrders = orderRepository.findAllByCustomerName(customerName.get(), sorting);
        }
        return PizzaOrderMapper.pizzaOrderToFullOrderResponse(pizzaOrders);
    }

}
