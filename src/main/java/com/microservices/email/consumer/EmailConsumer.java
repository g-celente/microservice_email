package com.microservices.email.consumer;

import com.microservices.email.dto.EmailDTO;
import com.microservices.email.model.Email;
import com.microservices.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receive(@Payload EmailDTO emailDto) {
        var emailModel = new Email();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
    }
}
