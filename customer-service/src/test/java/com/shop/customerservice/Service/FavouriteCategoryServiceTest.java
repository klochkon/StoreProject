package com.shop.customerservice.Service;

import com.shop.customerservice.CustomerServiceApplication;
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
class FavouriteCategoryServiceTest {

    @InjectMocks
    private FavouriteCategoryService service;

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
    void findFavouriteCategoryByCustomerId() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        Set<String> favouriteCategory = customer.getFavouriteCategory();
        Set<String> testFavouriteCategory = service.findFavouriteCategoryByCustomerId(customer.getId());
        assertEquals(favouriteCategory, testFavouriteCategory);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void setFavouriteCategoryByCustomerId() {
        Set<String> favouriteCategories = Set.of("favoriteCategories");
        when(repository.findById(anyLong())).thenReturn(Optional.of(customer));
        service.setFavouriteCategoryByCustomerId(customer.getId(), favouriteCategories);
        assertEquals(favouriteCategories, customer.getFavouriteCategory());
        verify(repository, times(1)).findById(anyLong());

    }
}