package model;

import java.util.List;

import subclass.CustomDate;

public class Receipt {
	private String id;
	private String employee;
	private String customer;
	private CustomDate date;
	private List<Product> productList;
	
	public Receipt(String id, String employee, String customer, CustomDate date, List<Product> productList) {
		this.id = id;
		this.employee = employee;
		this.customer = customer;
		this.date = date;
		this.productList = productList;
	}
	
	public Receipt(String id, String employee, String customer, CustomDate date) {
		this.id = id;
		this.employee = employee;
		this.customer = customer;
		this.date = date;
	}
	
	public String getId() {
		return id;
	}
	public String getEmployee() {
		return employee;
	}
	public String getCustomer() {
		return customer;
	}
	public CustomDate getDate() {
		return date;
	}
	public List<Product> getProductList() {
		return productList;
	}
}
