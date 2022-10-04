package com.project.product.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.product.dto.StockDto;
import com.project.product.entities.Product;
import com.project.product.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

	@Query(value = 
			"SELECT p.pzn, p.supplier, p.product_name, p.strength,"
			+ " p.package_size, p.unit, s.price, s.quantity, s.stock_id\r\n"
			+ "FROM products as p\r\n"
			+ "LEFT JOIN stock as s\r\n"
			+ "ON p.pzn= s.pzn", nativeQuery = true)
//			+ "WHERE s.stock_id NOT NULL"
	Collection<Stock> findStockWithProduct(); 
	
//	@Query(value = 
//			"SELECT p.pzn, p.supplier, p.product_name, p.strength,"
//			+ " p.package_size, s.price, s.quantity, s.stock_id\r\n"
//			+ "FROM products as p\r\n"
//			+ "LEFT JOIN stock as s\r\n"
//			+ "ON p.pzn= s.pzn", nativeQuery = true)
//	Stock saveStockWithProduct(Stock stock);
	
	Stock findByProductPzn(String pzn);
	
	void deleteByProductPzn(String pzn);
}
