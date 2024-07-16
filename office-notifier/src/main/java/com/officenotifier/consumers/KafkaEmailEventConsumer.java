package com.ttsw.officenotifier.consumers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttsw.officenotifier.events.MailEvent;
import com.ttsw.officenotifier.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class KafkaEmailEventConsumer {


    private final MailService mailSender;

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, String> redisTemplate;

    @SneakyThrows
    @KafkaListener(topics = "${office-notifier.kafka.topics.email.name}", groupId = "office-notifier-group")
    public void consumeEvent(@Payload String message) {
        var mailEvent = objectMapper.readValue(message, MailEvent.class);
        mailSender.sendEmail(mailEvent);
        redisTemplate.opsForValue().append(Instant.now().toString(), message);
    }
}
