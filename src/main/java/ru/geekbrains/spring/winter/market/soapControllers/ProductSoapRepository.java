package ru.geekbrains.spring.winter.market.soapControllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.winter.market.soap.Product;

@Repository
public interface ProductSoapRepository extends JpaRepository<ProductEntity, Long> {

    @Query("select s from ProductEntity s where s.title = ?1")
    Product findByName(String title);
}
