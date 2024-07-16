package com.ttsw.officecore.configuration;

import java.util.HashMap;
import java.util.Map;

import com.ttsw.officenotifier.events.MailEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaEventProducerConfiguration {

	@Value("${spring.kafka.bootstrap-servers:localhost:29092}")
	private String bootstrapServersUrl;

	@Bean
	public ProducerFactory<Integer, MailEvent> producerFactory() {
		Map<String, Object> producerFactoryProperties = new HashMap<>();
		producerFactoryProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersUrl);
		producerFactoryProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		producerFactoryProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(producerFactoryProperties);
	}

	@Bean
	public KafkaTemplate<Integer, MailEvent> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
}
