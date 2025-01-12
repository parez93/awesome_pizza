package com.parezzan.awesome_pizza.features.order.dto.request;

import com.parezzan.awesome_pizza.utils.ValidUuid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UpdateOrderRequest {

    @ValidUuid
    private UUID id;

    @NotBlank(message = "customerName cannot be null or blank")
    private String customerName;

    @NotBlank(message = "pizzaType cannot be null or blank")
    private String pizzaType;

    @NotBlank(message = "status cannot be null or blank")
    private String status; // PENDING, IN_PROGRESS, COMPLETED

    @NotNull(message = "createdAt cannot be null")
    private LocalDateTime createdAt;

    @NotNull(message = "updatedAt cannot be null")
    private LocalDateTime updatedAt;


}
