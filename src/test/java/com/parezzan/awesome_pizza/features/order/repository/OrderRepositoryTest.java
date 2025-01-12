package com.parezzan.awesome_pizza.features.order.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.parezzan.awesome_pizza.features.order.entity.PizzaOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private PizzaOrder testOrder1;
    private PizzaOrder testOrder2;

    @BeforeEach
    void setUp() {
        testOrder1 = new PizzaOrder();
        testOrder1.setCustomerName("Federico Parezzan");
        testOrder1.setPizzaType("Margherita");
        testOrder1.setStatus("PENDING");
        testOrder1.setCreatedAt(LocalDateTime.now().minusDays(2));
        testOrder1.setUpdatedAt(LocalDateTime.now().minusDays(1));

        testOrder2 = new PizzaOrder();
        testOrder2.setCustomerName("Federico Parezzan");
        testOrder2.setPizzaType("Cappricciosa");
        testOrder2.setStatus("IN_PROGRESS");
        testOrder2.setCreatedAt(LocalDateTime.now().minusDays(3));
        testOrder2.setUpdatedAt(LocalDateTime.now().minusDays(2));

        orderRepository.save(testOrder1);
        orderRepository.save(testOrder2);
    }

    @Test
    void testFindAllByCustomerName_WithSorting() {
        List<PizzaOrder> ordersAsc = orderRepository.findAllByCustomerName("Federico Parezzan", Sort.by("updatedAt").ascending());
        List<PizzaOrder> ordersDesc = orderRepository.findAllByCustomerName("Federico Parezzan", Sort.by("updatedAt").descending());

        assertThat(ordersAsc).hasSize(2);
        assertThat(ordersAsc.get(0).getUpdatedAt()).isBefore(ordersAsc.get(1).getUpdatedAt());

        assertThat(ordersDesc).hasSize(2);
        assertThat(ordersDesc.get(0).getUpdatedAt()).isAfter(ordersDesc.get(1).getUpdatedAt());
    }

    @Test
    void testFindAllByCustomerNameAsc() {
        List<PizzaOrder> orders = orderRepository.findAllByCustomerNameAsc("Federico Parezzan");
        assertThat(orders).hasSize(2);
        assertThat(orders.get(0).getUpdatedAt()).isBefore(orders.get(1).getUpdatedAt());
    }

    @Test
    void testFindAllByCustomerNameDesc() {
        List<PizzaOrder> orders = orderRepository.findAllByCustomerNameDesc("Federico Parezzan");
        assertThat(orders).hasSize(2);
        assertThat(orders.get(0).getUpdatedAt()).isAfter(orders.get(1).getUpdatedAt());
    }


    @Test
    void testFindAllByCustomerName_NoResults() {
        List<PizzaOrder> orders = orderRepository.findAllByCustomerName("NonExistingCustomer", Sort.by("updatedAt").ascending());
        assertThat(orders).isEmpty();
    }

}
