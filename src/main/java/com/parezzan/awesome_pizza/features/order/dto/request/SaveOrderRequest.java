package com.parezzan.awesome_pizza.features.order.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveOrderRequest {

    @NotBlank(message = "customerName cannot be null or blank")
    private String customerName;

    @NotBlank(message = "pizzaType cannot be null or blank")
    private String pizzaType;

}
