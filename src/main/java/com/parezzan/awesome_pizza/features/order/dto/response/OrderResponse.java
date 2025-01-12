package com.parezzan.awesome_pizza.features.order.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractOrderResponse {

    private Long id;
    private String customerName;
    private String pizzaType;
    private String status; // PENDING, IN_PROGRESS, COMPLETED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
