package com.example.customerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.core.task.AsyncTaskExecutor;

/**
 * Configuration class for performance optimizations in a Java 17 environment.
 * This class configures thread pools and async execution strategies optimized
 * for the application's workload characteristics.
 */
@Configuration
@EnableAsync
public class OptimizationConfig {

    /**
     * Configures Tomcat's thread pool for handling HTTP requests.
     * 
     * Thread Pool Settings:
     * - Core Pool Size: 8 (handles normal load)
     * - Max Pool Size: 200 (handles peak load)
     * - Queue Capacity: 100 (prevents thread explosion under high load)
     * 
     * These settings are optimized for Java 17's thread implementation:
     * - Smaller thread pool compared to virtual threads
     * - Efficient context switching
     * - Better memory utilization
     *
     * @return TomcatProtocolHandlerCustomizer for thread pool configuration
     */
    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerCustomizer() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(8);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix("http-executor-");
        taskExecutor.initialize();
        return protocolHandler -> protocolHandler.setExecutor(taskExecutor.getThreadPoolExecutor());
    }

    /**
     * Configures a separate thread pool for handling async application tasks.
     * This pool is distinct from the HTTP request handling pool to prevent
     * resource contention.
     * 
     * Thread Pool Settings:
     * - Core Pool Size: 4 (efficient for background tasks)
     * - Max Pool Size: 8 (prevents resource exhaustion)
     * - Queue Capacity: 50 (buffers task overflow)
     * 
     * Benefits:
     * - Prevents thread starvation
     * - Optimizes CPU utilization
     * - Maintains responsive user experience
     * 
     * Use Cases:
     * - Email notifications
     * - Background data processing
     * - Scheduled tasks
     *
     * @return AsyncTaskExecutor for handling background tasks
     */
    @Bean
    public AsyncTaskExecutor applicationTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("async-executor-");
        executor.initialize();
        return executor;
    }
}
