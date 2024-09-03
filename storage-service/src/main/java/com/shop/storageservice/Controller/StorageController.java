package com.shop.storageservice.Controller;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;

    @GetMapping("check")
    public Boolean isInStorage(@RequestBody String name, Integer requiredQuantity) {
        return service.isInStorage(name, requiredQuantity);
    }

    @GetMapping("find/{id}")
    public Storage findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("add")
    public void addById(@RequestParam Integer quantityAdded, @RequestParam Long addedId) {
        service.addProductById(addedId, quantityAdded);
    }

    @DeleteMapping("delete")
    public void deleteById(@RequestParam Integer quantityDeleted, @RequestParam Long deletedId) {
        service.deleteProductById(deletedId, quantityDeleted);
    }

    @GetMapping("order/check")
    public Boolean isOrderInStorage(@RequestBody Map<String, Integer> cart) {
        return service.isOrderInStorage(cart);
    }

    @GetMapping("find/order/out")
    public Map<String, Integer> findOutOfStorageProduct(@RequestBody Map<String, Integer> cart) {
        return service.findOutOfStorageProduct(cart);
    }


}
