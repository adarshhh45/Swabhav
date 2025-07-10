package com.aurionpro.model;

public class Employee {
	private String dept;
	private double salary;
	public Employee(String dept, double salary) {
		super();
		this.dept = dept;
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String display() {
		return "Employee [dept=" + dept + ", salary=" + salary + "]";
	}
	
	
}
