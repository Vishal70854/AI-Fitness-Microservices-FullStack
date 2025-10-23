package com.fitness.aiservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;   // fetch queue value from application.yml file

    @Value("${rabbitmq.exchange.name}")
    private String exchange;    // getting exchange variable value from application.yml file

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // declares a queue named activity.queue in rabbitmq
    // true means even if project restarts, the message will still exists in the rabbitmq queue
    @Bean
    public Queue activityQueue(){
        return new Queue(queue,true);
    }

    // creating a bean for our activity exchange
    @Bean
    public DirectExchange activityExchange(){
        return new DirectExchange(exchange);
    }

    // binding our queue to exchange with our routing key as defined in application.yml-- below is the bean for doing that
    @Bean
    public Binding activityBinding(Queue activitQueue, DirectExchange activityExchange){
        return BindingBuilder.bind(activitQueue).to(activityExchange).with(routingKey);
    }

    // convert java objects to json using jackson message converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}



