package com.project.product.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.BeanUtils;

import com.project.product.dto.StockDto;


@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "stock_id")
	private Long stockId;
	
	@NotNull(message = "Quantity cannot be blank!")
	@Column(name = "quantity")
	private Long quantity;
	
	@NotNull(message = "Price cannot be blank!")
	@Column(name = "price", precision = 10, scale = 2)
	private BigDecimal price;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToOne(cascade = CascadeType.REMOVE) 
	@JoinColumn(name = "pzn", referencedColumnName = "pzn")
	private Product product;
	
	public Stock() {
		
	}
	
	public Stock(StockDto stockDto, Product product) {
		BeanUtils.copyProperties(stockDto, this);
		this.product = product;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
