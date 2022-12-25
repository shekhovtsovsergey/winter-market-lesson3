package ru.geekbrains.spring.winter.market.soapControllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.soap.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSoapService {
    private final ProductSoapRepository productSoapRepository;

    public static final Function<ProductEntity, Product> functionEntityToSoap = se -> {
        Product s = new Product();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setCost(se.getCost());
        return s;
    };

    public List<Product> getAllProducts() {
        return productSoapRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    /*public Product getByName(String name) {
        return productRepository.findByName(name).map(functionEntityToSoap).get();
    }*/
}
