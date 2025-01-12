package com.parezzan.awesome_pizza.features.order.mapping;

import com.parezzan.awesome_pizza.features.order.dto.request.SaveOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.response.FullOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.SaveOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.UpdateOrderResponse;
import com.parezzan.awesome_pizza.features.order.entity.PizzaOrder;
import com.parezzan.awesome_pizza.features.order.entity.PizzaOrderStatusEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PizzaOrderMapperTest {

    @Test
    void testPizzaOrderToFullOrderResponse() {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(UUID.randomUUID());
        pizzaOrder.setCustomerName("Federico Parezzan");
        pizzaOrder.setPizzaType("Margherita");
        pizzaOrder.setStatus(PizzaOrderStatusEnum.PENDING.getValue());
        pizzaOrder.setCreatedAt(LocalDateTime.now());
        pizzaOrder.setUpdatedAt(LocalDateTime.now());

        FullOrderResponse response = PizzaOrderMapper.pizzaOrderToFullOrderResponse(pizzaOrder);

        assertThat(response).isNotNull();
        assertThat(response.getCustomerName()).isEqualTo(pizzaOrder.getCustomerName());
        assertThat(response.getPizzaType()).isEqualTo(pizzaOrder.getPizzaType());
    }

    @Test
    void testPizzaOrderToSaveOrderResponse() {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(UUID.randomUUID());
        pizzaOrder.setCustomerName("Jane Doe");
        pizzaOrder.setPizzaType("Pepperoni");
        pizzaOrder.setStatus(PizzaOrderStatusEnum.COMPLETED.getValue());
        pizzaOrder.setCreatedAt(LocalDateTime.now());
        pizzaOrder.setUpdatedAt(LocalDateTime.now());

        SaveOrderResponse response = PizzaOrderMapper.pizzaOrderToSaveOrderResponse(pizzaOrder);

        assertThat(response).isNotNull();
        assertThat(response.getCustomerName()).isEqualTo(pizzaOrder.getCustomerName());
        assertThat(response.getPizzaType()).isEqualTo(pizzaOrder.getPizzaType());
    }

    @Test
    void testSaveOrderRequestToPizzaOrder() {
        SaveOrderRequest request = new SaveOrderRequest("Michael Scott", "Veggie");

        PizzaOrder pizzaOrder = PizzaOrderMapper.saveOrderRequestToPizzaOrder(request);

        assertThat(pizzaOrder).isNotNull();
        assertThat(pizzaOrder.getCustomerName()).isEqualTo(request.getCustomerName());
        assertThat(pizzaOrder.getPizzaType()).isEqualTo(request.getPizzaType());
        assertThat(pizzaOrder.getStatus()).isEqualTo(PizzaOrderStatusEnum.PENDING.getValue());
    }

    @Test
    void testPizzaOrderToUpdateOrderResponse() {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setId(UUID.randomUUID());
        pizzaOrder.setCustomerName("Pam Beesly");
        pizzaOrder.setPizzaType("Hawaiian");
        pizzaOrder.setStatus(PizzaOrderStatusEnum.IN_PROGRESS.getValue());
        pizzaOrder.setCreatedAt(LocalDateTime.now());
        pizzaOrder.setUpdatedAt(LocalDateTime.now());

        UpdateOrderResponse response = PizzaOrderMapper.pizzaOrderToUpdateOrderResponse(pizzaOrder);

        assertThat(response).isNotNull();
        assertThat(response.getCustomerName()).isEqualTo(pizzaOrder.getCustomerName());
        assertThat(response.getPizzaType()).isEqualTo(pizzaOrder.getPizzaType());
        assertThat(response.getStatus()).isEqualTo(pizzaOrder.getStatus());
    }

    @Test
    void testPizzaOrderToFullOrderResponseList() {
        PizzaOrder order1 = new PizzaOrder();
        order1.setId(UUID.randomUUID());
        order1.setCustomerName("Jim Halpert");
        order1.setPizzaType("Cheese");
        order1.setStatus(PizzaOrderStatusEnum.COMPLETED.getValue());
        order1.setCreatedAt(LocalDateTime.now());
        order1.setUpdatedAt(LocalDateTime.now());

        PizzaOrder order2 = new PizzaOrder();
        order2.setId(UUID.randomUUID());
        order2.setCustomerName("Dwight Schrute");
        order2.setPizzaType("Pepperoni");
        order2.setStatus(PizzaOrderStatusEnum.PENDING.getValue());
        order2.setCreatedAt(LocalDateTime.now());
        order2.setUpdatedAt(LocalDateTime.now());

        List<PizzaOrder> orders = List.of(order1, order2);

        List<FullOrderResponse> responses = PizzaOrderMapper.pizzaOrderToFullOrderResponse(orders);

        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getCustomerName()).isEqualTo(order1.getCustomerName());
        assertThat(responses.get(1).getCustomerName()).isEqualTo(order2.getCustomerName());
    }
}
