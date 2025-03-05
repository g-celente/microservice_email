package com.microservices.email.consumer;

import com.microservices.email.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receive(@Payload EmailDTO emailDto) {

        System.out.println(emailDto.body());
    }
}
