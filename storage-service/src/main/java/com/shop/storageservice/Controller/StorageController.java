package com.shop.storageservice.Controller;

import com.shop.storageservice.DTO.OrderDuplicateDTO;
import com.shop.storageservice.DTO.ProductDuplicateDTO;
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
    public Boolean isInStorage(@RequestParam Long id,@RequestParam Integer requiredQuantity) {
        return service.isInStorage(id, requiredQuantity);
    }

    @PostMapping("save/{quantity}")
    public void saveProduct(@PathVariable Integer quantity,
                            @RequestBody ProductDuplicateDTO productDuplicateDTO) {
        service.saveProduct(quantity, productDuplicateDTO);
    }

    @PutMapping("save/{quantity}")
    public void updateProduct(@PathVariable Integer quantity,
                            @RequestBody ProductDuplicateDTO productDuplicateDTO) {
        service.updateProduct(quantity, productDuplicateDTO);
    }


    @GetMapping("find/{id}")
    public Storage findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {service.deleteById(id);}

    @PostMapping("add")
    public void addById(@RequestParam Integer quantityAdded, @RequestParam Long addedId) {
        service.addProductById(addedId, quantityAdded);
    }

    @DeleteMapping("delete")
    public void deleteById(@RequestBody OrderDuplicateDTO orderDuplicateDTO) {
        service.deleteProductById(orderDuplicateDTO);
    }

    @GetMapping("order/check")
    public Boolean isOrderInStorage(@RequestBody Map<ProductDuplicateDTO, Integer> cart) {
        return service.isOrderInStorage(cart);
    }

    @GetMapping("find/order/out")
    public Map<ProductDuplicateDTO, Integer> findOutOfStorageProduct(@RequestBody Map<ProductDuplicateDTO, Integer> cart) {
        return service.findOutOfStorageProduct(cart);
    }


}
