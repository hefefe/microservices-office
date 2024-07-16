package com.ttsw.officenotifier.mail;

import com.ttsw.officenotifier.events.MailEvent;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;

    private final SpringTemplateEngine templateEngine;

    @Value("${office-notifier.templateName}")
    private String templateName;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    @SneakyThrows
    public void sendEmail(MailEvent mailEvent) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        String html = getHtmlContent(mailEvent);

        helper.setTo(mailEvent.getTo());
        helper.setFrom(mailFrom);
        helper.setSubject(mailEvent.getSubject());
        helper.setText(html, true);

        emailSender.send(message);
    }

    private String getHtmlContent(MailEvent mailEvent) {
        Context context = new Context();
        context.setVariables(mailEvent.getProperties());
        return templateEngine.process(templateName, context);
    }

}
