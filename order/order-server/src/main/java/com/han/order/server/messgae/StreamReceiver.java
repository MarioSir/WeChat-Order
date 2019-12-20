package com.han.order.server.messgae;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(StreamClient.class)
public class StreamReceiver {
    @StreamListener(StreamClient.CHANNEL)//监听管道接收到的消息
    @SendTo(StreamClient.CHANNEL_NOTICE)//发送给其他的管道
    public String process(Object message){
          log.info("StreamReceiver ：{}",message);
          return "notice other server has consume------>"+message;
    }

    @StreamListener(StreamClient.CHANNEL_NOTICE)
    public void processNotice(Object message){
        log.info("processNotice ：{}",message);
    }
}
