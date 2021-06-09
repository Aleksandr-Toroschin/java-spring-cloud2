package ru.toroschin.spring.front.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.toroschin.spring.common.ProductDto;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String getTest() {
        return restTemplate.getForObject("http://product-service/products", String.class);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        ProductDto[] productDtos = restTemplate.getForObject("http://product-service/api/v1/products", ProductDto[].class);
        List<ProductDto> products = List.of();
        if (productDtos != null) {
            products = Arrays.asList(productDtos);
        }
        return products;
    }

    @GetMapping("/{id}")
    public ProductDto getOneProduct(@PathVariable Long id) {
        return restTemplate.getForObject("http://product-service/api/v1/products/"+id, ProductDto.class);
    }
}
