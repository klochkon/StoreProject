package com.shop.customerservice.Service;

import com.shop.customerservice.DTO.SaleDuplicateDTO;
import com.shop.customerservice.Model.Sale;
import com.shop.customerservice.Repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository repository;

    @CachePut(value = "sale", key = "#sale.id")
    public Sale updateSale(Sale sale) {
        return repository.save(sale);
    }

    @CachePut(value = {"sale", "allSales"}, key = "#sale.id")
    public Sale saveSale(Sale sale) {
        return repository.save(sale);
    }

    @CachePut(value = {"sale", "allSales"}, key = "#saleDuplicateDTO.id")
    @KafkaListener(topics = "sale-topic", groupId = "${spring.kafka.consumer-groups.sale-group.group-id}")
    public Sale saveSaleDTO(SaleDuplicateDTO saleDuplicateDTO) {

        Sale sale;
        sale = Sale.builder()
                .id(saleDuplicateDTO.getId())
                .sale(saleDuplicateDTO.getSale())
                .customerId(saleDuplicateDTO.getCustomerId())
                .build();

        return repository.save(sale);
    }

    @CacheEvict(value = {"sale", "allSales"}, key = "#id")
    public void deleteSaleById(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "sale", key = "#id")
    public Sale findSaleById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable(value = "allOrders")
    public List<Sale> findAllByCustomerId(Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }

}
