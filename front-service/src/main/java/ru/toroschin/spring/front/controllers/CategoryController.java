package ru.toroschin.spring.front.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.toroschin.spring.common.CategoryDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public CategoryDto findOneById(@PathVariable Long id) {
        return restTemplate.getForObject("http://product-service/api/v1/category/"+id, CategoryDto.class);
    }

}
