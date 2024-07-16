package com.ttsw.officecore;

import com.ttsw.officenotifier.events.MailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@SpringBootApplication
public class OfficeCoreApplication {

	@Autowired
	private KafkaTemplate<Integer, MailEvent> kafkaTemplate;

	public static void main(String[] args) {
		SpringApplication.run(OfficeCoreApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendEvent(){
		var mail = MailEvent.builder()
				.to("hefefe87@gmail.com")
				.subject("kafkaTemat3000")
				.properties(Map.of("testName", "asdasasfas"))
				.build();

		kafkaTemplate.send("pl.pk.office.email", mail);
	}

}
