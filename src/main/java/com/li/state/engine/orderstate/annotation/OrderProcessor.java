package com.li.state.engine.orderstate.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现“多状态+多类型+多场景+多维度”这种组合业务
 * 定义一个注解@OrderPorcessor将不同的维度予以组合、开发出多个对应的具体实现类，在系统运行阶段，
 * 通过判断上下文来动态选择具体使用哪一个实现类执行。
 *
 * @author lym
 * @OrderPorcessor 中分别定义state代表当前处理器要处理的状态，bizCode和sceneId分别代表业务类型和场景
 * 这两个字段留给业务进行扩展，比如可以用bizCode代表产品或者订单类型、sceneId代表业务形态或来源场景等等
 * 如果要扩展多个维度的组合、也可以用多个维度拼接后的字符串赋值bizCode和sceneId
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface OrderProcessor {
    /**
     * 指定状态，state不能同时存在
     * @return
     */
    String[] state() default {};

    /**
     * 订单操作事件
     * @return
     */
    String event();

    /**
     * 业务
     * @return
     */
    String[] bizCode() default {};

    /**
     * 场景
     * @return
     */
    String[] sceneId() default {};
}
