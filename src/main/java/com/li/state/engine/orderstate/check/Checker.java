package com.li.state.engine.orderstate.check;


import com.li.state.engine.util.R;
import com.li.state.engine.orderstate.context.StateContext;

/**
 * 定义checker校验接口
 * @author lym
 */
public interface Checker<T,C> {
    /**
     * 默认check实现校验逻辑
     * @param context
     * @return
     */
    R<T> check(StateContext<C> context);

    /**
     * 多个checker时的执行顺序
     * @return
     */
    default int order(){
        return 0;
    }
}
