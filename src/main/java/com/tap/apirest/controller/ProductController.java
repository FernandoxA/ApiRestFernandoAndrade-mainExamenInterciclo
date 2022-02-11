package com.tap.apirest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tap.apirest.entity.Products;
import com.tap.apirest.service.ProductService;

@RestController
@RequestMapping("/api/products")

public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//Crear
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Products products){
		
	    return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(products));
	
	}
	
	//Buscar
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable(value = "id") Long productid) {
		Optional<Products> oUser = productService.findById(productid);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oUser);
		
	}
	
	//Editar
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody Products userDetails, @PathVariable (value = "id")Long productid){
		Optional<Products> products = productService.findById(productid);
		
		if(!products.isPresent()) {
			return ResponseEntity.notFound().build();		
		}
		
		// BeanUtils.copyProperties(userDetails, user.get());
		products.get().setDescripcion(userDetails.getDescripcion());
		products.get().setCodigo(userDetails.getCodigo());
		products.get().setPrecio(userDetails.getPrecio());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(products.get()));
	}
	
	//Borrar
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id")Long productid){
		if(!productService.findById(productid).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		productService.deleteById(productid);
		
		return ResponseEntity.ok().build();
	}
	
	
	//Leer todos los usuarios
	
	@GetMapping
	public List<Products> readAll (){
		List<Products> productos = StreamSupport
				.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return productos;
		
	}

}
