package com.ttsw.officecore.configuration;

import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfiguration {

	@Value("${spring.kafka.bootstrap-servers:localhost:29092}")
	private String bootstrapServersUrl;

	@Value("${office-core.kafka.topics.email.name}")
	private String mailTopic;

	@Value("${office-core.kafka.topics.email.partitions}")
	private int emailTopicPartitions;

	@Value("${office-core.kafka.topics.email.replicationFactor}")
	private short emailTopicReplicationFactor;

	@Bean
	public KafkaAdmin kafkaAdmin(){
		return new KafkaAdmin(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersUrl));
	}

	@Bean
	public NewTopic mailTopic(){
		return new NewTopic(mailTopic, emailTopicPartitions, emailTopicReplicationFactor);
	}
}
