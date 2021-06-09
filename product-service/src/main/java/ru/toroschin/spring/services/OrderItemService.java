package ru.toroschin.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.toroschin.spring.common.CartDto;
import ru.toroschin.spring.common.OrderItemDto;
import ru.toroschin.spring.models.Order;
import ru.toroschin.spring.models.OrderItem;
import ru.toroschin.spring.repositories.OrderItemRepository;
import ru.toroschin.spring.utils.Cart;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public static final Function<OrderItem, OrderItemDto> functionOrderItemToDto = oi -> {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setPrice(oi.getPrice());
        orderItemDto.setQuantity(oi.getQuantity());
        orderItemDto.setPricePerProduct(oi.getPricePerProduct());
        orderItemDto.setProductDto(ProductService.functionProductToDto.apply(oi.getProduct()));
        return orderItemDto;
    };

    public void saveAll(List<OrderItem> orderItems, Order order) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }
        orderItemRepository.saveAll(orderItems);
    }
}
