package com.han.order.server.controller;

import com.han.order.common.dto.OrderDTO;
import com.han.order.server.messgae.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void sendMessage(){
        streamClient.output().send(MessageBuilder.withPayload("I am a message").build());
    }

    @GetMapping("/sendObjectMessage")
    public void sendObjectMessage(){
        OrderDTO orderDTO = OrderDTO.builder().orderId("1111").buyerName("han").build();
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
