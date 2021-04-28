package com.li.state.engine.orderstate.processor;


import com.alibaba.fastjson.JSONObject;
import com.li.state.engine.entity.Order;
import com.li.state.engine.orderstate.annotation.OrderProcessor;
import com.li.state.engine.orderstate.check.Checkable;
import com.li.state.engine.util.R;
import com.li.state.engine.orderstate.context.StateContext;
import com.li.state.engine.orderstate.template.AbstractStateProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 订单初始化状态
 *
 * @author lym
 */
@Slf4j
@OrderProcessor(state = "INIT", event = "1", bizCode = {"CHEAP"}, sceneId = {"H5"})
@Component
public class StateCreateProcessor extends AbstractStateProcessor<Order, String> {

    @Override
    public void prepare(StateContext<String> context) {
        log.info("-----prepare-----订单数据进行预处理");
        String orderId = context.getOrderId();
        log.info("将Order订单名称进行赋值处理");
        context.setOrderName("XXX的火车票订单");
        context.setOrder(new Order(context.getOrderId(),"XXX的火车票订单","订单简介"));
    }

    @Override
    public R check(StateContext context) {
        log.info("-----check-----订单数据校验中");
        if(StringUtils.isEmpty(context.getOrderName())){
            return R.failed();
        }
        return R.ok();
    }

    @Override
    public String getNextState(StateContext context) {
        log.info("-----getNextState-----获取此逻辑的订单状态");
        return "A1000";
    }

    @Override
    public R<Order> action(String nextState, StateContext context) throws Exception {
        log.info("-----action-----将当前的状态设置到上下文中");
        //将当前的状态设置到订单中
        context.getOrder().setOrderStatus(nextState);
        return R.ok(context.getOrder());
    }

    @Override
    public R save(String nextState, StateContext context) throws Exception {
        log.info("-----save-----将订单进行落库处理");
        log.info("获取订单上下文信息：{}",JSONObject.toJSONString(context));
        return R.ok();
    }

    @Override
    public void after(StateContext context) {
        log.info("-----after-----将订单进行后续处理");
    }

    @Override
    public Checkable getCheckable(StateContext context) {
        return super.getCheckable(context);
    }
}
