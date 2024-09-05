package com.example.notificationservice.Controller;

import com.example.notificationservice.DTO.MailDTO;
import com.example.notificationservice.Service.NotificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("send/purchase/{to}")
    public void sendPurchaseEmail(@RequestBody MailDTO mailDTO) throws MessagingException {
        service.sendPurchaseEmail(mailDTO);
    }

    @PostMapping("send/registration/{to}")
    public void sendRegistrationEmail(@PathVariable String to,@RequestBody Map<String, Object> data) throws MessagingException {
        service.sendRegistrationEmail(to, data);
    }


}
