package com.project.product.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "pzn", unique = true, nullable = false, length = 8)
	@Size(max = 8)// sa pun validarea de minim si pe FE
	@NotNull
	private String pzn;

	@Column(name = "supplier", length = 100)
	@Size(max = 100)
	private String supplier;

	@NotBlank(message = "Product name cannot be blank!")
	@Column(name = "product_name", length = 100)
	@Size(max = 100)
	private String productName;

	@NotBlank(message = "Strength name cannot be blank!")
	@Column(name = "strength", length = 100)
	@Size(max = 100)
	private String strength;

	@NotBlank(message = "Package size name cannot be blank!")
	@Column(name = "package_size", length = 20)
	@Size(max = 20)
	@NotEmpty
	private String packageSize;

	@NotBlank(message = "Unit cannot be blank!")
	@Column(name = "unit", length = 2)
	@Size(max = 2)
	@NotEmpty
	private String unit;

	public String getPzn() {
		return pzn;
	}

	public void setPzn(String pzn) {
		this.pzn = pzn;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
