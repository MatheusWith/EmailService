package com.Appnotifica_o.email.consumer;

import com.Appnotifica_o.email.dtos.DtoEmail;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenerEmailQueue(@Payload DtoEmail data) {
        System.out.println(data.message());
    }
}
