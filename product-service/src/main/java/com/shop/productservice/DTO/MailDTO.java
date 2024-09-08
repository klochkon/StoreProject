package com.shop.productservice.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MailDTO {

    private String to;
    private Map<String, Object> data;
}
