package com.Appnotifica_o.email.consumer;

import com.Appnotifica_o.email.dtos.DtoEmail;
import com.Appnotifica_o.email.models.Email;
import com.Appnotifica_o.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${broker.queue.notificacao.queue}")
    public void listenerEmailQueue(@Payload DtoEmail data) {
        System.out.println(data.emailTo());
        var email = new Email(data);
        this.emailService.sendEmail(email);
    }
}
