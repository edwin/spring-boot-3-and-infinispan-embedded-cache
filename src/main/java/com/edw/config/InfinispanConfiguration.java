package com.edw.config;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.IndexStorage;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <pre>
 *     com.edw.config.InfinispanConfiguration
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 28 Apr 2023 10:54
 */
@Configuration
public class InfinispanConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public InfinispanCacheConfigurer infinispanCacheConfigurer() {
        return manager -> {
            org.infinispan.configuration.cache.Configuration userCacheConfig = new ConfigurationBuilder()
                    .indexing().enable()
                    .addIndexedEntities("com.edw.bean.User")
                    .build();

            manager.defineConfiguration("user-cache", userCacheConfig);
        };
    }

    @Bean
    public InfinispanGlobalConfigurer globalConfigurer() {
        return () -> {
            final GlobalConfiguration globalConfiguration = new GlobalConfigurationBuilder()
                    .build();
            return globalConfiguration;
        };
    }
}
