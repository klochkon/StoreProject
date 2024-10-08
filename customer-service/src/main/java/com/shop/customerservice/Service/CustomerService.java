package com.shop.customerservice.Service;

import com.shop.customerservice.Client.NotificationClient;
import com.shop.customerservice.DTO.CustomerDTO;
import com.shop.customerservice.DTO.MailDTO;
import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Model.Sale;
import com.shop.customerservice.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final KafkaTemplate<String, MailDTO> kafkaRegistration;
    private final NotificationClient notificationClient;
    private final MongoTemplate mongoTemplate;
    private final SaleService saleService;

//    todo when sale on product which is in favourite customer`s products make an email about it

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public Customer saveCustomer(Customer customer) {

        Map<String, Object> data = Map.of(
                "Email", customer.getEmail(),
                "Name", customer.getName()
        );

        MailDTO mailDTO;
        mailDTO = MailDTO.builder()
                .to(customer.getEmail())
                .data(data)
                .build();

        Sale sale;
        sale = Sale.builder()
                .customerId(customer.getId())
                .sale(new BigDecimal(0.1))
                .build();


        saleService.saveSale(sale);

        kafkaRegistration.send("mail-topic", mailDTO);
        return repository.save(customer);
    }

    @CachePut(value = {"customer", "allCustomer"}, key = "#customer.id")
    public Customer updateCustomer(Customer customer) {
        return repository.save(customer);
    }

    @CacheEvict(value = {"customer", "allCustomer"}, key = "#id")
    public void deleteCustomerById(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "customer", key = "#id")
    public Customer findCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable(value = "allCustomer")
    public List<Customer> findAllCustomer() {
        return repository.findAll();
    }


    public CustomerDTO findCustomerEmailAndNameById(Long customerId) {
        Customer customer = repository.findById(customerId).orElse(null);
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());

        return customerDTO;
    }

    public void customerIdentify(Map<Long,String> productsWasOutMap) {

        for (Map.Entry<Long, String> entry : productsWasOutMap.entrySet()) {
            Customer customer = repository.findById(entry.getKey()).orElse(null);

            Map<String, Object> data = Map.of(
                    "Product", entry.getValue(),
                    "Name", customer.getName()
            );
            MailDTO mailDTO;
            mailDTO = MailDTO.builder()
                    .data(data)
                    .to(customer.getEmail())
                    .build();
            notificationClient.sendUpdateStorageEmail(mailDTO);

        }
    }

    public void cleanCart(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("cart", Map.of());
        mongoTemplate.updateFirst(query, update, "customer");

    }
}
