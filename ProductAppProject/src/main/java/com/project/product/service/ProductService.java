package com.project.product.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import com.project.product.dao.ProductRepository;
import com.project.product.entities.Product;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;
	
	@PostConstruct
	private void populateProductList() {
		try {
			if (productRepository.count() == 0) {
				Resource resource = new ClassPathResource("products.csv");
				CsvSchema schema = CsvSchema.emptySchema().withHeader();
				CsvMapper mapper = new CsvMapper();
				MappingIterator<Product> iterator = mapper.readerFor(Product.class).with(schema)
						.readValues(resource.getInputStream());
				productRepository.saveAll(iterator.readAll());
			}
		} catch (IOException e) {
			logger.error("An error occurred while populating products.", e);
		}
	}

	public Product addProductToDb(Product product) {
		String pznLeftPadd = product.getPzn();
		product.setPzn(StringUtils.leftPad(pznLeftPadd, 8, "0"));
		return productRepository.save(product);
	}
	
	public List<Product> addProductsToTable() {
		return productRepository.findAll();
	}

	public Optional<Product> sendProductToForm(String id) {
		return productRepository.findById(id);
	}

	public void deleteProductById(String id) {
		productRepository.deleteById(id);
	}

	
	
}
