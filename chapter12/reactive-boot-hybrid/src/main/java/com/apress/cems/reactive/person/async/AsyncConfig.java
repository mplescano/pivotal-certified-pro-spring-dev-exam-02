package com.apress.cems.reactive.person.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {

    private final ServerProperties serverProperties;

    public AsyncConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(serverProperties.getTomcat().getThreads().getMinSpare());
        executor.setMaxPoolSize(serverProperties.getTomcat().getThreads().getMax());
        executor.setQueueCapacity(serverProperties.getTomcat().getAcceptCount());
        executor.setThreadNamePrefix("AsyncExecutor-");
        //executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }
}