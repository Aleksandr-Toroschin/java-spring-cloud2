package ru.toroschin.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.toroschin.spring.common.ProductDto;
import ru.toroschin.spring.error_handling.ResourceNotFoundException;
import ru.toroschin.spring.models.Category;
import ru.toroschin.spring.models.Product;
import ru.toroschin.spring.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public static final Function<Product, ProductDto> functionProductToDto = p -> {
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setTitle(p.getTitle());
        productDto.setCost(p.getCost());
        productDto.setCategoryTitle(p.getCategory().getTitle());
        return productDto;
    };

    public Page<Product> findPage(int page, int pageSize) {
        return productRepository.findAllBy(PageRequest.of(page, pageSize));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public ProductDto saveDto(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        Optional<Category> category = categoryService.findByTitle(productDto.getCategoryTitle());
        product.setCategory(category.orElseThrow(() -> new ResourceNotFoundException("Такой категории не найдено " + productDto.getCategoryTitle())));
        productRepository.save(product);
        return functionProductToDto.apply(product);
    }

    @Transactional
    public ProductDto update(ProductDto productDto) {
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с id=" + productDto.getId() + "не найден"));
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        Optional<Category> category = categoryService.findByTitle(productDto.getCategoryTitle());
        product.setCategory(category.orElseThrow(() -> new ResourceNotFoundException("Такой категории не найдено " + productDto.getCategoryTitle())));
        return functionProductToDto.apply(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
