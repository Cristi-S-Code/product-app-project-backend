package com.project.product.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.product.entities.Product;
import com.project.product.service.ProductService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product addNewProduct(@Valid @RequestBody Product newProduct) {
		return productService.addProductToDb(newProduct);
		
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts(){
		return productService.addProductsToTable();
	}
	
	@GetMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> editProduct(@PathVariable String id) {
		return productService.sendProductToForm(id);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Product addEditedProductToList(@RequestBody Product editedProduct) {
		return productService.addProductToDb(editedProduct);
	}
	
	@DeleteMapping(value = "/delete/{id}" )
	public void deleteProduct(@PathVariable String id) {
		productService.deleteProductById(id);
	}
}
