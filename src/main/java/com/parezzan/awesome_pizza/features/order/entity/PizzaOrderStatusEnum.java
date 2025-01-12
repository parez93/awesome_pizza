package com.parezzan.awesome_pizza.features.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    PENDING, IN_PROGRESS, COMPLETED;

}
