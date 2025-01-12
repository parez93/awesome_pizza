package com.parezzan.awesome_pizza.features.order.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FullOrderResponse extends OrderResponse {


    public FullOrderResponse(UUID id, String customerName, String pizzaType, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, customerName, pizzaType, status, createdAt, updatedAt);
    }
}
