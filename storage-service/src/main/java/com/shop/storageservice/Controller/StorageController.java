package com.shop.storageservice.Controller;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;
    @GetMapping("isInStorage")
    public Boolean isInStorage(@RequestBody Storage product, @RequestParam Integer requiredquentity){
        return service.isInStorage(product,requiredquentity);
    }

    @PostMapping("add")
    public void addProductById(@RequestParam Integer quantityAdded, Long addedId){
        service.addProductById(addedId, quantityAdded);
    }

    @DeleteMapping("delete")
    public void deleteProductById(@RequestParam Integer quantityDeleted, Long deletedId){
        service.deleteProductById(deletedId,quantityDeleted);
    }


}
