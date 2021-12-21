package com.orderexample.orderexample.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    private OrderState orderState = OrderState.CREATED;

    @Column( precision = 10,scale = 4, nullable = false)
    @Getter
    @Setter
    private BigDecimal totalAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter
    @Setter
    private PaymentChannel paymentChannel;

    @OneToOne
    @Getter
    @Setter
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Getter
    private Collection<OrderItem> orderItems = new ArrayList<>();

    /*
    public void changeOrderState(OrderState orderState) {
        if (orderState.equals(OrderState.CREATED)){
            throw new UnsupportedOperationException();
        }
        this.orderState = orderState;
    }

     */

    public void addOrderItem(OrderItem orderItem){
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem){
        orderItem.setOrder(null);
        orderItems.remove(orderItem);
    }

    public Collection<OrderItem> getOrderItems() {
        return Collections.unmodifiableCollection(orderItems);
    }
}
