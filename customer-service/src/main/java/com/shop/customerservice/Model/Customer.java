package com.shop.customerservice.Model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {
    @Id
    private Long id;

    private String email;
    private String phoneNumber;
    private String nickName;
    @NotBlank(message = "Name can`t be blank")
    private String name;
    @NotBlank(message = "Surname can`t be blank")
    private String surname;
    private String sex;
    private LocalDate dateOfBirth;
    private Boolean newsLetterSubscribe;

    private Set<String> favouriteCategory;
    private Set<String> favouriteProduct;
}
