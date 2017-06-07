package com.employeedao.EmployeeDAO;

import java.sql.*;

public class Employee {
	private Integer number;
	private String name;
	private Double salary;
	private Integer age;
	private Date date;
	public Employee(Integer number, String name, Double salary, Integer age, Date date) {
		super();
		this.number = number;
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.date = date;
	}
	public Employee(String name, Double salary, Integer age, Date date) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.date = date;
	}


	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
	@Override
	public String toString() {
		return "Employee [number=" + number + ", name=" + name + ", salary=" + salary + ", age=" + age + ", date="
				+ date + "]";
	}
	
}
