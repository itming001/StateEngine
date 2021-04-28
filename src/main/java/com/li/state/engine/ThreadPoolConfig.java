package com.li.state.engine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 参数校验中线程的配置
 * @author dell
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor testCheckThreadPool(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,8,1, TimeUnit.HOURS,new LinkedBlockingQueue<>(1024*1024));
        return threadPoolExecutor;
    }
}
