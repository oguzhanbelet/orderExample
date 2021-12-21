package com.orderexample.orderexample.dao;

import com.orderexample.orderexample.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //JPQL
    @Query(value = "SELECT o FROM Order as o where o.user.id =: userId")
    Collection<Order> findOrdersByUserIdViaJPQL(@Param("userId") Long userId);

    //Native SQL
    @Query(value = "SELECT o.* FROM orders as o where o.user_id =: userId", nativeQuery = true)
    Collection<Order> findOrdersByUserIdViaNativeQuery(@Param("userId") Long userId);

    //Generate SQL from method names
    Collection<Order> findAllByUser_Id(Long id);
}
