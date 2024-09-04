package com.example.notificationservice.Controller;

import com.example.notificationservice.Service.NotificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("send/purchase/{to}")
    public void sendPurchaseEmail(@PathVariable String to, Map<String, Object> data) throws MessagingException {
        service.sendPurchaseEmail(to, data);
    }


}
