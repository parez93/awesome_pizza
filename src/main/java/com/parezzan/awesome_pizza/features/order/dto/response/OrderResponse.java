package com.parezzan.awesome_pizza.features.order.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID id;
    private String customerName;
    private String pizzaType;
    private String status; // PENDING, IN_PROGRESS, COMPLETED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
