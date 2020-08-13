package com.smirnov.aviaplant.repos;

import com.smirnov.aviaplant.domains.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByTypeOfProduct(String searchRequest);

    List<Product> findAll();
}
