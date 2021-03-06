package com.li.state.engine.orderstate.template;

import com.li.state.engine.orderstate.action.StateActionStep;
import com.li.state.engine.orderstate.check.Checkable;
import com.li.state.engine.orderstate.check.impl.CheckExecutor;
import com.li.state.engine.util.R;
import com.li.state.engine.orderstate.context.StateContext;
import com.li.state.engine.orderstate.strategy.StateProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 状态机处理模板类
 *
 * @author lym
 */
@Component
public abstract class AbstractStateProcessor<T, C> implements StateProcessor<T, C>, StateActionStep<T, C> {

    /**
     * 增加校验线程池进行异步执行
     */
    @Resource
    private CheckExecutor checkExecutor;

    @Override
    public R<T> action(StateContext<C> context) throws Exception {
        R<T> result = null;
        try {
            //数据校验,获取不同类型的校验信息
            Checkable checkable = this.getCheckable(context);
            //通过线程池进行数据校验
            result = checkExecutor.parallelCheck(checkable.getParamChecker(), context);
            if (result.getCode() != 0) {
                return result;
            }

            // 数据准备
            this.prepare(context);
            // 串行校验器
            result = this.check(context);
            if (result.getCode() != 0) {
                return result;
            }
            // getNextState不能在prepare前，因为有的nextState是根据prepare中的数据转换而来
            String nextState = this.getNextState(context);
            // 业务逻辑
            result = this.action(nextState, context);
            if (result.getCode() != 0) {
                return result;
            }
            // 持久化
            result = this.save(nextState, context);
            if (result.getCode() != 0) {
                return result;
            }
            // after
            this.after(context);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public R<T> check(StateContext<C> context) {
        return null;
    }

    @Override
    public String getNextState(StateContext<C> context) {
        return null;
    }

    @Override
    public R<T> action(String nextState, StateContext<C> context) throws Exception {
        return null;
    }

    @Override
    public R<T> save(String nextState, StateContext<C> context) throws Exception {
        return null;
    }

    @Override
    public void after(StateContext<C> context) {

    }

    @Override
    public Checkable getCheckable(StateContext<C> context) {
        return null;
    }
}
