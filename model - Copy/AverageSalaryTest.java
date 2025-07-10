package com.aurionpro.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageSalaryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee> staff = Arrays.asList(new Employee("IT", 50000), new Employee("HR", 60000),
				new Employee("IT", 80000), new Employee("IT", 90000), new Employee("HR", 70000));

		Map<String, Double> avgSalary = staff.stream()
				.collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingDouble(Employee::getSalary)));

		System.out.println(avgSalary); 
	}
}
