package farcic.dev.orderService.producer;

import farcic.dev.orderService.message.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducerService {

    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    public void sendMessage(NotificationMessage notificationMessage){
        kafkaTemplate.send(kafkaTopic, notificationMessage);
    }
}
