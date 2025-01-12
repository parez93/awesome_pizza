package com.parezzan.awesome_pizza.features.order.controller;

import com.parezzan.awesome_pizza.features.order.dto.request.SaveOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.request.UpdateOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.response.FullOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.SaveOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.UpdateOrderResponse;
import com.parezzan.awesome_pizza.features.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<SaveOrderResponse> createOrder(@Valid @RequestBody SaveOrderRequest request) {
        try {
            SaveOrderResponse pizzaOrder = orderService.createOrder(request);
            return new ResponseEntity<>(pizzaOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<UpdateOrderResponse> updateOrderStatus(@Valid @NotBlank @PathVariable String id) {
        try {
            UpdateOrderResponse pizzaOrder = orderService.updateOrderStatus(id);
            return new ResponseEntity<>(pizzaOrder, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateOrderResponse> updateOrder(@Valid @RequestBody UpdateOrderRequest request) {
        try {
            UpdateOrderResponse pizzaOrder = orderService.updateOrder(request);
            return new ResponseEntity<>(pizzaOrder, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Boolean> deleteOrder(@Valid @NotBlank @PathVariable String id) {
        try {
            Boolean result = orderService.deleteOrder(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<List<FullOrderResponse>> getAllOrders(@RequestParam(required = false) Optional<String> customerName, @RequestParam(defaultValue = "false") boolean orderAsc) {
        try {
            return new ResponseEntity<>(orderService.getAllOrders(customerName, orderAsc), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseStatusException handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder strBuilder = new StringBuilder();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();

            } catch (ClassCastException ex) {
                fieldName = error.getObjectName();
            }
            String message = error.getDefaultMessage();
            strBuilder.append(String.format("%s: %s\n", fieldName, message));
        });
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, strBuilder.substring(0, strBuilder.length()-1));
    }
}
