package com.parezzan.awesome_pizza.features.order.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class SaveOrderResponse extends OrderResponse {

    public SaveOrderResponse(UUID id, String customerName, String pizzaType, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, customerName, pizzaType, status, createdAt, updatedAt);
    }
}
