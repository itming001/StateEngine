package com.li.state.engine.controller;

import com.alibaba.fastjson.JSONObject;
import com.li.state.engine.entity.Order;
import com.li.state.engine.orderstate.context.StateContext;
import com.li.state.engine.orderstate.registry.DefaultStateProcessRegistry;
import com.li.state.engine.orderstate.template.AbstractStateProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lym
 */
@RestController
public class OrderController {

    @Resource
    DefaultStateProcessRegistry defaultStateProcessRegistry;

    @GetMapping("/testOrder")
    public void testOrder() throws Exception {
        Map<String, Map<String, Map<String, List<AbstractStateProcessor>>>> stringMapMap = defaultStateProcessRegistry.acquireStateProcess();
        AbstractStateProcessor init = stringMapMap.get("INIT").get("1").get("CHEAP@H5").get(0);
        StateContext<String> orderStateContext = new StateContext<>();
        orderStateContext.setOrderId("111");
        init.action(orderStateContext);
        System.out.println(JSONObject.toJSONString(stringMapMap));
    }
}
