package com.han.order.server.messgae;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MQReceiver {
    //需要手动在rabbmit上创建消队列myQueue
    //@RabbitListener(queues="myQueue")
    //自动创建消息队列myQueue
    //@RabbitListener(queuesToDeclare=@Queue("myQueue"))
    //绑定交换机myExchange
    @RabbitListener(bindings = @QueueBinding(
            exchange =@Exchange("myExchange"),value = @Queue("myQueue")
    ))
    public void process(String message){
        log.info("messgae:{}",message);
    }


    //绑定交换机myExchange并通过order_key路由到orderQueue队列
    @RabbitListener(bindings = @QueueBinding(
            exchange =@Exchange("myExchange"),value = @Queue("orderQueue"),key = "order_key"
    ))
    public void processToOrder(String message){
        log.info("messgae:{}",message);
    }

    //绑定交换机myExchange并通过order_key路由到fruitQueue队列
    @RabbitListener(bindings = @QueueBinding(
            exchange =@Exchange("myExchange"),value = @Queue("fruitQueue"),key = "fruit_key"
    ))
    public void processToFruit(String message){
        log.info("messgae:{}",message);
    }

}
