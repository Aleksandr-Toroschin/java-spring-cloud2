package ru.toroschin.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.toroschin.spring.common.CartDto;
import ru.toroschin.spring.common.CategoryDto;
import ru.toroschin.spring.models.Category;
import ru.toroschin.spring.repositories.CategoryRepository;
import ru.toroschin.spring.utils.Cart;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public static final Function<Category, CategoryDto> functionCategoryToDto = c -> {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(c.getId());
        categoryDto.setTitle(c.getTitle());
        return categoryDto;
    };

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findCategoryByTitle(title);
    }

    public Optional<Category> findOneById(Long id) {
        return categoryRepository.findById(id);
    }

}
