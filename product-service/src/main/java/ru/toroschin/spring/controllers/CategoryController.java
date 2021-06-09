package ru.toroschin.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.toroschin.spring.common.CategoryDto;
import ru.toroschin.spring.error_handling.ResourceNotFoundException;
import ru.toroschin.spring.models.Category;
import ru.toroschin.spring.services.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findOneById(@PathVariable Long id) {
        Category category = categoryService.findOneById(id).orElseThrow(() -> new ResourceNotFoundException("Категория с id="+id+" не найдена"));
        return CategoryService.functionCategoryToDto.apply(category);
    }

}
