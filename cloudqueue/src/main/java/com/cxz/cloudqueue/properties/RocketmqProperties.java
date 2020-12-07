package com.cxz.cloudqueue.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/16 23:16
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "pivotal.cloud.rocketmq")
public class RocketmqProperties {
    /**
     * rocketmq消息队列生产者-Producer
     */
    private final Producer producer = new Producer();

    /**
     * rocketmq消息队列消费者-Consumer
     */
    private final Consumer consumer = new Consumer();

    @Data
    public static class Producer {
    }

    @Data
    public static class Consumer {
    }
}