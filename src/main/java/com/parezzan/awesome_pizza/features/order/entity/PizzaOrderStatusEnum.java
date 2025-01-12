package com.parezzan.awesome_pizza.features.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PizzaOrderStatusEnum {
    PENDING("PENDING"),
    IN_PROGRESS ("IN_PROGRESS"),
    COMPLETED ("COMPLETED");

    private final String value;

    public static PizzaOrderStatusEnum next(String actual) {
        return switch (actual) {
            case "PENDING" -> IN_PROGRESS;
            case "IN_PROGRESS" -> COMPLETED;
            case "COMPLETED" -> throw new IllegalArgumentException("PizzaOrderStatusEnum already completed");
            default -> throw new IllegalArgumentException("Wrong PizzaOrderStatusEnum");
        };
    }
}
