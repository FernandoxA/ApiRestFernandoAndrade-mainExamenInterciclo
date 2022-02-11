package com.tap.apirest.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tap.apirest.entity.Products;

public interface ProductService {
	
	public Iterable<Products> findAll();
	
	public Page<Products> findAll(Pageable pageable);
	
	public Optional<Products> findById(Long id);
	
	public Products save(Products products);
	
	public void deleteById(Long id);

}
