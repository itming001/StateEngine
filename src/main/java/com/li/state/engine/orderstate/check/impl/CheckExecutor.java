package com.li.state.engine.orderstate.check.impl;

import com.li.state.engine.orderstate.check.Checker;
import com.li.state.engine.util.R;
import com.li.state.engine.orderstate.context.StateContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 校验器执行器
 * @author lym
 */
@Component
public class CheckExecutor<T,C> {
    /**
     * 注入自定义线程池的配置
     */
    @Resource
    private ThreadPoolExecutor executor;
    /**
     * 执行并行校验器，
     * 按照任务投递的顺序判断返回。
     */
    public R<T> parallelCheck(List<Checker> checkers, StateContext<C> context) {
        if (!CollectionUtils.isEmpty(checkers)) {
            if (checkers.size() == 1) {
                return checkers.get(0).check(context);
            }
            List<Future<R>> resultList = Collections.synchronizedList(new ArrayList<>(checkers.size()));
            checkers.sort(Comparator.comparingInt(Checker::order));
            for (Checker c : checkers) {
                Future<R> future = executor.submit(() -> c.check(context));
                resultList.add(future);
            }
            for (Future<R> future : resultList) {
                try {
                    R sr = future.get();
                    if (sr.getCode()!=0) {
                        return sr;
                    }
                } catch (Exception e) {
                    System.out.println("parallelCheck executor.submit error.");
//                    log.error("parallelCheck executor.submit error.", e);
                    throw new RuntimeException(e);
                }
            }
        }
        return new R<>();
    }
}
