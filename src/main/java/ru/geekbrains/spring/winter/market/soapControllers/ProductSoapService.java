package ru.geekbrains.spring.winter.market.soapControllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.soap.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSoapService {
    private final ProductSoapRepository productSoapRepository;

    public static final Function<ProductEntity, Product> functionEntityToSoap = se -> {
        Product s = new Product();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    public List<Product> getAllProducts() {
        log.info(String.valueOf(productSoapRepository.findAll()));

        return productSoapRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    /*public Product getByName(String name) {
        return productRepository.findByName(name).map(functionEntityToSoap).get();
    }*/
}
