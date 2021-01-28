package com.zw.admin.system.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @description: RabbitMQ配置类，配置交换机，消息队列，并且绑定交换机和消息队列
 * @author: ZhouWei
 * @create: 2020-12
 **/
public class RabbitMqConfig {

    /**声明队列*/
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    /**声明交换机*/
    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
    /**声明routingKey*/
    public static final String ROUTING_KEY_EMAIL = "inform.#.email.#";
    public static final String ROUTING_KEY_SMS = "inform.#.sms.#";

    /**
     * 声明交换机
     * @return org.springframework.amqp.core.Exchange
     **/
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    /**
     * 声明QUEUE_INFORM_SMS队列
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean(QUEUE_INFORM_SMS)
    public Queue QUEUE_INFORM_SMS() {
        return new Queue(QUEUE_INFORM_SMS);
    }

    /**
     * 声明QUEUE_INFORM_EMAIL队列
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue QUEUE_INFORM_EMAIL() {
        return new Queue(QUEUE_INFORM_EMAIL);
    }

    /**
     * ROUTING_KEY_EMAIL队列绑定交换机，指定routingKey
     * @param queue
     * @param exchange
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    public Binding BINDING_QUEUE_INFORM_EMAIL(@Qualifier(QUEUE_INFORM_EMAIL) Queue queue, @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_EMAIL).noargs();
    }

    /**
     * ROUTING_KEY_SMS队列绑定交换机，指定routingKey
     * @param queue
     * @param exchange
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    public Binding BINDING_ROUTING_KEY_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue, @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_SMS).noargs();
    }

}
