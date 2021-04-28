package com.li.state.engine.orderstate.context;

import com.li.state.engine.entity.Order;
import lombok.Getter;
import lombok.Setter;

/**
 * 实现上下文参数的传递，进阶模式可以使用线程的ThreadLocal进行隐式传递
 */
@Getter
@Setter
public class StateContext<T> {
    /**
     * 订单id
     */
    private T orderId;

    private String orderName;

    private String orderStatus;

    private Order order;

}
