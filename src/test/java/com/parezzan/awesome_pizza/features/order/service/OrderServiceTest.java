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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_shouldSaveAndReturnResponse() {
        SaveOrderRequest request = new SaveOrderRequest("John Doe", "Margherita");
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(UUID.randomUUID());
        pizzaOrder.setCustomerName("John Doe");
        pizzaOrder.setPizzaType("Margherita");

        when(orderRepository.save(any(PizzaOrder.class))).thenReturn(pizzaOrder);

        SaveOrderResponse response = orderService.createOrder(request);

        assertThat(response).isNotNull();
        assertThat(response.getCustomerName()).isEqualTo("John Doe");
        verify(orderRepository, times(1)).save(any(PizzaOrder.class));
    }

    @Test
    void updateOrderStatus_shouldUpdateAndReturnResponse() {
        UUID orderId = UUID.randomUUID();
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(orderId);
        pizzaOrder.setStatus(PizzaOrderStatusEnum.PENDING.getValue());

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(pizzaOrder));
        when(orderRepository.save(any(PizzaOrder.class))).thenReturn(pizzaOrder);

        UpdateOrderResponse response = orderService.updateOrderStatus(orderId.toString());

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(PizzaOrderStatusEnum.IN_PROGRESS.getValue());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(any(PizzaOrder.class));
    }

    @Test
    void updateOrderStatus_shouldThrowExceptionWhenOrderNotFound() {
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.updateOrderStatus(orderId.toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order not found");

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(any(PizzaOrder.class));
    }

    @Test
    void updateOrder_shouldUpdateAndReturnResponse() {
        UpdateOrderRequest request = new UpdateOrderRequest(UUID.randomUUID(), "John Doe", "Pepperoni", "IN_PROGRESS", null, null);
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(request.getId());

        when(orderRepository.findById(request.getId())).thenReturn(Optional.of(pizzaOrder));
        when(orderRepository.save(any(PizzaOrder.class))).thenReturn(pizzaOrder);

        UpdateOrderResponse response = orderService.updateOrder(request);

        assertThat(response).isNotNull();
        assertThat(response.getCustomerName()).isEqualTo("John Doe");
        verify(orderRepository, times(1)).findById(request.getId());
        verify(orderRepository, times(1)).save(any(PizzaOrder.class));
    }

    @Test
    void deleteOrder_shouldDeleteOrderAndReturnTrue() {
        UUID orderId = UUID.randomUUID();
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(pizzaOrder));

        Boolean result = orderService.deleteOrder(orderId.toString());

        assertThat(result).isTrue();
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).delete(pizzaOrder);
    }

    @Test
    void deleteOrder_shouldThrowExceptionWhenOrderNotFound() {
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.deleteOrder(orderId.toString()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Order not found");

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).delete(any(PizzaOrder.class));
    }

    @Test
    void getAllOrders_shouldReturnListOfOrders() {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setCustomerName("John Doe");

        when(orderRepository.findAll(any(Sort.class))).thenReturn(List.of(pizzaOrder));

        List<FullOrderResponse> responses = orderService.getAllOrders(Optional.empty(), true);

        assertThat(responses).isNotEmpty();
        assertThat(responses.get(0).getCustomerName()).isEqualTo("John Doe");
        verify(orderRepository, times(1)).findAll(any(Sort.class));
    }

    @Test
    void getAllOrders_shouldReturnFilteredOrdersByCustomerName() {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setCustomerName("John Doe");

        when(orderRepository.findAllByCustomerName(anyString(), any(Sort.class))).thenReturn(List.of(pizzaOrder));

        List<FullOrderResponse> responses = orderService.getAllOrders(Optional.of("John Doe"), true);

        assertThat(responses).isNotEmpty();
        assertThat(responses.get(0).getCustomerName()).isEqualTo("John Doe");
        verify(orderRepository, times(1)).findAllByCustomerName(anyString(), any(Sort.class));
    }

    @Test
    void getAllOrders_shouldReturnEmptyListWhenNoOrdersFound() {
        when(orderRepository.findAll(any(Sort.class))).thenReturn(List.of());

        List<FullOrderResponse> responses = orderService.getAllOrders(Optional.empty(), true);

        assertThat(responses).isEmpty();
        verify(orderRepository, times(1)).findAll(any(Sort.class));
    }
}
