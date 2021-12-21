package com.orderexample.orderexample.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @Getter
    @Setter
    private Order order;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private BigDecimal amount;

}
