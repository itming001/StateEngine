package com.li.state.engine.orderstate.action;


import com.li.state.engine.orderstate.check.Checkable;
import com.li.state.engine.util.R;
import com.li.state.engine.orderstate.context.StateContext;

/**
 * 状态迁移流程封装 6个阶段
 * 数据准备（prepare）--》校验（check）--》获取下一个状态（getNextState）--》业务逻辑执行（action）
 * --》数据持久化（save）--》后续处理（after）
 *
 * @author lym
 */
public interface StateActionStep<T,C> {
    /**
     * 准备数据
     * @param context
     */
    default void prepare(StateContext<C> context){

    }

    /**
     * 校验
     * @param context
     * @return
     */
    R<T> check(StateContext<C> context);

    /**
     * 获取当前状态处理器处理完毕后，所处于的下一个状态
     * @param context
     * @return
     */
    String getNextState(StateContext<C> context);

    /**
     * 状态动作方法，主要状态迁移逻辑
     * @param nextState
     * @param context
     * @return
     * @throws Exception
     */
    R<T> action(String nextState, StateContext<C> context) throws Exception;

    /**
     * 状态数据持久化
     * @param nextState
     * @param context
     * @return
     * @throws Exception
     */
    R<T> save(String nextState, StateContext<C> context) throws Exception;

    /**
     * 状态迁移成功，持久化后执行的后续处理
     * @param context
     */
    void after(StateContext<C> context);

    /**
     * 获取校验相关集合信息
     * @param context
     * @return
     */
    Checkable getCheckable(StateContext<C> context);
}
