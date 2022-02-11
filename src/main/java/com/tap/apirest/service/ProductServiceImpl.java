package com.tap.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tap.apirest.entity.Products;
import com.tap.apirest.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Products> findAll() {
		
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Products> findAll(Pageable pageable) {
		
		return productRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Products> findById(Long id) {
		
		return productRepository.findById(id);
	}

	@Override
	@Transactional
	public Products save(Products products) {
		
		return productRepository.save(products);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		productRepository.deleteById(id);
	}

	

}
