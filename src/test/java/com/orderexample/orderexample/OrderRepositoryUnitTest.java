package com.orderexample.orderexample;

import com.orderexample.orderexample.dao.OrderRepository;
import com.orderexample.orderexample.dao.UserRepository;
import com.orderexample.orderexample.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@SpringBootTest
class OrderRepositoryUnitTest {

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Test
    void shouldSaveOrder_successfully() {
        Order order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(1000.00));
        order.setPaymentChannel(PaymentChannel.CC);
        order.setOrderDate(new Date());
        orderRepository.save(order);
        Assertions.assertThat(order.getId()).isNotNull();
        Assertions.assertThat(order.getOrderState()).isEqualTo(OrderState.CREATED);
    }

    @Test
    void shouldSaveOrderWithOrderItem_successfully(){
        Order order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(1000.00));
        order.setPaymentChannel(PaymentChannel.CC);
        order.setOrderDate(new Date());

        OrderItem pizza = new OrderItem();
        pizza.setAmount(BigDecimal.valueOf(100));
        pizza.setName("pizza");
        order.addOrderItem(pizza);

        orderRepository.save(order);

        Assertions.assertThat(order.getId()).isNotNull();
        Assertions.assertThat(order.getOrderState()).isEqualTo(OrderState.CREATED);
        Assertions.assertThat(order.getOrderItems()).hasSize(1);
        Assertions.assertThat(order.getOrderItems().stream().findFirst().orElseThrow(IllegalAccessError::new).getId()).isNotNull();

    }

    @Test
    void testOneToOneRelation(){
        Collection<User> users = UserBuilder.buildBulkUser();
        userRepository.saveAll(users);

        Assertions.assertThat(users
                .stream()
                .map(User::getId)
                .filter(Objects::isNull)
                .findAny())
                .isNotPresent();
    }

    @Autowired
    public OrderRepositoryUnitTest(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
}
