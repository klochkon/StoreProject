package com.shop.storageservice.Controller;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;
    @GetMapping("isInStorage")
    public Boolean IsInStorage(@RequestBody Storage product, Integer requiredquentity){return service.IsInStorage(product,requiredquentity);}






}
