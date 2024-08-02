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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavouriteProductServiceTest {

    @InjectMocks
    private FavouriteProductService service;

    @Mock
    private CustomerRepository repository;

    private Customer customer;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setPhoneNumber("phoneNumber");
        customer.setNickName("nickName");
        customer.setName("name");
        customer.setSurname("surname");
        customer.setSex("sex");

        LocalDate dateOfBirth = LocalDate.of(2020, 8, 2);
        customer.setDateOfBirth(dateOfBirth);

        Set<String> favouriteCategory= Set.of("favouriteCategory");
        Set<String> favouriteProduct= Set.of("favouriteProduct");

        customer.setFavouriteCategory(favouriteCategory);
        customer.setFavouriteProduct(favouriteProduct);
    }

    @Test
    void findFavouriteProductByCustomerId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        Set<String> favouriteProduct = customer.getFavouriteProduct();
        Set<String> testFavouriteProduct = service.findFavouriteProductByCustomerId(customer.getId());
        assertEquals(favouriteProduct, testFavouriteProduct);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void setFavouriteProductByCustomerId() {
        Set<String> favouriteProducts = Set.of("favoriteProducts");
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        service.setFavouriteProductByCustomerId(customer.getId(), favouriteProducts);
        assertEquals(favouriteProducts, customer.getFavouriteProduct());
        verify(repository, times(1)).findById(anyLong());
    }
}