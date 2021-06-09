package ru.toroschin.spring.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private ProductDto productDto;
}
