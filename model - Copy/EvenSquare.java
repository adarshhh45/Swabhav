package com.aurionpro.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> nums = Arrays.asList(10, 21, 30, 14, 45, 16);
		List<Integer> result = nums.stream().filter(n -> n % 2 == 0).map(n -> n * n).collect(Collectors.toList());

		System.out.println(result);
	}

}