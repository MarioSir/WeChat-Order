package com.han.order.server.messgae;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * 组：解决配置分布式一个应用多个实例会同时消费消息问题，这样只会由组中的一个实例去消息消息
 * spring:
 *  cloub:
 *   stream:
 *       bindings:
 *         myMessage:
 *           group: order
 *           content-type:	application/json  #解决对象格式问题
 */
@Component
public interface StreamClient {
    String CHANNEL="myMessage";
    String CHANNEL_NOTICE="myMessage_notice";

    @Input(StreamClient.CHANNEL)
    SubscribableChannel input();

    @Output(StreamClient.CHANNEL)
    MessageChannel output();

    @Input(StreamClient.CHANNEL_NOTICE)
    SubscribableChannel inputNotice();

    @Output(StreamClient.CHANNEL_NOTICE)
    MessageChannel outputNotice();
}
