package com.example.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitDirectConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue directQueue() {
        return new Queue("direct");
    }

    @Bean
    public Queue testQueue() {
        return new Queue("test");
    }

//    @Bean
//    DirectExchange directExchange() {
//        return new DirectExchange("directExchange");
//    }

//    @Bean
//    Binding bindExchangeDirectQueue(Queue directQueue, DirectExchange directExchange) {
//        return BindingBuilder.bind(directQueue).to(directExchange).with("direct");
//    }
}
