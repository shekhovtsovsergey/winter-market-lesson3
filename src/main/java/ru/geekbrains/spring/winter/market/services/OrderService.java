package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.entities.Order;
import ru.geekbrains.spring.winter.market.entities.OrderItem;
import ru.geekbrains.spring.winter.market.entities.Product;
import ru.geekbrains.spring.winter.market.entities.User;
import ru.geekbrains.spring.winter.market.model.Cart;
import ru.geekbrains.spring.winter.market.model.CartItem;
import ru.geekbrains.spring.winter.market.repositories.OrderRepository;
import ru.geekbrains.spring.winter.market.repositories.ProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartService cartService;


    public void createOrder(User user) {
        Order order = new Order();
        List<OrderItem> orderItemList = new LinkedList<>();
        Cart cart = cartService.getCurrentCart();
        log.info(String.valueOf(cart.getItems()));

        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());


        for (CartItem tmps : cart.getItems()) {
            Optional<Product> product = productService.findById(tmps.getProductId());

            OrderItem newOrderItem = new OrderItem(null,
                    product.get(),
                    order,
                    tmps.getQuantity(),
                    tmps.getPricePerProduct(),
                    tmps.getPrice());

            orderItemList.add(newOrderItem);
        }

        order.setItems(orderItemList);
        orderRepository.save(order);

    }
}
