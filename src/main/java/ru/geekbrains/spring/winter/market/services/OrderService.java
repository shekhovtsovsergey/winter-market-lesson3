package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.entities.Order;
import ru.geekbrains.spring.winter.market.entities.OrderItem;
import ru.geekbrains.spring.winter.market.entities.Product;
import ru.geekbrains.spring.winter.market.entities.User;
import ru.geekbrains.spring.winter.market.model.Cart;
import ru.geekbrains.spring.winter.market.repositories.OrderRepository;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartService cartService;


    public void createOrder(User user) {
        Order order = new Order();

        Cart cart = cartService.getCurrentCart();
        log.info(String.valueOf(cart));
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setItems(
                cart.getItems().stream().map(
                    cartItem -> new OrderItem(
                        ((Product)this.productService.findById(cartItem.getProductId()).get()).getId(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
    }







}
