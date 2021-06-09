package ru.toroschin.spring.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> items;
    private BigDecimal sum;
}
