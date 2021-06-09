package ru.toroschin.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.toroschin.spring.common.ProductDto;
import ru.toroschin.spring.error_handling.InvalidDataException;
import ru.toroschin.spring.error_handling.ResourceNotFoundException;
import ru.toroschin.spring.models.Product;
import ru.toroschin.spring.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductService productService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String getTest() {
        return "test products string";
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.findAll();
        return products.stream().map(ProductService.functionProductToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getOneProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        Product product = productOptional.orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: "+id));
        return ProductService.functionProductToDto.apply(product);
    }

    @PostMapping("/add")
    public ProductDto createProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        } else {
            return productService.saveDto(productDto);
        }
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
        productService.delete(product.getId());
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }
}
