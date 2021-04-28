package com.li.state.engine.orderstate.strategy;

import com.li.state.engine.util.R;
import com.li.state.engine.orderstate.context.StateContext;

/**
 * 实现模板方法
 * 状态机处理器接口
 * @author lym
 */
public interface StateProcessor<T,C> {
    /**
     * 执行状态迁移的入口
     * @param context
     * @throws Exception
     */
    R<T> action(StateContext<C> context) throws Exception;

}
