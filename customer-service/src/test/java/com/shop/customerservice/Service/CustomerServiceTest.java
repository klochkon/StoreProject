package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private Customer customer;
    @InjectMocks
    private CustomerService service;
    @Mock
    private CustomerRepository repository;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        LocalDate dateOfBirth = LocalDate.of(2020, 8, 1);
//        Set<String> favouriteCategory = Set.of("favouriteCategory");
//        Set<String> favouriteProduct = Set.of("favouriteProduct");
//        customer = Customer.builder()
//                .id(1L)
//                .name("name")
//                .surname("surname")
//                .email("email")
//                .sex("sex")
//                .phoneNumber("phoneNumber")
//                .nickName("nickName")
//                .dateOfBirth(dateOfBirth)
//                .favouriteProduct(favouriteProduct)
//                .build();
//    }

    @Test
    void saveCustomer() {
        when(repository.save(any(Customer.class))).thenReturn(customer);
        Customer testCustomer = service.saveCustomer(customer);
        assertEquals(customer, testCustomer);
        verify(repository, times(1)).save(any(Customer.class));
    }

    @Test
    void updateCustomer() {
        when(repository.save(any(Customer.class))).thenReturn(customer);
        Customer testCustomer = service.updateCustomer(customer);
        assertEquals(customer, testCustomer);
        verify(repository, times(1)).save(any(Customer.class));
    }

    @Test
    void deleteCustomerById() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteCustomerById(customer.getId());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void findCustomerById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        Customer testCustomer = service.findCustomerById(customer.getId());
        assertEquals(customer, testCustomer);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findAllCustomer() {
        List<Customer> customers = List.of(customer);
        when(repository.findAll()).thenReturn(customers);
        List<Customer> testCustomers = service.findAllCustomer();
        assertEquals(customers, testCustomers);
        verify(repository, times(1)).findAll();
    }
}