package com.fornew.demo;

public class Product {
		
	private String color;
	private Double pricr;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double getPricr() {
		return pricr;
	}
	public void setPricr(Double pricr) {
		this.pricr = pricr;
	}
	public Product(String color, Double pricr) {
		super();
		this.color = color;
		this.pricr = pricr;
	}
	@Override
	public String toString() {
		return "Product [color=" + color + ", pricr=" + pricr + "]";
	}
	
}
