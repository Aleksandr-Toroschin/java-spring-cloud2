package ru.toroschin.spring.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.toroschin.spring.common.CartDto;
import ru.toroschin.spring.common.ProductDto;
import ru.toroschin.spring.error_handling.ResourceNotFoundException;
import ru.toroschin.spring.models.OrderItem;
import ru.toroschin.spring.models.Product;
import ru.toroschin.spring.utils.Cart;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class CartService {
    private final ProductService productService;
    private final Cart cart;

    public static final Function<Cart, CartDto> functionCartToDto = c -> {
        CartDto cartDto = new CartDto();
        cartDto.setSum(c.getSum());
        cartDto.setItems(c.getOrderItems().stream().map(OrderItemService.functionOrderItemToDto).collect(Collectors.toList()));
        return cartDto;
    };

    public void addProduct(Long id, Cart cart) {
        for (OrderItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(id)) {
                item.incrementQuantity();
                cart.recalculate();
                return;
            }
        }
        cart.addProduct(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id=" + id + "не найден")));
    }

    public CartDto getCart() {
        return functionCartToDto.apply(cart);
    }

}
