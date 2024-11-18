package com.Appnotifica_o.email.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitMQConfig {
    @Value("${broker.queue.notificacao.queue}")
    private String queueNotificacao;
    @Value("${broker.queue.notificacao.exchange}")
    private String exchangeNotificacao;
    @Value("${broker.queue.notificacao.queuedlq}")
    private String queueNotificacaodlq;
    @Value("${broker.queue.notificacao.exchangedlq}")
    private String exchangeNotificacaodlq;

    @Bean
    public Queue queueNotificQueueDlq() {
        return new Queue(this.queueNotificacaodlq);
    }

    @Bean
    public Queue queueNotificQueue() {
        Map<String,Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange",this.exchangeNotificacaodlq);
        return new Queue(this.queueNotificacao,true,false,false,args);
    }

    @Bean
    public DirectExchange  exchangeNotificdlq() {
        return new DirectExchange(this.exchangeNotificacaodlq);
    }

    @Bean
    public DirectExchange  exchangeNotific() {
        return new DirectExchange(this.exchangeNotificacao);
    }

    @Bean
    public Binding bindingdlq() {
        return BindingBuilder.bind(this.queueNotificQueueDlq()).to(this.exchangeNotificdlq()).with(this.queueNotificacao);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(this.queueNotificQueue()).to(this.exchangeNotific()).with(this.queueNotificacao);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);

    }
}
