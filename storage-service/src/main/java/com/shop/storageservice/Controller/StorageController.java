package com.shop.storageservice.Controller;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;

    @GetMapping("isInStorage")
    public Boolean isInStorage(@RequestBody Long id, Integer requiredQuantity) {
        return service.isInStorage(id, requiredQuantity);
    }

    @GetMapping("find-by-{id}")
    public Storage findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("add")
    public void addProductById(@RequestParam Integer quantityAdded, @RequestParam Long addedId) {
        service.addProductById(addedId, quantityAdded);
    }

    @DeleteMapping("delete")
    public void deleteProductById(@RequestParam Integer quantityDeleted, @RequestParam Long deletedId) {
        service.deleteProductById(deletedId, quantityDeleted);
    }

    @GetMapping("order-check")
    public Object isOrderInStorage(@RequestBody HashMap<Long, Integer> cart) {
        return service.isOrderInStorage(cart);
    }
}
