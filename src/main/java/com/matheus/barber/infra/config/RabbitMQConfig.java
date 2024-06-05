package com.matheus.barber.infra.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("emailExchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("emailQueue", true);
    }

    @Bean
    public Binding binding(Queue emailQueue, CustomExchange delayedExchange) {
        return BindingBuilder.bind(emailQueue).to(delayedExchange).with("emailRoutingKey").noargs();
    }


}
