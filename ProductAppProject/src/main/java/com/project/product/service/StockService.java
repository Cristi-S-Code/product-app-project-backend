package com.project.product.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.product.dao.ProductRepository;
import com.project.product.dao.StockRepository;
import com.project.product.dto.StockDto;
import com.project.product.entities.Product;
import com.project.product.entities.Stock;

@Transactional
@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProductRepository productRepository;

//	public Stock addStockToDb(Stock stock) {
//		return stockRepository.save(stock);
//	}
	
//	public Stock addStockToDb(StockDto stockDto, String pzn) {
//		return stockRepository.save(new Stock(stockDto, findProductByPzn(pzn)));
//	}
	
	
	private StockDto convertToDto(Stock stock) {
		StockDto stockDto = new StockDto();
		BeanUtils.copyProperties(stock, stockDto);
		return stockDto;
	}
	
	private Stock convertToStock(StockDto stockDto) {
		Stock stock = new Stock();
		BeanUtils.copyProperties(stockDto, stock);
		return stock;
	}
	
	private Product findProductByPzn(String pzn) {
		return productRepository.findById(pzn).get();
	}
	
	public StockDto addStockToDb(StockDto stockDto, String pzn) {
		Stock stock = convertToStock(stockDto);
		productRepository.findById(pzn).ifPresentOrElse(p -> {
			stock.setProduct(p);
		}, () -> new Exception("Patient with id " + pzn + " not found!"));
		return convertToDto(stockRepository.save(stock));
	}
	
	public Stock updateStock(StockDto stockDto, String pzn) {
		Stock updatedStock = this.getStockByPzn(pzn);
		
		if(updatedStock == null)
		{
			updatedStock = new Stock();
		}
		if(updatedStock.getProduct() == null) {
			if(productRepository.existsById(pzn)) {
				updatedStock.setProduct(productRepository.getReferenceById(pzn));
			}
			
		}
		
		updatedStock.setPrice(stockDto.getPrice());
		updatedStock.setQuantity(stockDto.getQuantity());
		return stockRepository.save(updatedStock);
	}
	
	public List<Stock> addStockToTable() {
		return stockRepository.findAll() ;
	}

//	public Optional<Stock> sendStockToForm(Long id) {
//		return stockRepository.findById(id);
//	}
	
	public Stock getStockByPzn(String pzn) {
		return stockRepository.findByProductPzn(pzn);
	}

	public void deleteStockByPzn(String pzn) {
		stockRepository.deleteByProductPzn(pzn);
		
	}
	
	public Collection<Stock> addStockAndProductToTable() {
		return stockRepository.findStockWithProduct();
	}
	
//	public Stock saveStockExtra(Stock stock) {
//		return stockRepository.saveStockWithProduct(stock); 
//	}
}
