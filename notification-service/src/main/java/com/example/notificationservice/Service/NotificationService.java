package com.example.notificationservice.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender sender;
    private final TemplateEngine templateEngine;

    public void sendPurchaseEmail(String to, String subject, Map<String, Object> data) throws jakarta.mail.MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariables(data);

        String html = templateEngine.process("HTML", context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);

        sender.send(message);
    }


    public void sendPurchaseEmail(String to, String subject, Map<String, Object> data) throws jakarta.mail.MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariables(data);

        String html = templateEngine.process("HTML", context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);

        sender.send(message);
    }



}
