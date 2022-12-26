package ru.geekbrains.spring.winter.market.soapControllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductSoapRepository extends JpaRepository<ProductEntity, Long> {

    @Query("select s from ProductEntity s where s.title = ?1")
    ProductEntity findByName(String title);
}
