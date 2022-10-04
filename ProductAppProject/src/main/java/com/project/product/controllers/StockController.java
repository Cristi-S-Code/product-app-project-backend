package com.project.product.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.product.dto.StockDto;
import com.project.product.entities.Stock;
import com.project.product.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;
	
//	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public Stock addnewStock(@RequestBody Stock newStock) {
//		return stockService.addStockToDb(newStock);
//	}
	
//	@PostMapping(value = "/add/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE,
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public StockDto addnewStock(@RequestBody StockDto newStock, @PathVariable String pzn) {
//		return new StockDto(stockService.addStockToDb(newStock, pzn));
//	}
	
	@PostMapping(value = "/add/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public StockDto addnewStock(@RequestBody StockDto newStock, @RequestParam String pzn) {
		return stockService.addStockToDb(newStock, pzn);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Stock> getAllStock(){
		return stockService.addStockToTable();
	}
	
//	@GetMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Optional<Stock> editStock(@PathVariable Long id){
//		return stockService.sendStockToForm(id);
//	}
	
	@GetMapping(value = "/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Stock getStockByPzn(@PathVariable String pzn){
		return stockService.getStockByPzn(pzn);
	}
	
	@PutMapping(value = "/update/{pzn}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDto addEditedStockToList(@RequestBody StockDto editedStock, @RequestParam String pzn) {
		return new StockDto(stockService.updateStock(editedStock, pzn));
	}
	
	@DeleteMapping(value = "delete/{pzn}") 
	public void deleteStock(@PathVariable String pzn) {
		stockService.deleteStockByPzn(pzn);
	}
	
	@GetMapping(value = "/table", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Stock> getAllStockExtra() {
		return stockService.addStockAndProductToTable();
	}
	
//	@PostMapping(value = "/table/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//	public Stock addnewStockExtra(@RequestBody Stock newStock) {
//		return stockService.saveStockExtra(newStock);
//	}
}
