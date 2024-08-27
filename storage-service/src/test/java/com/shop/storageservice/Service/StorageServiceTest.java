package com.shop.storageservice.Service;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Repository.StorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StorageServiceTest {

    private Storage storage;

    @Spy
    @InjectMocks
    private StorageService service;

    @Mock
    private StorageRepository repository;

    @BeforeEach
    void setUp() {
        storage = Storage.builder()
                .id(1L)
                .quantity(1)
                .name("name")
                .build();
    }

    @Test
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(storage));
        Storage testStorage = service.findById(storage.getId());
        assertEquals(storage, testStorage);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void isInStorage() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(storage));
        Boolean testStorage = service.isInStorage(storage.getId(), storage.getQuantity());
        assertEquals(testStorage, true);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void addProductById() {
        doNothing().when(repository).addProductById(anyLong(), anyInt());
        service.addProductById(storage.getId(), storage.getQuantity());
        verify(repository, times(1)).addProductById(anyLong(), anyInt());

    }

    @Test
    void deleteProductById() {
        doNothing().when(repository).deleteProductById(anyLong(), anyInt());
        service.deleteProductById(storage.getId(), storage.getQuantity());
        verify(repository, times(1)).deleteProductById(anyLong(), anyInt());
    }

    @Test
    void isOrderInStorage() {
        when(service.isInStorage(anyLong(), anyInt())).thenReturn(true);
        Map<Long, Integer> cart = new HashMap<>();
        Boolean answer = service.isOrderInStorage(cart);
        assertEquals(answer, true);
        verify(service, times(1)).isInStorage(anyLong(), anyInt());
    }

    @Test
    void testFindOutOfStorageProduct() {

        Map<Long, Integer> cart = new HashMap<>();
        cart.put(1L, 5);
        cart.put(2L, 10);


        when(repository.findById(anyLong())).thenReturn(Optional.of(storage));

        when(service.isInStorage(anyLong(), anyInt())).thenReturn(false);

        Map<String, Integer> result = service.findOutOfStorageProduct(cart);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("name", 1);

        assertEquals(expected, result);

        verify(repository, times(1)).findById(anyLong());
    }
}