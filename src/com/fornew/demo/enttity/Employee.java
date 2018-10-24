package com.fornew.demo.enttity;

import java.math.BigDecimal;

public class Employee {
	
	private String name;
	private BigDecimal salary;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", age=" + age + "]";
	}
	
	
	public Employee() {
		super();
		
	}
	
	public Employee(String name, BigDecimal salary, int age) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
	
	
	public Employee(int age) {
		super();
		this.age = age;
	}
	
	
}
