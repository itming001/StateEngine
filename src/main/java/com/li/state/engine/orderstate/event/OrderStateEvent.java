package com.li.state.engine.orderstate.event;

/**
 * 订单状态迁移事件
 * @author lym
 */
public interface OrderStateEvent {
    /**
     * 订单状态事件
     * @return
     */
    String getEventType();

    /**
     * 订单id
     * @return
     */
    String getOrderId();

    /**
     * 如果orderState不为空，则代表只有订单是当前状态才能迁移
     * @return
     */
    default String orderState(){
        return null;
    }

    /**
     * 是否要新创建订单
     * @return
     */
    boolean newCreate();

}
