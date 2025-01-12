package com.parezzan.awesome_pizza.features.order.repository;

import com.parezzan.awesome_pizza.features.order.entity.PizzaOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<PizzaOrder, UUID> {

    List<PizzaOrder> findAllByCustomerName(String customerName, Sort sort);



    /*
     *  Keeping away all the Spring magic stuff and using SQL
     */
    @Query("select u from PizzaOrder u where u.customerName = ?1 order by u.updatedAt asc")
    List<PizzaOrder> findAllByCustomerNameAsc(String customerName);

    @Query("select u from PizzaOrder u where u.customerName = ?1 order by u.updatedAt desc")
    List<PizzaOrder> findAllByCustomerNameDesc(String customerName);


}
