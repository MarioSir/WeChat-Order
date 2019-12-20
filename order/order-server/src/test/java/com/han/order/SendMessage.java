package com.han.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SendMessage extends OrderApplicationTests{
    @Autowired
    private RabbitTemplate amqpTemplate;
    @Test
    public void testSendMessage(){
        amqpTemplate.convertAndSend("myQueue","now "+new Date());
    }
    @Test
    public void testSendOrderMessage(){
        amqpTemplate.convertAndSend("myExchange","order_key","now "+new Date());
    }
    @Test
    public void testSendFruitMessage(){
        amqpTemplate.convertAndSend("myExchange","fruit_key","now "+new Date());
    }
}
