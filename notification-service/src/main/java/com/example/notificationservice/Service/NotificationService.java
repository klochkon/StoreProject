package com.example.notificationservice.Service;

import com.example.notificationservice.DTO.MailDTO;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender sender;
    private final TemplateEngine templateEngine;

    @Value("${subject.purchase}")
    private String purchaseSubject;

    @Value("${subject.registration}")
    private String registrationSubject;

    @Value("${subject.verification}")
    private String verificationSubject;

    @Value("${subject.updateStorage}")
    private String updateStorageSubject;

    @Value("${admins.storage-role.email}")
    private String storageAdminEmail;

//    TODO HTML

    @KafkaListener(topics = "mail-topic", groupId = "${spring.kafka.consumer-groups.purchase-group.group-id}")
    public void sendPurchaseEmail(MailDTO mailDTO) throws jakarta.mail.MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariables(mailDTO.getData());

        String html = templateEngine.process("PurchaseMailTemplate", context);


        helper.setTo(mailDTO.getTo());
        helper.setSubject(purchaseSubject);
        helper.setText(html, true);

        sender.send(message);
    }

    @KafkaListener(topics = "mail-topic", groupId = "${spring.kafka.consumer-groups.registration-group.group-id}")
    public void sendRegistrationEmail(MailDTO mailDTO) throws jakarta.mail.MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariables(mailDTO.getData());

        String html = templateEngine.process("RegistrationMailTemplate", context);

        helper.setTo(mailDTO.getTo());
        helper.setSubject(registrationSubject);
        helper.setText(html, true);

        sender.send(message);
    }

    @KafkaListener(topics = "mail-topic", groupId = "${spring.kafka.consumer-groups.product-verification-group.group-id}")
    public void sendProductVerificationEmail(MailDTO mailDTO) throws jakarta.mail.MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariables(mailDTO.getData());

        String html = templateEngine.process("RegistrationMailTemplate", context);

        helper.setTo(storageAdminEmail);
        helper.setSubject(verificationSubject);
        helper.setText(html, true);

        sender.send(message);
    }

    public void sendUpdateStorageEmail(MailDTO mailDTO) throws jakarta.mail.MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Context context = new Context();
        context.setVariables(mailDTO.getData());

        String html = templateEngine.process("RegistrationMailTemplate", context);

        helper.setTo(mailDTO.getTo());
        helper.setSubject(updateStorageSubject);
        helper.setText(html, true);

        sender.send(message);

    }

}
