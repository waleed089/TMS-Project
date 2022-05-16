package com.demo.tms;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Value("${spring.rabbitmq.host}")
    String host;
	
    @Value("${spring.rabbitmq.username}")
    String username;
    
    @Value("${spring.rabbitmq.password}")
    String password;
    
    @Value("${spring.rabbitmq.queue}")
    String userQueue;
    
    @Value("${spring.rabbitmq.exchange}")
    String userExchange;
    
    @Value("${spring.rabbitmq.routingkey}")
    String userRoutingKey;
	
    @Bean
    Queue userQueue() {
    	return new Queue(userQueue, true);
    }
   
    @Bean
    DirectExchange exchange() {
    	return new DirectExchange(userExchange);
    }
   
    @Bean
    Binding marketingBinding(Queue userQueue, DirectExchange exchange) {
    	return BindingBuilder.bind(userQueue).to(exchange).with(userRoutingKey);
    }
    
    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    
    @Bean
    public ModelMapper modelMapper() {
       ModelMapper modelMapper = new ModelMapper();
       return modelMapper;
    }
}