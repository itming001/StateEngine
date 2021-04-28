package com.li.state.engine.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * order订单对象
 * @author lym
 */
@Getter
@Setter
public class Order {

    public Order(String orderId, String orderName, String orderDesc) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDesc = orderDesc;
    }

    /**
     * orderId订单id
     */
    private String orderId;

    /**
     * order订单名称
     */
    private String orderName;

    /**
     * order订单状态
     */
    private String orderStatus;

    /**
     * order订单描述
     */
    private String orderDesc;

}
