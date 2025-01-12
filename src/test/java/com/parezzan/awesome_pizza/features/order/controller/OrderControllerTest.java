package com.parezzan.awesome_pizza.features.order.controller;

import com.parezzan.awesome_pizza.features.order.dto.request.SaveOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.request.UpdateOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.response.FullOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.SaveOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.UpdateOrderResponse;
import com.parezzan.awesome_pizza.features.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_validRequest_returnsCreatedResponse() {
        SaveOrderRequest request = new SaveOrderRequest("Federico Parezzan", "Margherita");
        SaveOrderResponse response = new SaveOrderResponse(
                UUID.fromString("b34268a1-61ea-4a8b-905f-100c86a4b97e"),
                "Federico Parezzan",
                "Margherita",
                "PENDING",
                LocalDateTime.now(),
                LocalDateTime.now());

        when(orderService.createOrder(request)).thenReturn(response);

        ResponseEntity<SaveOrderResponse> result = orderController.createOrder(request);

        assertThat(result.getStatusCode().value()).isEqualTo(201);
        assertThat(result.getBody()).isEqualTo(response);
    }


    @Test
    void updateOrderStatus_validId_returnsUpdatedResponse() {
        UUID uuid = UUID.fromString("b34268a1-61ea-4a8b-905f-100c86a4b97e");
        UpdateOrderResponse response = new UpdateOrderResponse(
                uuid,
                "Federico Parezzan",
                "Margherita",
                "PENDING",
                LocalDateTime.now(),
                LocalDateTime.now());

        when(orderService.updateOrderStatus(uuid.toString())).thenReturn(response);

        ResponseEntity<UpdateOrderResponse> result = orderController.updateOrderStatus(uuid.toString());

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(response);
    }

    @Test
    void updateOrderStatus_serviceThrowsException_throwsInternalServerError() {
        String orderId = "b34268a1-61ea-4a8b-905f-100c86a4b97e";

        when(orderService.updateOrderStatus(orderId)).thenThrow(new RuntimeException("Service error"));

        assertThrows(ResponseStatusException.class, () -> orderController.updateOrderStatus(orderId));
    }

    @Test
    void updateOrder_validRequest_returnsUpdatedResponse() {
        UUID uuid = UUID.fromString("b34268a1-61ea-4a8b-905f-100c86a4b97e");
        UpdateOrderRequest request = new UpdateOrderRequest(
                uuid,
                "Federico Parezzan",
                "Margherita",
                "PENDING",
                LocalDateTime.now(),
                LocalDateTime.now());

        UpdateOrderResponse response = new UpdateOrderResponse(
                uuid,
                "Federico Parezzan",
                "Margherita",
                "PENDING",
                LocalDateTime.now(),
                LocalDateTime.now());

        when(orderService.updateOrder(request)).thenReturn(response);

        ResponseEntity<UpdateOrderResponse> result = orderController.updateOrder(request);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(response);
    }

    @Test
    void deleteOrder_validId_returnsTrue() {
        String orderId = "b34268a1-61ea-4a8b-905f-100c86a4b97e";

        when(orderService.deleteOrder(orderId)).thenReturn(true);

        ResponseEntity<Boolean> result = orderController.deleteOrder(orderId);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isTrue();
    }


    @Test
    void updateOrder_serviceThrowsException_throwsInternalServerError() {
        UUID uuid = UUID.fromString("b34268a1-61ea-4a8b-905f-100c86a4b97e");

        UpdateOrderRequest request = new UpdateOrderRequest(
                uuid,
                "Federico Parezzan",
                "Margherita",
                "PENDING",
                LocalDateTime.now(),
                LocalDateTime.now());
        when(orderService.updateOrder(any())).thenThrow(new RuntimeException("Service error"));

        assertThrows(ResponseStatusException.class, () -> orderController.updateOrder(request));
    }

    @Test
    void deleteOrder_serviceThrowsException_throwsInternalServerError() {
        String orderId = "12345";

        when(orderService.deleteOrder(orderId)).thenThrow(new RuntimeException("Service error"));

        assertThrows(ResponseStatusException.class, () -> orderController.deleteOrder(orderId));
    }

    @Test
    void getAllOrders_noFilters_returnsOrderList() {
        UUID uuid = UUID.fromString("b34268a1-61ea-4a8b-905f-100c86a4b97e");

        List<FullOrderResponse> orders = Collections.singletonList(
                new FullOrderResponse(
                        uuid,
                        "Federico Parezzan",
                        "Margherita",
                        "PENDING",
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );

        when(orderService.getAllOrders(Optional.empty(), false)).thenReturn(orders);

        ResponseEntity<List<FullOrderResponse>> result = orderController.getAllOrders(Optional.empty(), false);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(orders);
    }

    @Test
    void getAllOrders_serviceThrowsException_throwsInternalServerError() {
        when(orderService.getAllOrders(Optional.empty(), false)).thenThrow(new RuntimeException("Service error"));

        assertThrows(ResponseStatusException.class, () -> orderController.getAllOrders(Optional.empty(), false));
    }


    @Test
    void createOrder_serviceThrowsException_throwsInternalServerError() {
        SaveOrderRequest request = new SaveOrderRequest("Federico Parezzan", "Margherita");
        when(orderService.createOrder(request)).thenThrow(new RuntimeException("Database error"));

        assertThrows(ResponseStatusException.class, () -> orderController.createOrder(request));
    }

    @Test
    void getAllOrders_withEmptyCustomerName_returnsAllOrders() {
        List<FullOrderResponse> orders = Arrays.asList(
                new FullOrderResponse(UUID.randomUUID(), "Federico", "Margherita", "PENDING", LocalDateTime.now(), LocalDateTime.now()),
                new FullOrderResponse(UUID.randomUUID(), "Jane", "Pepperoni", "IN_PROGRESS", LocalDateTime.now(), LocalDateTime.now())
        );

        when(orderService.getAllOrders(Optional.of(""), true)).thenReturn(orders);

        ResponseEntity<List<FullOrderResponse>> response = orderController.getAllOrders(Optional.of(""), true);
        assertThat(response.getBody()).hasSize(2);
    }

}
