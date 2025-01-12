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


public interface IOrderService {

    SaveOrderResponse createOrder(SaveOrderRequest request);

    UpdateOrderResponse updateOrderStatus(String orderId);

    UpdateOrderResponse updateOrder(UpdateOrderRequest request);

    Boolean deleteOrder(String orderId);

    List<FullOrderResponse> getAllOrders(Optional<String> customerName, boolean orderAsc);
}
