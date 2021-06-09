package ru.toroschin.spring.front.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.toroschin.spring.common.CartDto;
import ru.toroschin.spring.common.CategoryDto;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/{id}")
    public void addProduct(@PathVariable Long id) {
        restTemplate.postForLocation("http://product-service/api/v1/cart/" + id, "");
    }

    @GetMapping
    public CartDto getCart() {
        return restTemplate.getForObject("http://product-service/api/v1/cart", CartDto.class);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam Long id) {
        restTemplate.delete("http://product-service/api/v1/cart?id=" + id);
    }

    @DeleteMapping("/clear")
    public void clearCart() {
        restTemplate.delete("http://product-service/api/v1/cart/clear");
    }

    @GetMapping("/get")
    public BigDecimal getSum() {
        return restTemplate.getForObject("http://product-service/api/v1/cart/get", BigDecimal.class);
    }
}
