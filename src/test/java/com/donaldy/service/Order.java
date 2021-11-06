package com.donaldy.service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author donald
 * @date 2021/11/06
 */
public class Order {

    private Long orderId;
    private BigDecimal amount;
    private Address address;
    private List<OrderItem> orderItems;

    /**
     * 更新收获地址
     *
     * @param address 地址
     */
    public void updateAddress(Address address) {
        this.address = address;
    }

    /**
     * 更新订单条项
     *
     * 1. 找到对应订单条项
     * 2. 计算总价
     *
     * @param orderItem 订单条项
     */
    public void updateOrderItems(OrderItem orderItem) {
        // ...
    }
}

class Address {
    private String name;
}

class OrderItem {
    private String orderId;
    private BigDecimal price;
}
