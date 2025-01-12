package com.parezzan.awesome_pizza.features.order.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveOrderRequest {

    private String customerName;
    private String pizzaType;

}
