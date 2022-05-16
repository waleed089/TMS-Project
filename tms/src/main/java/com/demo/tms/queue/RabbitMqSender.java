package com.demo.tms.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.tms.dto.TaskDTO;

@Service
public class RabbitMqSender {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
   
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
   
    public void sendTask(TaskDTO taskDTO){
    	System.out.println("sending Task to Q");
        rabbitTemplate.convertAndSend(exchange,routingkey, taskDTO);
    }
    
}