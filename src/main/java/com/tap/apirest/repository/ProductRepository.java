package com.tap.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tap.apirest.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}
