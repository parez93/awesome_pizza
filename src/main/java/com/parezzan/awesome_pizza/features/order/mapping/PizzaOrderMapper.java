package com.parezzan.awesome_pizza.features.order.mapping;

import com.parezzan.awesome_pizza.features.order.dto.request.SaveOrderRequest;
import com.parezzan.awesome_pizza.features.order.dto.response.FullOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.SaveOrderResponse;
import com.parezzan.awesome_pizza.features.order.dto.response.UpdateOrderResponse;
import com.parezzan.awesome_pizza.features.order.entity.PizzaOrder;
import com.parezzan.awesome_pizza.features.order.entity.PizzaOrderStatusEnum;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class PizzaOrderMapper {


    public static List<FullOrderResponse> pizzaOrderToFullOrderResponse(List<PizzaOrder> pizzaOrders) {
        return pizzaOrders.stream().map(PizzaOrderMapper::pizzaOrderToFullOrderResponse).toList();
    }

    public static FullOrderResponse pizzaOrderToFullOrderResponse(PizzaOrder pizzaOrder) {
        FullOrderResponse fullOrderResponse = new FullOrderResponse();
        BeanUtils.copyProperties(pizzaOrder, fullOrderResponse);
        return fullOrderResponse;
    }

    public static SaveOrderResponse pizzaOrderToSaveOrderResponse(PizzaOrder pizzaOrder){
        SaveOrderResponse saveOrderResponse = new SaveOrderResponse();
        BeanUtils.copyProperties(pizzaOrder, saveOrderResponse);
        return saveOrderResponse;
    }

    public static PizzaOrder saveOrderRequestToPizzaOrder(SaveOrderRequest pizzaOrder){
        PizzaOrder response = new PizzaOrder();
        BeanUtils.copyProperties(pizzaOrder, response);
        response.setStatus(PizzaOrderStatusEnum.PENDING.getValue());
        return response;
    }

    public static UpdateOrderResponse pizzaOrderToUpdateOrderResponse(PizzaOrder pizzaOrder){
        UpdateOrderResponse updateOrderResponse = new UpdateOrderResponse();
        BeanUtils.copyProperties(pizzaOrder, updateOrderResponse);
        return updateOrderResponse;
    }

}
