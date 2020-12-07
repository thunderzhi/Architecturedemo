package com.cxz.cloudqueue.configuration;

import com.cxz.cloudqueue.properties.RocketmqProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/16 23:27
 */
@AllArgsConstructor
@Configuration
@EnableConfigurationProperties({RocketmqProperties.class})
public class RocketmqConfiguration {
}
